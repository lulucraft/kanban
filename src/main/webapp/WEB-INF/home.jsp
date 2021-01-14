<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Espace utilisateur</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/kanban/css/style.css">
    </head>
    <body class="kanban-background">
    	<div id="center" class="frame">
    		<h3><span>${username}</span>, vous êtes connecté(e) à votre espace utilisateur !</h3>
    		<div class="center"><a href="kanban">Accéder au tableau Kanban</a></div>
    		<c:if test="${hasShowPanelPermission}">
    			<div class="center"><a href="panel">Accéder au panel d'administration</a></div>
    		</c:if>
        </div>
    </body>
</html>
