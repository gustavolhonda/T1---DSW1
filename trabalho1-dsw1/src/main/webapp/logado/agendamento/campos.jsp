<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1" style="width: 400px; border: 1px solid black">

	<tr>
		<td><label for="data"> <fmt:message key="appointment.date" />
		</label></td>
		<td><input type="date" id="data" name="data" size="45"/></td>
	</tr>

	<tr>
		<td><label for="hora"> <fmt:message key="appointment.time" />
		</label></td>
		<td><input type="time" id="hora" name="hora" size="45"/></td>
	</tr>

</table>
<br/>
<br/>

<tr>
	<td colspan="2" align="center"><input type="submit"
		value="<fmt:message key="save.link" />" /></td>
</tr>