<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='RetroVideo' />
<body>
	<vdab:menu title='RetroVideo reservatie-systeem' />
	<c:if test='${not empty movies}'>
		<div class="movies">
		<c:forEach var='movie' items='${movies}'>
			<spring:url var='url' value='/movie/{id}'>
				<spring:param name='id' value='${movie.id}' />
			</spring:url>
			<spring:url var='movieImage' value='/${id}.jpg' />
			<img src='${movieImage}' title='${movie.titel}' />
		</c:forEach>
		</div>
	</c:if>
</body>
</html>