<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="messageThreadId"/>	
	<acme:form-textbox code="authenticated.involved.form.label.userAccount.username" path="userAccount.username" readonly="${command == 'show'}"/>
	
	<jstl:if test="${command == 'create'}">
	<acme:form-submit code="authenticated.involved.form.button.create" action="/authenticated/involved/create?messageThreadId=${messageThreadId}"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
	<acme:form-submit code="authenticated.involved.form.button.delete" action="/authenticated/involved/delete"/>
	</jstl:if>
	
	<acme:form-return code="authenticated.involved.form.button.return"/>	
</acme:form>
