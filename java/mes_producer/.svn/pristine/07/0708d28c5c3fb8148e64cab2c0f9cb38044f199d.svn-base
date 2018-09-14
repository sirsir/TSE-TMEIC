<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@taglib prefix="f" uri="http://sastruts.seasar.org/functions" %>

<%@ attribute name="authority" type="java.lang.String" required="false"%>
<%@ attribute name="link" type="java.lang.String" required="true"%>
<%@ attribute name="icon" type="java.lang.String" required="false"%>
<%@ attribute name="message" type="java.lang.String" required="true"%>

<c:set var="isAuthority" value="${empty authority ? true : userDto.authority(authority)}"/>

<button class="sys_menu_btn" onclick="location.href='${f:url(link)}'" ${isAuthority ? '':'disabled=\"disabled\"'}>
  <div style="background-image: url(${f:url(icon)})"></div>
  <div>${message}</div>
</button>