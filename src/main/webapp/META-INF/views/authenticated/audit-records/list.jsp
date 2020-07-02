<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:message code="authenticated.auditRecord.title"/>
	<acme:list-column code="authenticated.auditRecord.list.title" path="title"/>
	<acme:list-column code="authenticated.auditRecord.list.creationMoment" path="creationMoment"/>
	<acme:list-column code="authenticated.auditRecord.list.auditor" path="auditor.userAccount.username"/>
</acme:list>
