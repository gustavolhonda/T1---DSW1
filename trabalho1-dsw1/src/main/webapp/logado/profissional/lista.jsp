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
				<fmt:message key="profissionals.welcome" />
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
					<fmt:message key="profissionals.create" />
				</a> 
			</h2>
			<h3><fmt:message key="profissionals.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="profissionals.ID" /></th>
					<th><fmt:message key="profissionals.name" /></th>
					<th><fmt:message key="profissionals.email" /></th>
					<th><fmt:message key="profissionals.password" /></th>
					<th><fmt:message key="profissionals.cpf" /></th>
					<th><fmt:message key="profissionals.role" /></th>
					<th><fmt:message key="profissionals.speciality" /></th>
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
								<fmt:message key="profissionals.update" />
						</a>
              							&nbsp;&nbsp;&nbsp;&nbsp;
                                <a
									href="/<%= contextPath %>/profissionais/remocao?id=<c:out value='${profissional.id}' />"
									onclick="return confirm('<fmt:message key="confirm.link" />');">
									<fmt:message key="profissionals.delete" />
								</a>
							</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>