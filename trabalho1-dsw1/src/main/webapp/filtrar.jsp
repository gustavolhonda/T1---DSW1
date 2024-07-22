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
		<form action="profissionais" method="post">
            <label for="especialidade">Especialidade:</label>
            <input type="text" id="especialidade" name="especialidade">
            <button type="submit">Buscar</button>
        </form>        
	</body>
</fmt:bundle>

</html>