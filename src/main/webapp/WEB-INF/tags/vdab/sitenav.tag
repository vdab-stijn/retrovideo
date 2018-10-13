<%@ tag description='site navigation' language='java' pageEncoding='UTF-8' %>
<%@ attribute name='currentPage' required='false' type='java.lang.String' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!-- // BEGIN (MENU: SITE NAVIGATION) ************************************** -->
<div class='nav'>
	<a href='/' title='homepage'<c:if test='${empty currentPage}'> class='current'</c:if>>RESERVATIES</a>
	<a href='/basket' title='basket'<c:if test='${currentPage.equalsIgnoreCase("basket")}'> class='current'</c:if>>Basket</a>
	<a href='/customers' title='customers'<c:if test='${currentPage.equalsIgnoreCase("customers")}'> class='current'</c:if>>Customers</a>
	<a href='/movies' title='Movie database' class='<c:choose><c:when test='${currentPage.equalsIgnoreCase("movies")}'>current</c:when><c:otherwise>movieDB</c:otherwise></c:choose>'>Movie database</a>
	<div class='search'>
		<form action='' method='get'>
			<input id='searchMovieDB' type='text' class='movieDB' autofocus />
		</form>
		<img src='/images/search.png' title='Search the movie database' width='22px' />
	</div>
</div>
<!-- // END (MENU: SITE:NAVIGATION) **************************************** -->