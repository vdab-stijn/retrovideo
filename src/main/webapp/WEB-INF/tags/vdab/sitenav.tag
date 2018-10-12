<%@ tag description='site navigation' language='java' pageEncoding='UTF-8' %>
<%@ attribute name='currentPage' required='false' type='java.lang.String' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<div class='nav'>
	<a href='/' title='homepage'<c:if test='${empty currentPage}'> class='current'</c:if>>RESERVATIES</a>
	<a href='/movies' title='Movie database'<c:if test='${not empty currentPage and currentPage.equalsIgnoreCase("movies")}'> class='current'</c:if>>Movie database</a>
</div>
