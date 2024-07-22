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
			<h1>
				<fmt:message key="professionals.welcome" />
			</h1>
			<h2>
				<a href="/<%= contextPath %>/clientes"> 
					<fmt:message key="clients.entity" />
				</a> 
				&nbsp;&nbsp;&nbsp;
				<a href="/<%= contextPath %>/usuarios"> 
					<fmt:message key="users.entity" />
				</a> 
				&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%= contextPath %>/profissionais/cadastro">
					<fmt:message key="professionals.create" />
				</a> 
			</h2>
			<h3><fmt:message key="professionals.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="professionals.ID" /></th>
					<th><fmt:message key="professionals.name" /></th>
					<th><fmt:message key="professionals.email" /></th>
					<th><fmt:message key="professionals.password" /></th>
					<th><fmt:message key="professionals.cpf" /></th>
					<th><fmt:message key="professionals.role" /></th>
					<th><fmt:message key="professionals.speciality" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
					<tr>
						<td><c:out value="${profissional.id}" /></td>
						<td><c:out value="${profissional.nome}" /></td>
						<td><c:out value="${profissional.email}" /></td>
						<td><c:out value="${profissional.senha}" /></td>
						<td><c:out value="${profissional.cpf}" /></td>
						<td><c:out value="${profissional.papel}" /></td>
						<td><c:out value="${profissional.especialidade}" /></td>
						<td><a
							href="/<%= contextPath %>/profissionais/edicao?id=<c:out value='${profissional.id}' />">
								<fmt:message key="professionals.update" />
						</a>
              							&nbsp;&nbsp;&nbsp;&nbsp;
                                <a
									href="/<%= contextPath %>/profissionais/remocao?id=<c:out value='${profissional.id}' />"
									onclick="return confirm('<fmt:message key="confirm.link" />');">
									<fmt:message key="professionals.delete" />
								</a>
							</td>
					</tr>
				</c:forEach>
			</table>

		</div>


	</body>
</fmt:bundle>

</html>