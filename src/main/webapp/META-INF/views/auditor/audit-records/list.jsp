<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:message code="auditor.auditRecord.title"/>
	<acme:list-column code="auditor.auditRecord.list.title" path="title"/>
	<acme:list-column code="auditor.auditRecord.list.creationMoment" path="creationMoment"/>
	<acme:list-column code="auditor.auditRecord.list.status" path="status"/>
	<acme:list-column code="auditor.auditRecord.list.auditor" path="auditor.userAccount.username"/>
</acme:list>

