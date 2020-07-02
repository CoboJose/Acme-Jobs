<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${status == 'PUBLISHED'}">
		<acme:message code="auditor.auditRecord.title"/>
		
		<acme:form-hidden path="jobId"/>	
		
		<acme:form-textbox code="auditor.job.reference" path="job.reference" readonly="true"/>
		<acme:form-moment code="auditor.auditRecord.form.creationMoment" path="creationMoment" readonly="true"/>	
		<acme:form-textbox code="auditor.auditRecord.form.title" path="title" readonly="true"/>
		<acme:form-textbox code="auditor.auditRecord.form.body" path="body" readonly="true"/>	
		<acme:form-textbox code="auditor.auditRecord.form.status" path="status" readonly="true"/>
	
		<acme:message code="auditor.auditRecord.form.auditor.title"/>
		<acme:form-textbox code="auditor.auditRecord.form.auditor.username" path="auditor.userAccount.username" readonly="true"/>
		<acme:form-textbox code="auditor.auditRecord.form.auditor.fullName" path="auditor.userAccount.identity.fullName" readonly="true"/>
			
		<acme:form-return code="auditor.auditRecord.form.return"/>
	</jstl:if>
</acme:form>




