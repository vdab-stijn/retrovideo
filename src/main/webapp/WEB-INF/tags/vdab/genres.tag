<%@ tag description='genres' language='java' pageEncoding='UTF-8' %>
<%@ attribute name='currentGenre' required='false' type='java.lang.Long' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>

<!-- // BEGIN (MENU: GENRE LIST) ******************************************* -->
<c:if test='${not empty genres}'><div class='genres'><ul><c:forEach var='genre' items='${genres}'><spring:url var='url' value='/{id}'><spring:param name='id' value='${genre.id}' /></spring:url>
	<li><c:choose><c:when test='${empty currentGenre or currentGenre != genre.id}'><a href='${url}' title='${genre.name}'>${genre.name}</a></c:when><c:otherwise><span class='current'>${genre.name}</span></c:otherwise></c:choose></li></c:forEach>
</ul></div></c:if>
<!-- // END (MENU: GENRE LIST) ********************************************* -->