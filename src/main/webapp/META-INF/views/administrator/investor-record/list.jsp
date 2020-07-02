<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.investorRecord.list.label.name" path="name" width="20%"/>
	<acme:list-column code="administrator.investorRecord.list.label.sector" path="sector" width="20%"/>
</acme:list>

<acme:redirect-button code="administrator.investor-record.button.redirect.create" action="/administrator/investor-record/create"/>
