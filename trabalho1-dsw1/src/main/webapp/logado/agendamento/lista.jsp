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
				<fmt:message key="appointments.welcome" />
			</h1>
			<h2>
				<a href="${pageContext.request.contextPath}/profissionais"> 
					<fmt:message key="appointments.create" />
				</a> 
				&nbsp;&nbsp;&nbsp; 
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="exit.link" />
				</a>
			</h2>
			<br />
			<h3><fmt:message key="appointments.list" /></h3>
			<br />
		</div>

		<div align="center">
			<table border="1">
				<caption></caption>
				<tr>
					<th><fmt:message key="appointment.ID" /></th>
					<th><fmt:message key="appointment.date" /></th>
					<th><fmt:message key="appointment.profissional_cpf" /></th>
					<th><fmt:message key="appointment.client_cpf" /></th>
					<th><fmt:message key="appointment.link" /></th>
				</tr>
				<c:forEach var="agendamento" items="${requestScope.listaAgendamentos}">
					<tr>
						<td>${agendamento.id}</td>
						<td>${agendamento.data}</td>
						<td>${agendamento.id_profissional}</td>
						<td>${agendamento.id_cliente}</td>
						<td>${agendamento.linkVideoconferencia}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>