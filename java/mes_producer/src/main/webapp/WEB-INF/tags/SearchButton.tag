<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@taglib prefix="f" uri="http://sastruts.seasar.org/functions" %>

<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="onclick" type="java.lang.String" required="false"%>

<button type="button" id="${id}" onclick="${onclick}" style="width: 40px;">
  <img src="${f:url('/css/images/update.png')}">
</button>