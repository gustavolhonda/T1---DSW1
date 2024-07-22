<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ page
isELIgnored="false"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
    <fmt:bundle basename="message">
        <head>
            <title><fmt:message key="page.title" /></title>
        </head>

        <body>
            <% String contextPath = request.getContextPath().replace("/", ""); %>

            <div align="center">
                <h3><fmt:message key="professionals.list" /></h3>

                <a href="${pageContext.request.contextPath}/login.jsp">
                    <fmt:message key="back.link" />
                </a>
                <br />
                <br />

                <table border="1">
                    <tr>
                        <th><fmt:message key="professionals.ID" /></th>
                        <th><fmt:message key="professionals.name" /></th>
                        <th><fmt:message key="professionals.email" /></th>
                        <th><fmt:message key="professionals.speciality" /></th>
                        <th><fmt:message key="actions.link" /></th>
                    </tr>
                    <c:forEach var="profissional" items="${requestScope.listaProfissionais}">
                        <tr>
                            <td><c:out value="${profissional.id}" /></td>
                            <td><c:out value="${profissional.nome}" /></td>
                            <td><c:out value="${profissional.email}" /></td>
                            <td><c:out value="${profissional.especialidade}" /></td>
                            <td>
                                <a
                                    href="/<%= contextPath %>/agendamentos/?id=<c:out value='${profissional.id}' />">
                                    <fmt:message key="professionals.schedule" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
								<br/>
								<a href="${pageContext.request.contextPath}/filtrar.jsp">Filtrar por especialidade</a>
            </div>
        </body>
    </fmt:bundle>
</html>
