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
		<% String contextPath = request.getContextPath().replace("/", ""); %>

		<div align="center">
			<h3><fmt:message key="profissionals.list" /></h3>

			<a href="${pageContext.request.contextPath}/login.jsp"> 
					<fmt:message key="back.link" />
			</a>
			<br/>
			<br/>
			
			<table border="1">
				<tr>
					<th><fmt:message key="profissionals.ID" /></th>
					<th><fmt:message key="profissionals.name" /></th>
					<th><fmt:message key="profissionals.email" /></th>
					<th><fmt:message key="profissionals.speciality" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr> 
				<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
					<tr>
						<td><c:out value="${profissional.id}" /></td>
						<td><c:out value="${profissional.nome}" /></td>
						<td><c:out value="${profissional.email}" /></td>
						<td><c:out value="${profissional.especialidade}" /></td>
						<td><a
							href="/<%= contextPath %>/agendar/?id=<c:out value='${profissional.id}' />">
								<fmt:message key="profissionals.schedule" />
						</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>