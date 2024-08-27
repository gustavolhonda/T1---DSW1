<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<fmt:bundle basename="message">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="page.title" /></title>
        <link href="./layout.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="login-container">
            <h1><fmt:message key="page.label" /></h1>
            <c:if test="${mensagens.existeErros}">
                <div id="erro">
                    <ul>
                        <c:forEach var="erro" items="${mensagens.erros}">
                            <li> ${erro} </li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>
            
            <br/>
    
            <form method="post" action="index.jsp" class="form-login">
                <form method="post" action="index.jsp">
                    <div class="form-group">
                        <label for="login"><fmt:message key="user.email" /></label>
                        <input type="text" id="login" name="login" value="${param.login}"/>
                    </div>
                    <div class="form-group">
                        <label for="senha"><fmt:message key="user.password" /></label>
                        <input type="password" id="senha" name="senha"/>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn" name="bOK" value="<fmt:message key="user.login"/>">
                    </div>
                </form>
            </form>

            <a href="${pageContext.request.contextPath}/profissionais" class="btn"><fmt:message key="professionals.list" /></a>

        </div>

    </body>
</fmt:bundle>
</html>