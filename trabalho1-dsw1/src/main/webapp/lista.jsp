<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="page.title" /></title>
	</head>

	<body>
		<div align="center">
			<h3><fmt:message key="users.list" /></h3>
			<table border="1">
				<!-- <tr>
					<th><fmt:message key="user.ID" /></th>
					<th><fmt:message key="user.login" /></th>
					<th><fmt:message key="user.password" /></th>
					<th><fmt:message key="user.name" /></th>
					<th><fmt:message key="user.role" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr> -->
				<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
					<tr>
						<td><c:out value="${profissional.nome}" /></td>
						<td><c:out value="${profissional.especialidade}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>