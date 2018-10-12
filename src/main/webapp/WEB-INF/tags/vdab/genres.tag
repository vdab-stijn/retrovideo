<%@ tag description='genres' language='java' pageEncoding='UTF-8' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<c:if test='${not empty genres}'>
	<ul class='genres'>
	<c:forEach var='genre' items='${genres}'>
		<spring:url var='url' value='/movies/{id}'>
			<spring:param name='id' value='${genre.id}' />
		</spring:url>
		<li><a href='${url}' title='${genre.name}'>${genre.name}</a></li>
	</c:forEach>
	</ul>
</c:if>