<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:message code="auditor.auditRecord.title"/>
	
	<acme:form-hidden path="jobId"/>	
	
	<acme:form-textbox code="auditor.job.reference" path="job.reference" readonly="true"/>
	
	<jstl:if test="${command == 'show' || command == 'update'}">
		<acme:form-moment code="auditor.auditRecord.form.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show' && status == 'PUBLISHED'}">		
		<acme:form-textbox code="auditor.auditRecord.form.title" path="title" readonly="true"/>
		<acme:form-textbox code="auditor.auditRecord.form.body" path="body" readonly="true"/>	
		<acme:form-textbox code="auditor.auditRecord.form.status" path="status" readonly="true"/>
	</jstl:if>	
	
	
	<jstl:if test="${command=='create' || command == 'show' && status=='DRAFT' || command == 'update'}">
		<acme:form-textbox code="auditor.auditRecord.form.title" path="title"/>
		<acme:form-textbox code="auditor.auditRecord.form.body" path="body"/>	
		<acme:form-select code="auditor.auditRecord.form.status" path="status">
			<acme:form-option code="auditor.auditRecord.form.status.draft" value="DRAFT"/>
			<acme:form-option code="auditor.auditRecord.form.status.published" value="PUBLISHED"/>
		</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${command == 'show' || command == 'update'}">
			
		<acme:message code="auditor.auditRecord.form.auditor.title"/>
		<acme:form-textbox code="auditor.auditRecord.form.auditor.username" path="auditor.userAccount.username" readonly="true"/>
		<acme:form-textbox code="auditor.auditRecord.form.auditor.fullName" path="auditor.userAccount.identity.fullName" readonly="true"/>
	
	</jstl:if>
	
	
	<acme:form-submit test="${command == 'create'}" code="auditor.auditRecord.create" action="/auditor/audit-records/create?jobId=${jobId}"/>
	<acme:form-submit test="${command == 'show' && status == 'DRAFT' || command == 'update'}" code="auditor.auditRecord.update" action="/auditor/audit-records/update?id=${id}"/>
	<acme:form-submit test="${command == 'show' && auditorId == accountId}" code="auditor.auditRecord.delete" action="/auditor/audit-records/delete?id=${id}"/>
	<acme:form-return code="auditor.auditRecord.form.return"/>
	
</acme:form>




