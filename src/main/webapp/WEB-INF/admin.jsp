<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<title>Espace d'administration</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">

		<!--  <script type="text/javascript">
			function editUser() {
				var id = event.srcElement.id;

				var xhr = new XMLHttpRequest();
				xhr.open("POST", 'admin', true);
				xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				xhr.send("edit=" + id);

				alert(id);
			}
		</script>-->
	</head>
	<body class="kanban-background">
		<div id="center">
			<c:if test="${not empty username}">
				<c:choose>
					<c:when test="${hasManageUsersPermission}">
						<div class="frame">
							<h2>
								<span>${username}</span>, vous êtes connecté(e) à votre espace d'administration !
							</h2>
							<a href="home">Page d'accueil</a>
						</div>
						<br/>

						<div>
							<table class="admin-table">
								<thead>
									<tr>
										<th class="col-head">
											<p>Nom d'utilisateur</p>
										</th>
										<th class="col-head">
											<p>Rang</p>
										</th>
										<th class="col-head col-head-icon">
											<p> </p>
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${users}" var="user">
										<tr>
											<td class="col-body">
												<p>${fn:escapeXml(user.username)}</p>
											</td>
											<td class="col-body">
												<c:forEach items="${ranks}" var="rank">
													<c:if test="${rank.id == user.rank.id }">
														<p>${fn:escapeXml(rank.rankName)}</p>
													</c:if>
												</c:forEach>
											</td>
											<td class="col-body col-body-icon">
												<a href="edituser?user=${user.id}">
													<img alt="Modifier" src="<c:url value="resources/images/editer.png" />">
												</a>
												<!-- <button class="edit-button" id="${user.id}" onclick="editUser();"></button> -->
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:when>

					<c:otherwise>
						<div class="frame">
    	  					<h2>Vous n'avez pas la permission d'afficher cette page</h2>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</body>
</html>
