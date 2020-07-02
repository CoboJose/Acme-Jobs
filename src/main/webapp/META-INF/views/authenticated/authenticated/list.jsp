<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.user-account.list.label.id" path="id" width="20%"/>
	<acme:list-column code="authenticated.user-account.list.label.username" path="username" width="40%"/>
	
</acme:list>
