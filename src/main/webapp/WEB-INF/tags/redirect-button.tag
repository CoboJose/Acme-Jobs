<%--
- redirect-button.tag
- Hecho por Enrique Reina Gutierrez
--%>

<%@tag language="java" body-content="empty" 
	import="java.util.Collection,java.util.ArrayList,java.util.Map,javax.servlet.jsp.tagext.JspFragment" 
%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="action" required="true" type="java.lang.String"%>
<%@attribute name="access" required="false" type="java.lang.String"%>
<%@attribute name="width" required="false" type="java.lang.String"%>

<jstl:if test="${access == null}">
	<jstl:set var="access" value="true"/>
</jstl:if>

<security:authorize access="${access}">	
	<span onclick="javascript: clearReturnUrl(); redirect('${action}')" class="btn btn-primary my-3">
		<acme:message code="${code}"/> 
	</span>
</security:authorize>