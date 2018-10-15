<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<!DOCTYPE html>
<html>
<vdab:htmlhead title='RetroVideo - reservation' />
<body>
<vdab:menu title='Confirm the reservation' />
<c:if test='${not empty customer}'>
<div id='content'>
	<div class='header1'>Reservation details</div>
	<div>
		<p>Reservation for <em>${customer.getName()}</em></p>
	</div>
	<table class='basket'>
		<tr class='basketHeader'>
			<th class='movieTitle'>MOVIE</th>
			<th class='moviePrice'>PRICE</th>
		</tr>
		<c:choose>
		<c:when test='${not empty movies}'>
		<c:forEach var='movie' items='${movies}'>
			<tr class='basketData'>
				<td class='movieTitle'>${movie.title}</td>
				<td class='moviePrice'>${movie.price}</td>
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
			<td class='moviePrice'><c:out value='${total}' /> &euro;</td>
		</tr>
	</table>
	<form:form id='confirmForm' action='' modelAttribute='reservationConfirmationForm' method='post'>
		<c:forEach var='movie' items='${movies}'>
		</c:forEach>
		<input id='confirmFormSubmit' type='submit' value='Confirm reservation'<c:if test='${empty movies}'> disabled='true'</c:if> />
	</form:form>
</div>
</c:if>
</body>
</html>