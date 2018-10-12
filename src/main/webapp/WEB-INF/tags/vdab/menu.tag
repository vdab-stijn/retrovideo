<%@ tag description='menu' language='java' pageEncoding='UTF-8' %>
<%@ attribute name='title' required='true' type='java.lang.String' %>
<%@ attribute name='currentPage' required='false' type='java.lang.String' %>
<%@ attribute name='currentGenre' required='false' type='java.lang.Long' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='vdab' uri='http://vdab.be/tags' %>

<div id='menu'>
	<vdab:sitenav currentPage='${currentPage}' />
	<h1>${title}</h1>
	<vdab:genres currentGenre='${currentGenre}' />
</div>