<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="anonymous.investorRecord.list.label.name" path="name"/>
	
	<acme:list-column code="anonymous.investorRecord.list.label.sector" path="sector"/>
	
	<jstl:if test="${command != 'list-fivestars'}">
		<acme:list-column code="anonymous.investorRecord.list.label.stars" path="stars"/>
	</jstl:if>
	
</acme:list>

<jstl:if test="${command != 'list-fivestars'}">
	<acme:redirect-button code="anonymous.investorRecord.listFiveStars.button.redirect" action="/anonymous/investor-record/list-fivestars"/>
</jstl:if>

<jstl:if test="${command != 'list'}">
	<acme:redirect-button code="anonymous.investorRecord.listFiveStars.button.redirect2" action="/anonymous/investor-record/list"/>
</jstl:if>