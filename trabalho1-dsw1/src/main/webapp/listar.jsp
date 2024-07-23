<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ page
isELIgnored="false"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
    <fmt:bundle basename="message">
        <head>
            <title><fmt:message key="page.title" /></title>
            <link href="./layout.css" rel="stylesheet" type="text/css"/>

        </head>

        <body>
            <% String contextPath = request.getContextPath().replace("/", ""); %>

            <div align="center" class="list-container">
                <h3><fmt:message key="professionals.list" /></h3>

                <form action="profissionais" method="post">
                    <label for="especialidade">Especialidade:</label>
                    <input type="text" id="especialidade" name="especialidade">
                    <button type="submit">Buscar</button>
                </form>    

                <form action="profissionais" method="post"><button type="submit">Ver todos</button></form>

                <table border="1">
                    <tr>
                        <th><fmt:message key="professionals.name" /></th>
                        <th><fmt:message key="professionals.email" /></th>
                        <th><fmt:message key="professionals.speciality" /></th>
                        <c:if test="${usuario != null}">
                            <th><fmt:message key="actions.link" /></th>
                        </c:if>
                    </tr>
                    <c:forEach var="profissional" items="${requestScope.listaProfissionais}">
                        <tr>
                            <td><c:out value="${profissional.nome}" /></td>
                            <td><c:out value="${profissional.email}" /></td>
                            <td><c:out value="${profissional.especialidade}" /></td>
                            <c:if test="${usuario != null}">
                                <td>
                                    <a
                                        href="${pageContext.request.contextPath}/agendamentos/cadastro?id=<c:out value='${profissional.id}' />">
                                        <fmt:message key="professionals.schedule" />
                                    </a>
                                </td>
                            </c:if>

                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${usuario == null}">
                    <a href="${pageContext.request.contextPath}/login.jsp" class="btn">
                        <fmt:message key="back.link" />
                    </a>
                </c:if>

                <c:if test="${usuario != null}">
                    <a href="${pageContext.request.contextPath}/agendamentos" class="btn">
                        <fmt:message key="back.link" />
                    </a>
                </c:if>
            </div>
        </body>
    </fmt:bundle>
</html>
