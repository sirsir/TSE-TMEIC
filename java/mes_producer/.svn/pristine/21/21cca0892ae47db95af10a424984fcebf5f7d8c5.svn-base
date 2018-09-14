<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@taglib prefix="f" uri="http://sastruts.seasar.org/functions" %>

<%@ attribute name="authority" type="java.lang.String" required="false"%>
<%@ attribute name="link" type="java.lang.String" required="true"%>
<%@ attribute name="icon" type="java.lang.String" required="false"%>
<%@ attribute name="iconHeight" type="java.lang.String" required="false"%>
<%@ attribute name="iconVerticalAlign" type="java.lang.String" required="false"%>
<%@ attribute name="message" type="java.lang.String" required="true"%>

<c:set var="isAuthority" value="${empty authority ? true : userDto.authority(authority)}"/>
<c:set var="iconHeight" value="${empty iconHeight ? '17px' : iconHeight}"/>
<c:set var="iconVerticalAlign" value="${empty iconVerticalAlign ? '0px' : iconVerticalAlign}"/>

<a class="${isAuthority ? '' : 'events_disabled'}" href="${isAuthority ? f:url(link) : '#'}" onclick="return ${isAuthority};">
  <span class="menuitem">
    <c:if test="${!empty icon}">
      <img src="${f:url(icon)}" style="height: ${iconHeight}; vertical-align: ${iconVerticalAlign};" />
    </c:if>
    ${message}
  </span>
</a>