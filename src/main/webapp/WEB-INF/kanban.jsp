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
    				containment: "tbody tr",
    				stop: function(event, ui) {
    					alert($(this).attr("shape"));
    				}
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
								<th>${taskProgress.progressLabel}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td></td>
							<td>
							<c:forEach items="${tasks}" var="task">
								<area shape="rect" class="draggable" />
									<p>${task.name}</p>
<!-- 								</area>class="kanban-table-body-column" -->
							</c:forEach>
							</td>
						</tr>
					</tbody>
<!--        				<div class="kanban-table-column"></div> -->
       			</table>
        	</c:if>
        </div>
    </body>
</html>
