<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="administrator.company-records.form.label.companyName" path="companyName"/>
	
	<acme:form-textbox code="administrator.company-records.form.label.workSector" path="workSector"/>
	
	<acme:form-textbox code="administrator.company-records.form.label.ceoName" path="ceoName"/>
	
	<acme:form-textarea code="administrator.company-records.form.label.activityDesc" path="activityDesc"/>
	
	<acme:form-url code="administrator.company-records.form.label.webSite" path="webSite"/>
	
	<acme:form-textbox code="administrator.company-records.form.label.contactPhone" path="contactPhone" placeholder="Eg.+111 (1111) 12345678"/>
	
	<acme:form-textbox code="administrator.company-records.form.label.contactEmail" path="contactEmail"/>
	
	<acme:form-checkbox code="administrator.company-records.form.label.incorporated" path="incorporated"/>

	<acme:form-textbox code="administrator.company-records.form.label.rating" placeholder="0...5" path="rating"/>
		
  	<acme:form-submit test="${command == 'show'}" 
		code="administrator.company-records.button.update" 
		action="/administrator/company-records/update"/>
		
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.company-records.button.delete" 
		action="/administrator/company-records/delete"/>
		
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.company-records.button.create" 
		action="/administrator/company-records/create"/>
		
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.company-records.button.update" 
		action="/administrator/company-records/update"/>
		
	<acme:form-submit test="${command == 'delete'}" 
		code="administrator.company-records.button.delete" 
		action="/administrator/company-records/delete"/>
		
  	<acme:form-return code="administrator.company-records.form.button.return"/>
  	
</acme:form>
