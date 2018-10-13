<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='Film: ${movie.title}'/>
<body>
	<vdab:menu title='${movie.title}'/>
	
	<div class="movie">
		<img src='<c:out value='/posters/${movie.id}.jpg' />' />
		<dl>
			<dt>Price:</dt>
			<dd>${movie.price}</dd>
			
			<dt>Stock</dt>
			<dd>${movie.stock}</dd>
			
			<dt>Reservations</dt>
			<dd>${movie.reservations}</dd>
			
			<dt>Available</dt>
			<dd>${movie.available}</dd>
		</dl>
		<c:url var='url' value='/basket' />
		<form:form id='movieBasketForm' action='${url}' method='get'>
			<form:input id='movieId' path='movieId' type='hidden' value='${movie.id}' />
			<input id='basketSubmit' type='submit' value='Add to basket' />
		</form:form>
		<script>
			document.getElementById('movieBasketForm').onsubmit = function() {
				document.getElementById('movieId').value = '-1';
				document.getElementById('basketSubmit').disabled = true;
			};
		</script>
	</div>
</body>
</html>