<%@ tag description='menu' language='java' pageEncoding='UTF-8' %>
<%@ attribute name='title' required='true' type='java.lang.String' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>

<div id='menu'>
	<vdab:sitenav />
	<h1>${title}</h1>
	<vdab:genres />
</div>