<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="employer.application.list.label.reference" path="reference" width="20%"/>
	<acme:list-column code="employer.application.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="employer.application.list.label.status" path="status" width="20%"/>
	<acme:list-column code="employer.application.list.label.worker" path="worker.fullName" width="20%"/>
	<acme:list-column code="employer.application.list.label.job.reference" path="job.reference" width="20%"/>
</acme:list>