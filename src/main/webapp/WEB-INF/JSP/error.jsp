<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='RetroVideo - ERROR' />
<body>
	<vdab:menu title='Hmmm ...' />
	<div id='content' class='error'>
	<div>
		<div class='header2'>Problem description</div>
		<p>An error occurred while processing your request.</p>
		<p>We apologise for the inconvenience. Please try again later.</p>
	</div>
	<div>
		<div class='header2'>Error output</div>
		<c:choose>
		<c:when test='${not empty cause}'>
			<p>
				Caused by: ${cause.getClass().getName()}
			</p>
		</c:when>
		<c:otherwise>
			<p>
				The cause of this error is as of yet undetermined.
			</p>
		</c:otherwise>
		</c:choose>
		<c:if test='${not empty message}'>
			<p>
				${message}
			</p>
		</c:if>
		<c:if test='${not empty trace}'>
			<p>
				<c:forEach var='element' items='${trace}'>
				${element.getClassName()}<br />
				</c:forEach>
			</p>
		</c:if>
	</div>
	<div>
		<div class='header2'>What to do</div>
		<p>
			Our developers have already been notified of the problem. If you
			would like further information or submit a full error report,
			please contact the site administrator.
		</p>
		<p>
			Administrator e-mail:
			<a href='<c:url value='mailto:sverholen@gmail.com' />'>sverholen@gmail.com</a>
		</p>
	</div>
	</div>
</body>
</html>