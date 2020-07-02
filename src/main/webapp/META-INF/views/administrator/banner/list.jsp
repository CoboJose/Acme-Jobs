<%@ page language="java"%>

<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.picture" path="picture" width="70%"/>
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan" width="20%"/>
	<acme:list-column code="administrator.banner.list.label.sponsor" path="sponsor.fullName" width="10%"/>
</acme:list>
