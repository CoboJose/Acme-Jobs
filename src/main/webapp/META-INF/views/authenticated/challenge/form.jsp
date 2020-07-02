<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description"/>
	<acme:form-panel code="authenticated.challenge.form.label.goldLevel">
		<acme:form-textbox code="authenticated.challenge.form.label.goal" path="goldGoal"/>
		<acme:form-money code="authenticated.challenge.form.label.reward" path="goldReward"/>
	</acme:form-panel>
	<acme:form-panel code="authenticated.challenge.form.label.silverLevel">
		<acme:form-textbox code="authenticated.challenge.form.label.goal" path="silverGoal"/>
		<acme:form-money code="authenticated.challenge.form.label.reward" path="silverReward"/>
	</acme:form-panel>
	<acme:form-panel code="authenticated.challenge.form.label.bronzeLevel">
		<acme:form-textbox code="authenticated.challenge.form.label.goal" path="bronzeGoal"/>
		<acme:form-money code="authenticated.challenge.form.label.reward" path="bronzeReward"/>
	</acme:form-panel>
	
  	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>
