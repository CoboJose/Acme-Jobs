<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox readonly="true" code="employer.application.form.reference" path="reference"/>
	<acme:form-moment readonly="true" code="employer.application.form.moment" path="moment"/>
	<acme:form-textbox readonly="true" code="employer.application.form.status" path="status"/>
	<acme:form-textarea readonly="true" code="employer.application.form.statement" path="statement"/>
	<acme:form-textarea readonly="true" code="employer.application.form.skills" path="skills"/>
	<acme:form-textarea readonly="true" code="employer.application.form.qualifications" path="qualifications"/>
	<acme:form-panel code="employer.application.form.worker">
		<acme:form-textbox readonly="true" code="employer.application.form.worker.name" path="worker.userAccount.identity.name"/>
		<acme:form-textbox readonly="true" code="employer.application.form.worker.surname" path="worker.userAccount.identity.surname"/>
	</acme:form-panel>
	<acme:form-panel code="employer.application.form.job">
		<acme:form-textbox readonly="true" code="employer.application.form.job.reference" path="job.reference"/>
		<acme:form-textbox readonly="true" code="employer.application.form.job.title" path="job.title"/>
	</acme:form-panel>
	<acme:form-panel code="employer.application.form.resolution">
			<jstl:if test="${status != 'PENDING'}">
				<acme:form-moment readonly="true" code="employer.application.form.resolutionMoment" path="resolutionMoment"/>
			</jstl:if>
			<acme:form-textarea readonly="${status != 'PENDING'}" code="employer.application.form.resolutionJustification" path="resolutionJustification"/>
	</acme:form-panel>
	
	<acme:form-submit test="${(command == 'show' || command == 'reject-application') && (status == 'PENDING' || appStatus == 'PENDING')}" 
		code="employer.applicaton.form.button.accept" 
		action="/employer/application/accept-application"/>
	<acme:form-submit test="${(command == 'show' || command == 'reject-application') && (status == 'PENDING' || appStatus == 'PENDING')}" 
		code="employer.applicaton.form.button.reject" 
		action="/employer/application/reject-application"/>
	
	<acme:form-return code="employer.application.form.return"/>	
</acme:form>