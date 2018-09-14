<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="system.manageme"/>
  </bean:define>

  <tiles:put name="title" value="${title}"/>
  <tiles:put name="content" type="string">

    <style type="text/css">

      div.sub_title {
        font-size: 16px;
        margin: 15px 10px;
      }

      .sys_menu_btn {
        width: 145px;
        height: 145px;
        background-color: #6495ed;
        border-bottom: 5px solid rgba(0, 0, 0, 0.2);
        border-top: 5px solid rgba(255, 255, 255, 0.2);
        border-left: 5px solid rgba(255, 255, 255, 0.2);
        border-right: 5px solid rgba(0, 0, 0, 0.2);
        margin-left: 10px;
        padding: 0px;
      }

      .sys_menu_btn div:nth-child(1) {
        width: 120;
        height: 100px;
        background-repeat: no-repeat;
        background-position: 50% 50%;
      }

      .sys_menu_btn div:nth-child(2) {
        font-size: 16px;
        color: #ffffff;
      }
    </style>

    <div class="sub_title">
      <img src="${f:url('/img/square.png')}" style="vertical-align: text-top;">
      <bean:message key="master.manage.product" />
    </div>

    <tags:AuthorityMenuButton link="/master/product">
      <jsp:attribute name="authority">operation</jsp:attribute>
      <jsp:attribute name="icon">/css/images/master/product.png</jsp:attribute>
      <jsp:attribute name="message"><bean:message key="product"/></jsp:attribute>
    </tags:AuthorityMenuButton>

    <tags:AuthorityMenuButton link="/master/material">
      <jsp:attribute name="authority">operation</jsp:attribute>
      <jsp:attribute name="icon">/css/images/master/material.png</jsp:attribute>
      <jsp:attribute name="message"><bean:message key="material"/></jsp:attribute>
    </tags:AuthorityMenuButton>

    <tags:AuthorityMenuButton link="/master/spec">
      <jsp:attribute name="authority">operation</jsp:attribute>
      <jsp:attribute name="icon">/css/images/master/spec.png</jsp:attribute>
      <jsp:attribute name="message"><bean:message key="spec"/></jsp:attribute>
    </tags:AuthorityMenuButton>

    <div style="margin-top: 50px;"></div>

    <div class="sub_title">
      <img src="${f:url('/img/square.png')}" style="vertical-align: text-top;">
      <bean:message key="master.manage.sys" />
    </div>

    <tags:AuthorityMenuButton link="/master/users">
      <jsp:attribute name="authority">operation</jsp:attribute>
      <jsp:attribute name="icon">/css/images/master/user.png</jsp:attribute>
      <jsp:attribute name="message"><bean:message key="user"/></jsp:attribute>
    </tags:AuthorityMenuButton>

    <tags:AuthorityMenuButton link="/master/workCalendar">
      <jsp:attribute name="authority">operation</jsp:attribute>
      <jsp:attribute name="icon">/css/images/master/calendar.png</jsp:attribute>
      <jsp:attribute name="message"><bean:message key="calendar"/></jsp:attribute>
    </tags:AuthorityMenuButton>

    <s:form method="post" styleId="mainForm">

    </s:form>

  </tiles:put>

</tiles:insert>
