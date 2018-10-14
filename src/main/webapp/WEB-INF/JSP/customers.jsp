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
	<c:if test='${not empty customers}'>
	<table class='customers'>
		<tr>
			<th>Name</th>
			<th>Street - number</th>
			<th>Postal code</th>
			<th>Municipality</th>
		</tr>
	<c:forEach var='customer' items='${customers}'>
	<spring:url var='url' value='/reservation/{id}'>
		<spring:param name='id' value='${customer.id}' />
	</spring:url>
	<tr>
		<td><a href='${url}'>${customer.getName()}</a></td>
		<td>${customer.streetNumber}</td>
		<td>${customer.postalCode}</td>
		<td>${customer.municipality}</td>
	</tr>
	</c:forEach>
	</table>
	</c:if>
</div>
</body>
</html>