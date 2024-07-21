<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${profissional != null}">
				<fmt:message key="profissionals.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="profissionals.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${profissional != null}">
		<input type="hidden" name="id" value="<c:out value='${profissional.id}' />" />
	</c:if>

	<c:if test="${profissional == null}">
		<tr>
		<td><label for="nome"><fmt:message key="profissionals.name" />
		</label></td>
		<td><input type="text" name="nome" size="45" required
			value="<c:out value='${profissional.nome}' />" /></td>
	</tr>

	<tr>
		<td><label for="email"><fmt:message key="profissionals.email" />
		</label></td>
		<td><input type="text" name="email" size="45" required
			value="<c:out value='${profissional.email}' />" /></td>
	</tr>

	<tr>
		<td><label for="senha"><fmt:message key="profissionals.password" />
		</label></td>
		<td><input type="text" name="senha" size="45" required
			value="<c:out value='${profissional.senha}' />" /></td>
	</tr>

	<tr>
		<td><label for="cpf"><fmt:message key="profissionals.cpf" />
		</label></td>
		<td><input type="text" name="cpf" size="45" required
			value="<c:out value='${profissional.cpf}' />" /></td>
	</tr>
	</c:if>
	
	<tr>
		<td><label for="especialidade"><fmt:message key="profissionals.speciality" />
		</label></td>
		<td><input type="text" name="especialidade" size="45" required
			value="<c:out value='${profissional.especialidade}' />" /></td>
	</tr>
	
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>