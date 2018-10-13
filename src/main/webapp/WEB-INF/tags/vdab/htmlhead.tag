<%@ tag language='java' pageEncoding='UTF-8' %>
<%@ attribute name='title' required='true' type='java.lang.String' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<head>
	<meta charset='UTF-8' />
	<meta name='viewport' content='width=device-width,initial-scale=1' />
	
	<title>${title}</title>
	
	<link rel='icon' href='<c:url value="/images/retrovideo.ico" />' type='image/x-icon' />
	
	<link rel='stylesheet' href='<c:url value="/style/reset.css" />' type='text/css' />
	<link rel='stylesheet' href='<c:url value="/style/set.css" />' type='text/css' />
	<link rel='stylesheet' href='<c:url value="/style/retrovideo.css" />' type='text/css' />
	
	<script type='text/javascript' src='js/retrovideo.js'></script>
</head>