<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<!DOCTYPE html>
<html>
<vdab:htmlhead title='RetroVideo - reservation' />
<body>
<vdab:menu title='Confirm the reservation' />
<c:if test='${not empty customer}'>
<div class='content'>
	<div class='header1'>Confirm the reservation</div>
	<c:forEach var='movie' items='${movies}'>
	
	</c:forEach>
	<form:form id='confirmForm' action='' modelAttribute='reservationConfirmationForm' method='post'>
		<form:input path='customerId' type='hidden' value='${customer.id}'></form:input>
		<input id='confirmFormSubmit' type='submit' value='Confirm reservation' />
	</form:form>
</div>
</c:if>
</body>
</html>