<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.company-records.list.label.companyName" path="fullName"/>
	
	<acme:list-column code="anonymous.company-records.list.label.workSector" path="workSector"/>
	
	<acme:list-column code="anonymous.company-records.list.label.webSite" path="webSite"/>
	
	<acme:list-column code="anonymous.company-records.list.label.activityDesc" path="activityDesc"/>
	
	<jstl:if test="${command != 'list-fivestars'}">
		<acme:list-column code="anonymous.company-records.list.label.rating" path="rating"/>
	</jstl:if>
	
</acme:list>

<jstl:if test="${command != 'list-fivestars'}">
	<acme:redirect-button code="anonymous.company-records.button.redirect" action="/anonymous/company-records/list-fivestars"/>
</jstl:if>

<jstl:if test="${command != 'list'}">
	<acme:redirect-button code="anonymous.company-records.button.redirect2" action="/anonymous/company-records/list"/>
</jstl:if>