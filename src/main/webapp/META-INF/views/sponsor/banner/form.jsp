<%@ page language="java"%>

<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${creditCardNumber != null || command == 'create-commercial' || command == 'update-commercial'}">
			<acme:message code="sponsor.banner.title.commercial"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:message code="sponsor.banner.title.nonCommercial"/>
		</jstl:otherwise>
	</jstl:choose>

	<acme:form-url code="sponsor.banner.form.label.picture" path="picture"/>
	<acme:form-textbox code="sponsor.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.banner.form.label.target" path="target"/>
	
	<jstl:choose>
		<jstl:when test="${creditCardNumber != null || command == 'create-commercial' || command == 'update-commercial'}">
			<acme:form-panel code="sponsor.banner.form.label.creditCard">
				<acme:form-textbox code="sponsor.banner.form.label.creditCardNumber" path="creditCardNumber"/>
				<acme:form-textbox code="sponsor.banner.form.label.cardHolder" path="cardHolder"/>
				<acme:form-textbox code="sponsor.banner.form.label.cvv" path="cvv"/>
				<acme:form-textbox placeholder="MM/YY" code="sponsor.banner.form.label.expirationDate" path="expirationDate"/>
			</acme:form-panel>
		</jstl:when>
		<jstl:otherwise>
			<acme:form-url code="sponsor.banner.form.label.jingle" path="jingle"/>
		</jstl:otherwise>
	</jstl:choose>
	
	<acme:form-submit test="${command == 'show' && creditCardNumber != null}" 
		code="sponsor.banner.form.button.update" 
		action="/sponsor/banner/update-commercial"/>
	<acme:form-submit test="${command == 'show' && creditCardNumber == null}" 
		code="sponsor.banner.form.button.update" 
		action="/sponsor/banner/update-non-commercial"/>	
	<acme:form-submit test="${command == 'show'}" 
		code="sponsor.banner.form.button.delete" 
		action="/sponsor/banner/delete"/>
		
	<acme:form-submit test="${command == 'create-commercial'}" 
		code="sponsor.banner.form.button.create" 
		action="/sponsor/banner/create-commercial"/>
	<acme:form-submit test="${command == 'create-non-commercial'}" 
		code="sponsor.banner.form.button.create" 
		action="/sponsor/banner/create-non-commercial"/>
		
	<acme:form-submit test="${command == 'update-commercial'}" 
		code="sponsor.banner.form.button.update" 
		action="/sponsor/banner/update-commercial"/>
	<acme:form-submit test="${command == 'update-non-commercial'}" 
		code="sponsor.banner.form.button.update" 
		action="/sponsor/banner/update-non-commercial"/>
		
	<acme:form-submit test="${command == 'delete'}" 
		code="sponsor.banner.form.button.delete" 
		action="/sponsor/banner/delete"/>
		
	<acme:form-return code="sponsor.banner.form.button.return"/>
</acme:form>