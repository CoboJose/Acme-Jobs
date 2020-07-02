<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="employer.duty.form.title" path="titleDuty"/>
	<acme:form-textbox code="employer.duty.form.description" path="descriptionDuty"/>
	<acme:form-integer code="employer.duty.form.percentage" path="percentage" placeholder="0 - 100"/>
	
	<acme:form-hidden path="jobId"/>
	
	<acme:form-submit test="${command == 'create'}" 
	    code="employer.duty.form.button.create" action="/employer/duty/create"/>	
	    
	<acme:form-submit test="${(command == 'show' || command == 'update') && (descriptor.job.status == 'DRAFT' || jobStatus == 'DRAFT')}" 
		code="employer.duty.form.button.update" action="/employer/duty/update"/>
			
	<acme:form-submit test="${(command == 'show' || command == 'update') && (descriptor.job.status == 'DRAFT' || jobStatus == 'DRAFT')}" 
		code="employer.duty.form.button.delete" action="/employer/duty/delete"/>	
		    
	<acme:form-return code="employer.duty.form.return"/>
	
</acme:form>




