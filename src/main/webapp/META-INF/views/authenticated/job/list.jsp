<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.job.list.label.reference" path="reference"/>
	<acme:list-column code="authenticated.job.list.label.deadline" path="deadline"/>
	<acme:list-column code="authenticated.job.list.label.title" path="title"/>
	<acme:list-column code="authenticated.job.list.label.employer" path="employer.userAccount.username"/>
</acme:list>