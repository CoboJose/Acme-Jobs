<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="auditor.duty.form.title" path="titleDuty"/>
	<acme:form-textbox code="auditor.duty.form.description" path="descriptionDuty"/>
	<acme:form-integer code="auditor.duty.form.percentage" path="percentage"/>
		
	<acme:form-return code="auditor.duty.form.return"/>
	
</acme:form>