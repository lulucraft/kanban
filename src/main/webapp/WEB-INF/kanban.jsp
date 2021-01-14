<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Espace Kanban</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">

		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  		<script>
  			$(function() {
  				// Set scope
    			$(".draggable").draggable({
    				containment: "tbody tr"
    			});
    			$(".draggable").click(function(e){
    				showTaskDetails(this);
    			});

  				// Set drop action
    			$(".droppable").droppable({
    				drop: function(event, ui) {
    					//alert(ui.helper.find("input.task_id").attr("value"));
    					//alert($(this).find("input.task_id").attr("value"));
    					//alert($(this).find("input.taskprogress_id").attr("value"));

    					var xhr = new XMLHttpRequest();
    					xhr.open("POST", 'kanban', true);
    					xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    					xhr.send(
    							"move=" + ui.helper.find("input.task_id").attr("value") +
    							"&progress=" + $(this).find("input.taskprogress_id").attr("value")
    					);
    			        $(this)
    			          .find( "input.task_id" )
    			            .html( "Dropped!" );
    			    }
    			});

    			// Change tast_type drowdown background to current choice
      			$(function() {
      				 $("#task_type").change(function() {
      					var optionColor = $('option:selected', this).css("background-color");
      	  				$(document.body).find("#task_type").css("background-color", optionColor.toString());
      				 });
      			});

      			String.prototype.htmlEscape = function() {
      				return $('<div/>').text(this.toString()).html();
      			};

    			/*stop: function(event, ui) {
    				alert($(this).children(".taskid").attr("value"));
    			} */
    			// Getter
//     			var cursor = $( ".draggable" ).draggable( "option", "cursor" );
    			// Setter
//     			$( ".draggable" ).draggable( "option", "cursor", "crosshair" );
  			} );
  		</script>
  		<script type="text/javascript">
  			function insertAfter(referenceNode, newNode) {
  		  		referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
  			}

  			function addTask() {
  				var addTaskForm = document.forms['form-new-task'];
  				var taskName = addTaskForm.elements.task_name.value;
  				if (taskName === '') return;
  				var taskType = addTaskForm.elements.task_type.value;
  				if (taskType === '') return;

  				var xhr = new XMLHttpRequest();
  				xhr.open("POST", 'kanban', true);
  				xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				xhr.send(
						"new=" + taskName +
						"&tasktype=" + taskType
						);

				const result = getLastTaskId();
				summonTask(result);

				alert('Tâche ajoutée');
			}

  			function summonTask(taskId) {
  				taskprogress_id = document.getElementsByClassName('taskprogress_id')[0];
  				if (taskprogress_id == null) return;

  				task_name = document.getElementById('task_name');
  				if (task_name == null) return;

  				divParent = document.createElement("div");
  				divParent.className = 'draggable ui-widget-content';

  				div = document.createElement("div");
  				div.className = 'kanban-task';
  				div.setAttribute('onclick', 'showTaskDetails(this);');
 
  				task_type = document.getElementById("task_type");
  				if (task_type != null) div.style.backgroundColor = task_type.style.backgroundColor;

  				input = document.createElement("input");
  				input.type = 'hidden';
  				input.className = 'task_id';
  				input.value = taskId;

  				p = document.createElement("p");
  				p.innerHTML = task_name.value;

  				insertAfter(taskprogress_id, divParent);
  				divParent.appendChild(div);
  				div.appendChild(input);
  				div.appendChild(p);

  	  			// Set scope
  	    		$(".draggable").draggable({
  	    			containment: "tbody tr"
  	    		});
			}

  			function reload() {
				document.location.reload(true);
			}

  			function disconnect() {
  				document.location = "http://localhost:8080/kanban/connection";
  			}
 
  			function getTaskDetails(e) {
  				var taskId = e.getElementsByClassName('task_id')[0];
  				if (taskId == null) return;

  				// Get task id
  				taskId = taskId.value;

  				var xhr = new XMLHttpRequest();
  				xhr.open("POST", 'api', true);
//   				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   				xhr.overrideMimeType("application/json");
  				xhr.onload = function() {
  					if (xhr.readyState == 4) {// Fully loaded data
  						if (xhr.readyState == 4) { // Http state == Done
							var jsonResponse = JSON.parse(xhr.responseText);

							document.getElementById('task-details-task_name').innerHTML = jsonResponse.name.htmlEscape();
							document.getElementById('task-details-task_date').innerHTML = new Date(jsonResponse.creationDate).toString();
							document.getElementById('task-details-task_owner_firstname').innerHTML = 'Prénom du créateur : ' + jsonResponse.taskOwner.firstName.htmlEscape();
							document.getElementById('task-details-task_owner_lastname').innerHTML = 'Nom de famille du créateur : ' + jsonResponse.taskOwner.lastName.htmlEscape();
							document.getElementById('task-details-task_type').innerHTML = 'Type de tâche : ' + jsonResponse.taskType.label.htmlEscape();
							document.getElementById('task-details-task_progress').innerHTML = 'Progression : ' + jsonResponse.taskProgress.label.htmlEscape();
  						}
  					}
  				};
				xhr.send("taskid=" + taskId);
			}

  			function getLastTaskId() {
  				var xhr = new XMLHttpRequest();
  				xhr.open('POST', 'api', false);
  				xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   				xhr.overrideMimeType("application/json");
  				xhr.send(null);
  				return JSON.parse(xhr.responseText).id;
			}

  			function showTaskDetails(e) {
  				// Show modal
  				taskDialog = document.getElementById('task-details-dialog');
  				if (taskDialog == null) return;
  				taskDialog.showModal();
  				getTaskDetails(e);
  			}

  			function closeTaskDetailsDialog() {
  				// Close modal
  				taskDialog = document.getElementById("task-details-dialog");
  				if (taskDialog == null) return;
  				taskDialog.close();
  			}

//   			function changeTaskType() {
//   				var selectTaskType = document.getElementById("task_type");
//   				var selectedValue = selectTaskType.options[selectTaskType.selectedIndex].value;
// 			}
  		</script>
    </head>
    <body>
    	<div id="center" style="width: 90%;">
        	<c:if test="${not empty username}">
       			<div class="kanban-title center">
       				<button class="left-corner-button icon" onclick="disconnect();">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 384" height="30px" width="30px">
							<g>
								<path d="M341.333,0H42.667C19.093,0,0,19.093,0,42.667V128h42.667V42.667h298.667v298.667H42.667V256H0v85.333,C0,364.907,19.093,384,42.667,384h298.667C364.907,384,384,364.907,384,341.333V42.667C384,19.093,364.907,0,341.333,0z" />
								<polygon points="151.147,268.48 181.333,298.667 288,192 181.333,85.333 151.147,115.52 206.293,170.667 0,170.667 0,213.333,206.293,213.333" />
							</g>
						</svg>
       				</button>

					<span>Kanban Board</span>
       				<small>(connecté en tant que : ${fn:escapeXml(username)})</small>

					<button class="right-corner-button icon" onclick="reload();">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" height="30px" width="30px">
							<path d="m61.496094 279.609375c-.988282-8.234375-1.496094-16.414063-1.496094-23.609375 0-107.402344 88.597656-196 196-196 50.097656 0 97 20.199219 131.5 51.699219l-17.300781 17.601562c-3.898438 3.898438-5.398438 9.597657-3.898438 15 1.800781 5.097657 6 9 11.398438 10.199219 3.019531.605469 102.214843 32.570312 95.898437 31.300781 8.035156 2.675781 19.917969-5.894531 17.703125-17.699219-.609375-3.023437-22.570312-113.214843-21.300781-106.902343-1.199219-5.398438-5.101562-9.898438-10.5-11.398438-5.097656-1.5-10.800781 0-14.699219 3.898438l-14.699219 14.398437c-45.300781-42.296875-107.503906-68.097656-174.101562-68.097656-140.699219 0-256 115.300781-256 256v.597656c0 8.457032.386719 14.992188.835938 19.992188.597656 6.625 5.480468 12.050781 12.003906 13.359375l30.816406 6.160156c10.03125 2.007813 19.050781-6.402344 17.839844-16.5zm0 0"/>
							<path d="m499.25 222.027344-30.90625-6.296875c-10.042969-2.046875-19.125 6.371093-17.890625 16.515625 1.070313 8.753906 1.546875 17.265625 1.546875 23.753906 0 107.398438-88.597656 196-196 196-50.097656 0-97-20.199219-131.5-52l17.300781-17.300781c3.898438-3.898438 5.398438-9.597657 3.898438-15-1.800781-5.101563-6-9-11.398438-10.199219-3.019531-.609375-102.214843-32.570312-95.898437-31.300781-5.101563-.898438-10.203125.601562-13.5 4.199219-3.601563 3.300781-5.101563 8.699218-4.203125 13.5.609375 3.019531 22.574219 112.210937 21.304687 105.898437 1.195313 5.402344 5.097656 9.902344 10.496094 11.398437 6.261719 1.570313 11.488281-.328124 14.699219-3.898437l14.402343-14.398437c45.296876 42.300781 107.5 69.101562 174.398438 69.101562 140.699219 0 256-115.300781 256-256v-.902344c0-6.648437-.242188-13.175781-.796875-19.664062-.570313-6.628906-5.433594-12.074219-11.953125-13.40625zm0 0"/>
						</svg>
					</button>
     			</div>

       			<table class="kanban-table">
       				<thead>
						<tr>
							<c:forEach items="${taskProgress}" var="taskProgress">
								<th>
									<p>${fn:escapeXml(taskProgress.label)}</p>
								</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach items="${taskProgress}" var="taskProgress">
								<td class="droppable ui-widget-header">
									<input type="hidden" class="taskprogress_id" value="${taskProgress.id}">
<!-- 									<p>Drop here</p> -->
									<c:forEach items="${tasks}" var="task">
										<c:if test="${taskProgress.id == task.taskProgress.id }">
											<div class="draggable ui-widget-content">
												<div class="kanban-task" style="background-color: rgb(${task.taskType.color.getRGBColor().getString()});">
													<input type="hidden" class="task_id" value="${task.id}">
													<p>${fn:escapeXml(task.name)}</p>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</td>
							</c:forEach>
						</tr>
					</tbody>
       			</table>

				<!-- Task controls -->
				<c:if test="${hasCreateTaskPermission}">
					<div class="kanban-controls center">
						<form id="form-new-task">
							<label for="task_name">Nouvelle tâche : </label>
							<input type="text" name="task_name" id="task_name" placeHolder="Nom de la tâche" value="${fn:escapeXml(param.task_name)}" required>

							<label>Type de tâche : </label>
							<select id="task_type" style="background-color: rgb(${taskTypes[0].color.getRGBColor().getString()})" onchange="changeTaskType();" required>
								<c:forEach items="${taskTypes}" var="taskType">
									<option value="${taskType.id}" style="background-color: rgb(${taskType.color.getRGBColor().getString()});">${fn:escapeXml(taskType.label)}</option>
								</c:forEach>
							</select>

							<input type="button" name="add" onclick="addTask();" value="Ajouter" style="padding: 3px 5px 3px 5px;margin: 2px;">
						</form>
					</div>
				</c:if>

				<!-- Task details dialog -->
				<dialog id="task-details-dialog">
					<menu class="task-details-button">
						<button onclick="closeTaskDetailsDialog();">x</button>
					</menu>
					<div class="task-details">
						<p id="task-details-task_name"></p>
						<p id="task-details-task_date"></p>
						<p id="task-details-task_owner_firstname"></p>
						<p id="task-details-task_owner_lastname"></p>
						<p id="task-details-task_type"></p>
						<p id="task-details-task_progress"></p>
<%-- 						<c:if test="${tasks.size() > 0}"> --%>
<%-- 							<c:forEach items="${tasks}" var="task"> --%>
<%-- 								<c:if test="${task.id == id}"> --%>
<%-- 									<p>${task.id}</p> --%
<%--								</c:if> --%>
<%-- 							</c:forEach> --%>
<%-- 						</c:if> --%>
					</div>
				</dialog>
        	</c:if>
        </div>
    </body>
</html>
