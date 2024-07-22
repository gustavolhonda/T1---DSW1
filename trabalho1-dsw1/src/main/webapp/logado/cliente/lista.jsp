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
				<fmt:message key="clients.welcome" />
			</h1>
			<h2>
				<a href="/<%= contextPath %>/profissionais">
					<fmt:message key="profissionals.entity" />
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
				<a href="/<%= contextPath %>/clientes/cadastro">
					<fmt:message key="clients.create" />
				</a>
			</h2>
			<h3><fmt:message key="clients.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="clients.ID" /></th>
					<th><fmt:message key="clients.name" /></th>
					<th><fmt:message key="clients.email" /></th>
					<th><fmt:message key="clients.password" /></th>
					<th><fmt:message key="clients.cpf" /></th>
					<th><fmt:message key="clients.role" /></th>
					<th><fmt:message key="clients.telephone" /></th>
					<th><fmt:message key="clients.sex" /></th>
					<th><fmt:message key="clients.birthDate" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="cliente" items="${requestScope.listaClientes}">
					<tr>
						<td>${cliente.id}</td>
						<td>${cliente.nome}</td>
						<td>${cliente.email}</td>
						<td>${cliente.senha}</td>
						<td>${cliente.cpf}</td>
						<td>${cliente.papel}</td>
						<td>${cliente.telefone}</td>
						<td>${cliente.sexo}</td>
						<td>${cliente.dataNasc}</td>
						<td><a href="/<%= contextPath %>/clientes/edicao?id=${cliente.id}">
								<fmt:message key="clients.update" />
						</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
							href="/<%= contextPath %>/clientes/remocao?id=${cliente.id}"
							onclick="return confirm('<fmt:message key="confirm.link" />');">
								<fmt:message key="clients.delete" />
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</body>
</fmt:bundle>

</html>