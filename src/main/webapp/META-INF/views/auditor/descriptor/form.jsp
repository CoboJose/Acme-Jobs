<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="auditor.descriptor.form.description" path="description"/>
		
	<acme:form-return code="auditor.descriptor.form.return"/>
	
</acme:form>
<acme:redirect-button code="auditor.descriptor.button.duty" action="/auditor/duty/list?id=${id}"/>