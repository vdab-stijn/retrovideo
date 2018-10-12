<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='Basket' />
<body>
	<vdab:menu title='Movies in basket' currentPage='basket' />
	
	<c:url var='url' value='/movie' />
	<form:form id='basketForm' action='${url}' method='get'>
		<table>
			<tr>
				<th>Movie</th>
				<th>Price</th>
				<th></th>
			</tr>
			<c:forEach var='movie' items='movies'>
				
			</c:forEach>
			<tr>
				<td>TOTAL:</td>
				<td><c:out value='${total}' /></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
</body>
</html>