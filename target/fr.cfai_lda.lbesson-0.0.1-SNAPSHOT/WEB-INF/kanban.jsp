<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Espace Kanban</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">

		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  		<script>
  			$( function() {
    			$( ".draggable" ).draggable({
    				containment: "tbody tr"
    				/* ,stop: function( event, ui ) {
    					alert($(this).children(".task_id").attr("value"));
    			    } */
    			});
    			$( ".droppable" ).droppable({
    				drop: function( event, ui ) {
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
    			        $( this )
    			          .find( "input.task_id" )
    			            .html( "Dropped!" );
    			    }
    				/*stop: function(event, ui) {
    					alert($(this).children(".taskid").attr("value"));
    				} */
    			});
    			// Getter
//     			var cursor = $( ".draggable" ).draggable( "option", "cursor" );

    			// Setter
//     			$( ".draggable" ).draggable( "option", "cursor", "crosshair" );
  			} );
  		</script>
    </head>
    <body>
    	<div id="center">
        	<c:if test="${not empty username}">
       			<table class="kanban-table">
       				<caption>Kanban Board<small>(connecté en tant que : ${username})</small></caption>
       				<thead>
						<tr>
							<c:forEach items="${taskProgress}" var="taskProgress">
								<th>
									<p>${taskProgress.progressLabel}</p>
								</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach items="${taskProgress}" var="taskProgress">
								<td class="droppable ui-widget-header">
									<input type="hidden" class="taskprogress_id" value="${taskProgress.id}">
									<p>Drop here</p>
									<c:forEach items="${tasks}" var="task">
										<c:if test="${taskProgress.id == task.taskProgress.id }">
											<div class="draggable ui-widget-content">
												<input type="hidden" class="task_id" value="${task.id}">
												<p>${task.name}</p>
											</div>
										</c:if>
									</c:forEach>
								</td>
							</c:forEach>
						</tr>
					</tbody>
       			</table>
        	</c:if>
        </div>
    </body>
</html>
