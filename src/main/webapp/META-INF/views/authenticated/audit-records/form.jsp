<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	
	<acme:message code="authenticated.auditRecord.title"/>
	
	<acme:form-hidden path="jobId"/>	
	
	<acme:form-textbox code="authenticated.job.reference" path="job.reference" readonly="true"/>
	<acme:form-textbox code="authenticated.auditRecord.form.title" path="title"/>
	<acme:form-textbox code="authenticated.auditRecord.form.status" path="status"/>
	<acme:form-moment code="authenticated.auditRecord.form.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="authenticated.auditRecord.form.body" path="body"/>
	
	<acme:message code="authenticated.auditRecord.form.auditor.title"/>
	<acme:form-textbox code="authenticated.auditRecord.form.auditor.username" path="auditor.userAccount.username" readonly="true"/>
	<acme:form-textbox code="authenticated.auditRecord.form.auditor.fullName" path="auditor.userAccount.identity.fullName" readonly="true"/>
	
		
	<acme:form-return code="authenticated.auditRecord.form.return"/>
	
</acme:form>




