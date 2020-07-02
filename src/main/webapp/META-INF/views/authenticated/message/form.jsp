<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="messageThreadId"/>
	
	<acme:form-textbox code="authenticated.message.form.label.title" path="title" />
	<acme:form-textbox code="authenticated.message.form.label.tags" path="tags" />
	<acme:form-textbox code="authenticated.message.form.label.body" path="body" />
	
	<jstl:if test="${command == 'show'}">
	<acme:form-moment code="authenticated.message.form.label.moment" path="moment" />
	<acme:form-textbox code="authenticated.message.form.user.creator.username" path="creator.username" />
	</jstl:if>
	<jstl:if test="${command == 'create'}">
	<acme:form-checkbox code="authenticated.message.form.label.accept" path="accept"/>
 	<acme:form-submit code="authenticated.message.form.button.create" action="/authenticated/message/create"/>
	</jstl:if>
	
	    
	<acme:form-return code="authenticated.message.form.button.return"/>
</acme:form>

