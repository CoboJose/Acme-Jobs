<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.request-auditor.list.label.firm" path="firm" width="50%"/>
	<acme:list-column code="administrator.request-auditor.list.label.responsibilityStatement" path="responsibilityStatement" width="50%"/>

</acme:list>