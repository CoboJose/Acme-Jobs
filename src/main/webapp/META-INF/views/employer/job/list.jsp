<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="employer.job.list.label.reference" path="reference"/>
	<acme:list-column code="employer.job.list.label.deadline" path="deadline"/>
	<acme:list-column code="employer.job.list.label.status" path="status"/>
	<acme:list-column code="employer.job.list.label.title" path="title"/>
</acme:list>

<acme:redirect-button code="employer.job.list.redirect.create-job" action="/employer/job/create"/>