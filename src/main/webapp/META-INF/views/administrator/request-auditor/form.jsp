<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="administrator.request-auditor.form.label.firm" path="firm"/>
	<acme:form-textbox code="administrator.request-auditor.form.label.responsibilityStatement" path="responsibilityStatement"/>

</acme:form>
	<acme:form>
		<acme:form-hidden path="firm"/>
		<acme:form-hidden path="responsibilityStatement"/>

		
		<acme:form-submit test="${command == 'show'}" 
			code="administrator.request-auditor.form.button.accept" 
			action="/administrator/request-auditor/create"/>
		
		<acme:form-submit test="${command == 'show'}" 
			code="administrator.request-auditor.form.button.delete" 
			action="/administrator/request-auditor/delete"/>
		<acme:form-submit test="${command == 'delete'}" 
			code="administrator.request-auditor.form.button.delete" 
			action="/administrator/request-auditor/delete"/>
		<acme:form-return code="administrator.request-auditor.form.button.return"/>	
	</acme:form>
