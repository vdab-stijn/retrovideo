<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='RetroVideo - Basket' />
<body>
	<vdab:menu title='Movies in basket' currentPage='basket' />
	
	<div id='content'>
	<c:url var='url' value='/basket' />
	<form:form id='basketForm' modelAttribute='movieBasketForm' action='${url}' method='post'>
		<table class='basket'>
			<tr class='basketHeader'>
				<th class='movieTitle'>MOVIE</th>
				<th class='moviePrice'>PRICE</th>
				<th class='movieAction'>
					<input type='submit' value='Remove' id='' />
				</th>
			</tr>
			<c:choose>
			<c:when test='${not empty movies}'>
			<c:forEach var='movie' items='${movies}'>
				<tr class='basketData'>
					<td class='movieTitle'>${movie.title}</td>
					<td class='moviePrice'>${movie.price}</td>
					<td class='movieAction'>
						<form:checkbox path='movieId' value='${movie.id}' />
					</td>
				</tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<c:url var='url' value='/' />
				<tr class='basketData'>
					<td class='movieTitle' colspan='3'>
						The basket is empty.
						Please <a href='${url}'>select</a> some movies.
					</td>
				</tr>
			</c:otherwise>
			</c:choose>
			<tr class='basketFooter'>
				<td class='movieTitle'>TOTAL:</td>
				<td class='moviePrice' colspan='2'><c:out value='${total}' /> &euro;</td>
			</tr>
			<c:if test='${not empty customer}'>
			<c:url var='url' value='/customers/list' />
			<spring:url var='confirm' value='basket/reservation/{id}'>
				<spring:param name='id' value='${customer.id}' />
			</spring:url>
			<tr>
				<td colspan='3'>
					<span>Currently reserved for ${customer.getName()}</span>
					<span>(<a href='${url}'>change customer</a> or</span>
					<span><a href='${confirm}'>confirm reservation</a>)</span>
				</td>
			</tr>
			</c:if>
		</table>
	</form:form>
	<script>
		document.getElementById('basketForm').
	</script>
	</div>
</body>
</html>