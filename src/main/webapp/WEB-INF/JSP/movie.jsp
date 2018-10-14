<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='RetroVideo Movie: ${movie.title}'/>
<body>
	<vdab:menu title='Movie data'/>
	
	<div id='content'>
	<div class='movie'>
		<div class='title'>${movie.title}</div>
		<spring:url var='url' value='/basket/{id}'>
			<spring:param name='id' value='${movie.id}' />
		</spring:url>
		<c:if test='${movie.canBeReserved()}'><a href='${url}' title='Add ${movie.title} to basket'></c:if>
		<img id='movieIMG' src='<c:out value='/posters/${movie.id}.jpg' />' />
		<c:if test='${movie.canBeReserved()}'></a></c:if>
		<div class='row'>
			<span class='rowLabel'>Price:</span>
			<span class='rowData'>${movie.price} &euro;</span>
		</div>
		<div class='row'>
			<span class='rowLabel'>Copies in stock:</span>
			<span class='rowData'>${movie.stock}</span>
		</div>
		<div class='row'>
			<span class='rowLabel'>Reserved copies:</span>
			<span class='rowData'>${movie.reservations}</span>
		</div>
		<div class='row'>
			<span class='rowLabel'>Available copies:</span>
			<span class='rowData'>${movie.available}</span>
		</div>
		<div class='row'>
			<span class='rowHeader'></span>
			<span class='rowData'></span>
		</div>
		<form id='movieBasketForm' action='${url}' method='get'>
			<input id='movieId' type='hidden' value='${movie.id}' />
			<input id='basketSubmit' type='submit' value='Add to basket'<c:if test='${!movie.canBeReserved()}'> disabled='true'</c:if>>
		</form>
		<script>
			document.getElementById('movieIMG')
				.addEventListener('click', function(event) {
				document.getElementById('movieBasketForm').disabled = true;
			});
			
			document.getElementById('movieBasketForm')
				.addEventListener('submit', function(event) {
				document.getElementById('movieId').value = '-1';
				document.getElementById('basketSubmit').disabled = true;
			});
		</script>
	</div>
	</div>
</body>
</html>