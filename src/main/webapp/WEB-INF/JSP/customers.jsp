<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<!DOCTYPE html>
<html>
<vdab:htmlhead title='RetroVideo - Customers' />
<body>
<vdab:menu title='Customers' currentPage='customers' />
<div id='content'>
	<div class='header1'>Search the customer listing</div>
	<c:url var='url' value='/customers' />
	<form:form id='customerSearchForm' action='${url}' modelAttribute='customerSearchForm' method='post'>
		<div class='row'>
		<form:label path='searchString'>Family name contains:</form:label>
		</div>
		<div class='row'>
		<form:input id='searchString' path='searchString' autofocus='autofocus' />
		<form:errors path='searchString' cssClass='formError' />
		</div>
		<div class='row'>
		<input id='formSubmit' type='submit' value='Search' />
		</div>
	</form:form>
	<c:choose>
	<c:when test='${not empty customers}'>
	<table class='basket customers'>
		<tr class='basketHeader'>
			<th>Name</th>
			<th>Street - number</th>
			<th>Postal code</th>
			<th>Municipality</th>
		</tr>
	<c:forEach var='customer' items='${customers}'>
	<spring:url var='url' value='/basket/reservation/{id}'>
		<spring:param name='id' value='${customer.id}' />
	</spring:url>
	<tr class='basketData'>
		<td><a href='${url}'>${customer.getName()}</a></td>
		<td>${customer.streetNumber}</td>
		<td>${customer.postalCode}</td>
		<td>${customer.municipality}</td>
	</tr>
	</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
	<c:if test='${not empty searchString}'>
		<c:url var='url' value='/customers/list' />
		<p>No customers found for search string '${searchString}'</p>
		<p>
			Please enter a different search string or
			<a href='${url}' title='Select customers from a list'>select</a>
			the customer from the listing.
		</p>
	</c:if>
	</c:otherwise>
	</c:choose>
</div>
</body>
</html>