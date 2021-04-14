<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Connexion</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
    </head>
    <body>
    	<div id="center" class="frame">
        	<form action="connection" method="POST" id="auth-form">
        		<h2>Page de connexion</h2>
        		<div class="form-item">
            		<label for="username">Username :</label>
            		<input type="text" id="username" name="username" size="20" required>
        		</div>
            	<br>
            	<div class="form-item">
            		<label for="password">Password :</label>
        			<input type="password" id="password" name="password" size="20" required>
            	</div>
            	<br>
            	<div class="form-item center">
            		<input type="submit" value="Connexion">
            	</div>
        	</form>
        	<c:if test="${not empty error}">
        		<div class="form-message center">
        			<p>${error}</p>
        		</div>
        	</c:if>
        </div>
    </body>
</html>
