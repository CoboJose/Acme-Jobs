<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.auditor.form.label.firm" path="firm" />
	<acme:form-textbox code="authenticated.auditor.form.label.responsibilityStatement" path="responsibilityStatement"/>

	
  	<acme:form-return code="authenticated.auditor.form.button.return"/>
</acme:form>