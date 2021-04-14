<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Title</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
    </head>
    <body class="kanban-background">
		<div id="center">
			<c:choose>
				<c:when test="${hasUpdateUserPermission}">
					<div class="frame">
   						<h2>Modification de l'utilisateur <span>${userToUpdate.username}</span></h2>
   					</div>

					<div class="frame">
						<form method="POST" action="edituser">
							<input type="hidden" name="user_id" value="${userToUpdate.id}">
							<label for="username">Nom d'utilisateur : </label>
							<input type="text" id="username" name="username" placeHolder="Nom d'utilisateur" value="${fn:escapeXml(userToUpdate.username)}" required>
							<br>
							<label for="rank">Rang : </label>
							<select id="rank" name="rank_id">
								<c:forEach items="${ranks}" var="rank">
									<c:choose>
										<c:when test="${rank.id == userToUpdate.rank.id}">
											<option value="${rank.id}" selected>${fn:escapeXml(rank.rankName)}</option>
										</c:when>
										<c:otherwise>
											<option value="${rank.id}">${fn:escapeXml(rank.rankName)}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<br>
							<label for="firstname">Prénom : </label>
							<input type="text" id="firstname" name="firstname" placeHolder="Prénom" value="${fn:escapeXml(userToUpdate.firstName)}" required>
							<br>
							<label for="lastname">Nom de famille : </label>
							<input type="text" id="lastname" name="lastname" placeHolder="Nom de famille" value="${fn:escapeXml(userToUpdate.lastName)}" required>
							<br>
							<div class="center">
								<br>
								<input type="submit" value="Mettre à jour">
							</div>
							<c:if test="${not empty errormsg}">
								<div class="center">
									<p style="color: red;margin-bottom: 0;">${errormsg}</p>
								</div>
							</c:if>
						</form>
					</div>
				</c:when>

				<c:otherwise>
					<div class="frame">
   	  					<h2>Vous n'avez pas la permission d'afficher cette page</h2>
					</div>
				</c:otherwise>
			</c:choose>
    	</div>
    </body>
</html>
