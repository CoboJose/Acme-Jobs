<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.company-records.list.label.companyName" path="fullName"/>
	
	<acme:list-column code="authenticated.company-records.list.label.workSector" path="workSector"/>
	
	<acme:list-column code="authenticated.company-records.list.label.webSite" path="webSite"/>
	
	<acme:list-column code="authenticated.company-records.list.label.activityDesc" path="activityDesc"/>
	
	<acme:list-column code="authenticated.company-records.list.label.rating" path="rating"/>
</acme:list>
