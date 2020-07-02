<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="authenticated.duty.form.title" path="titleDuty"/>
	<acme:form-textbox code="authenticated.duty.form.description" path="descriptionDuty"/>
	<acme:form-integer code="authenticated.duty.form.percentage" path="percentage"/>
		
	<acme:form-return code="authenticated.duty.form.return"/>
	
</acme:form>




