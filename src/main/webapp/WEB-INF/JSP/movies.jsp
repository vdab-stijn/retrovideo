<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='Movies' />
<body>
	<vdab:menu title='Movie database' currentPage='movies' currentGenre='${currentGenre}' />
	
	<c:if test='${not empty movies}'>
	<div>
		<c:if test='${not empty genre}'>
			<h1>${genre.name.toUpperCase()}</h1>
		</c:if>
		<c:forEach var='movie' items='${movies}'>
			<spring:url var='url' value='/posters/{id}.jpg'>
				<spring:param name='id' value='${movie.id}' />
			</spring:url>
			<div class="movie">
				<div class="poster">
					<img src='${url}' title='${movie.title}' />
				</div>
			</div>
		</c:forEach>
	</div>
	</c:if>
</body>
</html>