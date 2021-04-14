<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Title</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
        
    </head>
    <body class="kanban-background">
		<div id="center">
			<c:if test="${not empty username}">
				<div class="frame">
    				<h2>Modification de l'utilisateur <span>${user}</span></h2>
    			</div>
    		</c:if>
    	</div>
    </body>
</html>
