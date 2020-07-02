<%@ page language="java"%>

<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%-- 
<jstl:if test="${picture!=null || model$size > 1}">
	<acme:list>
		<acme:list-column code="sponsor.banner.list.label.picture" path="picture" width="80%"/>
		<acme:list-column code="sponsor.banner.list.label.slogan" path="slogan" width="20%"/>
	</acme:list>
</jstl:if>
--%>
<jstl:choose>
	<jstl:when test="${requestScope['picture']!=null || model$size > 1}">
		<acme:list>
			<acme:list-column code="sponsor.banner.list.label.picture" path="picture" width="80%"/>
			<acme:list-column code="sponsor.banner.list.label.slogan" path="slogan" width="20%"/>
		</acme:list>
	</jstl:when>
	<jstl:otherwise>
		<acme:fake-list>
			<acme:list-column code="sponsor.banner.list.label.picture" path="picture" width="80%"/>
			<acme:list-column code="sponsor.banner.list.label.slogan" path="slogan" width="20%"/>
		</acme:fake-list>
	</jstl:otherwise>
</jstl:choose>
<jstl:choose>
	<jstl:when test="${model$size == 1}">
		<jstl:if test="${requestScope['hasCreditCard'] == true}">
			<acme:redirect-button code="sponsor.banner.button.redirect.create.commercial" action="/sponsor/banner/create-commercial"/>
		</jstl:if>
	</jstl:when>
	<jstl:otherwise>
		<jstl:if test="${requestScope['hasCreditCard[0]'] == true}">
			<acme:redirect-button code="sponsor.banner.button.redirect.create.commercial" action="/sponsor/banner/create-commercial"/>
		</jstl:if>
	</jstl:otherwise>
</jstl:choose>
<acme:redirect-button code="sponsor.banner.button.redirect.create.non-commercial" action="/sponsor/banner/create-non-commercial"/>