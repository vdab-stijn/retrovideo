<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
<vdab:htmlhead title='RetroVideo - Reservation status' />
<body>
<vdab:menu title='Reservation status' />
<div id='content'>
<div class='header1'>Report</div>
<c:choose>
<c:when test='${not empty success and success}'>
<p>The movies were reserved</p>
</c:when>
<c:otherwise>
<p>The movies could not be reserved !</p>

<c:if test='${not empty successfullyReserved}'>
<p>The following movies were <em>successfully</em> reserved:</p>
<ul>
<c:forEach var='movie' items='${successfullyReserved}'>
<li>${movie.title}</li>
</c:forEach>
</ul>
</c:if>
<c:if test='${not empty failIntermittentlyReserved}'>
<p>
	The following movies were reserved by someone else while you were processing
	the current reservation:
</p>
<ul>
<c:forEach var='movie' items='${failIntermittentlyReserved}'>
<li>${movie.title}</li>
</c:forEach>
</ul>
</c:if>
<c:if test='${not empty failAlreadyReserved}'>
<p>
	The following movies were already reserved for the customer account !
</p>
<ul>
<c:forEach var='movie' items='${failAlreadyReserved}'>
<li>${movie.title}</li>
</c:forEach>
</ul>
</c:if>
<c:if test='${not empty failStockUpdate}'>
<p>The following movies could not be updated in the database:</p>
<ul>
<c:forEach var='movie' items='${failStockUpdate}'>
<li>${movie.title}</li>
</c:forEach>
</ul>
</c:if>
<c:if test='${not empty failReservation}'>
<p>The following reservations could not be created in the database:</p>
<ul>
<c:forEach var='movie' items='${failReservation}'>
<li>${movie.title}</li>
</c:forEach>
</ul>
</c:if>
</c:otherwise>
</c:choose>
</div>
</body>
</html>