<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.challenge.form.label.title" path="title"/>
	<acme:form-moment code="administrator.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.challenge.form.label.description" path="description"/>
	<acme:form-panel code="administrator.challenge.form.label.goldLevel">
		<acme:form-textbox code="administrator.challenge.form.label.goal" path="goldGoal"/>
		<acme:form-money code="administrator.challenge.form.label.reward" path="goldReward"/>
	</acme:form-panel>
	<acme:form-panel code="administrator.challenge.form.label.silverLevel">
		<acme:form-textbox code="administrator.challenge.form.label.goal" path="silverGoal"/>
		<acme:form-money code="administrator.challenge.form.label.reward" path="silverReward"/>
	</acme:form-panel>
	<acme:form-panel code="administrator.challenge.form.label.bronzeLevel">
		<acme:form-textbox code="administrator.challenge.form.label.goal" path="bronzeGoal"/>
		<acme:form-money code="administrator.challenge.form.label.reward" path="bronzeReward"/>
	</acme:form-panel>
	
	
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.challenge.form.button.update" 
		action="/administrator/challenge/update"/>
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.challenge.form.button.delete" 
		action="/administrator/challenge/delete"/>
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.challenge.form.button.create" 
		action="/administrator/challenge/create"/>
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.challenge.form.button.update" 
		action="/administrator/challenge/update"/>
	<acme:form-submit test="${command == 'delete'}" 
		code="administrator.challenge.form.button.delete" 
		action="/administrator/challenge/delete"/>

  	<acme:form-return code="administrator.challenge.form.button.return"/>
</acme:form>
