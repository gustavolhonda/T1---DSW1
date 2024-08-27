<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${cliente != null}">
				<fmt:message key="clients.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="clients.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${cliente != null}">
		<input type="hidden" name="id" value="${cliente.id}" />
	</c:if>

	<c:if test="${cliente == null}">
	<tr>
		<td><label for="nome"> <fmt:message key="clients.name" />
		</label></td>
		<td><input type="text" id="nome" name="nome" size="45"
			value="${cliente.nome}" /></td>
	</tr>

	<tr>
		<td><label for="email"> <fmt:message key="clients.email" />
		</label></td>
		<td><input type="text" id="email" name="email" size="45"
			value="${cliente.email}" /></td>
	</tr>

	<tr>
		<td><label for="senha"> <fmt:message key="clients.password" />
		</label></td>
		<td><input type="text" id="senha" name="senha" size="45"
			value="${cliente.senha}" /></td>
	</tr>

	<tr>
		<td><label for="cpf"> <fmt:message key="clients.cpf" />
		</label></td>
		<td><input type="text" id="cpf" name="cpf" size="45"
			value="${cliente.cpf}" /></td>
	</tr>
</c:if>

	<tr>
		<td><label for="telefone"> <fmt:message key="clients.telephone" />
		</label></td>
		<td><input type="text" id="telefone" name="telefone" size="45"
			value="${cliente.telefone}" /></td>
	</tr>

	<tr>
		<td><label for="sexo"><fmt:message key="clients.sex" />
		</label></td>
		<td>
			<select name="sexo">
				<option value="M" ${cliente.sexo == "M" ? 'selected="selected"' : ''}>M</option>
				<option value="F" ${cliente.sexo == "F" ? 'selected="selected"' : ''}>F</option>
			</select>			
		</td>
	</tr>

	<tr>
		<td><label for="dataNasc"> <fmt:message key="clients.birthDate" />
		</label></td>
		<td><input type="date" id="dataNasc" name="dataNasc" size="45"
			value="${cliente.dataNasc}" /></td>
	</tr>

	
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>