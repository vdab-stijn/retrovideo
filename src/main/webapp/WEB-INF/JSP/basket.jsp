<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='Basket' />
<body>
	<vdab:menu title='Movies in basket' currentPage='basket' />
	
	<c:url var='url' value='/basket' />
	<form:form id='basketForm' action='${url}' method='post'>
		<table class="basket">
			<tr>
				<th>Movie</th>
				<th>Price</th>
				<th></th>
			</tr>
			<c:forEach var='movie' items='${movies}'>
				<tr>
					<td>${movie.title}</td>
					<td>${movie.price}</td>
					<td></td>
				</tr>
			</c:forEach>
			<tr>
				<td>TOTAL:</td>
				<td><c:out value='${total}' /> &euro;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
</body>
</html>