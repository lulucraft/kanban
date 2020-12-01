<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Espace Kanban</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
    	<div id="center">
        	<h2>Kanban</h2>
       		<div class="kanban-table">
       			
       		</div>

        	<c:if test="${not empty error}">
        		<div class="form-message center">
        			<p>${error}</p>
        		</div>
        	</c:if>
        </div>
    </body>
</html>
