<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='RetroVideo' />
<body>
	<vdab:menu title='RetroVideo reservatie-systeem' currentPage='' currentGenre='${currentGenre}' />
	
<!-- *********************************************************************** -->
<!-- BEGIN (CONTENT)                                                         -->
<!-- *********************************************************************** -->
<div id='content'>
<c:if test='${not empty movies}'>
<div>
	<c:if test='${not empty genre}'>
		<div class='header2'>${genre.name.toUpperCase()}</div>
	</c:if>
	<c:forEach var='movie' items='${movies}'>
		<spring:url var='imageURL' value='/posters/{id}.jpg'>
			<spring:param name='id' value='${movie.id}' />
		</spring:url>
		<spring:url var='movieURL' value='/movie/{id}'>
			<spring:param name='id' value='${movie.id}' />
		</spring:url>
	<div class="movie">
		<!-- <div class="title">${movie.title}</div> -->
		<div class="poster">
			<a href='${movieURL}'>
			<img src='${imageURL}' title='${movie.title} (RESERVATIE <c:if test='${not movie.canBeReserved()}'>NIET </c:if>MOGELIJK)' />
			</a>
		</div>
	</div>
	</c:forEach>
</div>
</c:if>
</div>
<!-- *********************************************************************** -->
<!-- END (CONTENT)                                                           -->
<!-- *********************************************************************** -->
</body>
</html>