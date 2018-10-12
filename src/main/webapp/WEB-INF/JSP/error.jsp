<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html>
	<vdab:htmlhead title='RetroVideo' />
<body>
	<vdab:menu title='Hmmm ...' />
	<div class="error">
	<div>
		<h2>Problem description</h2>
		<p>An error occurred while processing your request.</p>
		<p>We apologise for the inconvenience. Please try again later.</p>
	</div>
	<div>
		<h2>Error output</h2>
		<c:if test='${not empty cause}'>
			<p>
				${cause.toString}
			</p>
		<c:otherwise>
			<p>
				The cause of this error is as of yet undetermined.
			</p>
		</c:otherwise>
		</c:if>
		<c:if test='${not empty message}'>
			<p>
				${message}
			</p>
		</c:if>
		<c:if test='${not empty trace}'>
			<p>
				<c:forEach var='element' items='${trace}'>
				${element.toString}<br />
				</c:forEach>
			</p>
		</c:if>
	</div>
	<div>
		<h2>What to do</h2>
		<p>
			Our developers have already been notified of the problem. If you
			would like further information or submit a full error report,
			please contact the site administrator.
		</p>
		<p>
			Administrator e-mail:
			<a href='<c:url value="mailto:sverholen@gmail.com" />'>sverholen@gmail.com</a>
		</p>
	</div>
	</div>
</body>
</html>