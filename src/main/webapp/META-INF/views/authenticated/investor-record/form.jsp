<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.investorRecord.form.label.name" path="name"/>
	<acme:form-textbox code="authenticated.investorRecord.form.label.sector" path="sector"/>
	<acme:form-textarea code="authenticated.investorRecord.form.label.statement" path="statement"/>
	<acme:form-integer code="authenticated.investorRecord.form.label.stars" path="stars"/>
	<acme:form-return code="authenticated.investorRecord.form.button.return"/>
</acme:form>