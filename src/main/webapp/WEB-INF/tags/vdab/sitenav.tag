<%@ tag description='site navigation' language='java' pageEncoding='UTF-8' %>
<%@ attribute name='currentPage' required='false' type='java.lang.String' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<div class='nav'>
	<a href='/' title='homepage'<c:if test='${empty currentPage}'> class='current'</c:if>>RESERVATIES</a>
	<a href='/basket' title='basket'<c:if test='${currentPage.equalsIgnoreCase("basket")}'> class='current'</c:if>>Basket</a>
	<a href='/customers' title='customers'<c:if test='${currentPage.equalsIgnoreCase("customers")}'> class='current'</c:if>>Customers</a>
	<a href='/movies' title='Movie database'<c:if test='${currentPage.equalsIgnoreCase("movies")}'> class='current'</c:if>>Movie database</a>
	<div class='search'>
		<form>
			<input id='search' type='text' />
		</form>
		<img src='/images/search.png' title='Search the movie database' width='22px' />
	</div>
</div>