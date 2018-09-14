<script type="text/javascript">

$(function() {
  $("#menu").css("display", "");

  setHeaderMenuEvent();
});

function doLogout() {
  dialogUtils.confirm(
      "<bean:message key='info.logout'/>",
      function() {
        $("#mainForm").attr("action", "${f:url('/logout')}");
        $("#mainForm").submit();
      });
}

var setHeaderMenuEvent = function(){
  $('.top_menu__menu li').hover(function(){
      $("ul:not(:animated)", this).slideDown();
  }, function(){
      $("ul.child",this).slideUp();
  });
}
</script>

<div class="top_menu">
  <div class="userinfo" style="margin-top: 5px;">
    ${f:h(userDto.userId)}${f:nbsp("  ")}${f:h(userDto.userName)}
  </div>

  <ul class="top_menu__menu" style = "float:right;">
    <li class="menu menuitem-width-medium">
   	  <tags:AuthorityA link="#" >
        <jsp:attribute name="authority">operation</jsp:attribute>
        <jsp:attribute name="icon">/css/images/menu/regi_plan.png</jsp:attribute>
        <jsp:attribute name="iconVerticalAlign">-3px</jsp:attribute>
        <jsp:attribute name="message"><bean:message key="menu.arrival_shipping"/></jsp:attribute>
      </tags:AuthorityA>
      <ul class="child">
        <li class="menuitem-width-medium">
          <tags:AuthorityA link="/product/arrival" >
            <jsp:attribute name="authority">operation</jsp:attribute>
            <jsp:attribute name="message"><bean:message key="product.arrival"/></jsp:attribute>
          </tags:AuthorityA>
        </li>
        <li class="menuitem-width-medium">
          <tags:AuthorityA link="/product/shipping" >
            <jsp:attribute name="authority">operation</jsp:attribute>
            <jsp:attribute name="message"><bean:message key="product.shipping"/></jsp:attribute>
          </tags:AuthorityA>
        </li>
      </ul>
    </li>
    <li class="menuitem-width-large">
      <tags:AuthorityA link="/product/productPlan" >
        <jsp:attribute name="authority">operation</jsp:attribute>
        <jsp:attribute name="icon">/css/images/menu/regi_plan.png</jsp:attribute>
        <jsp:attribute name="iconVerticalAlign">-3px</jsp:attribute>
        <jsp:attribute name="message"><bean:message key="menu.product.plan"/></jsp:attribute>
      </tags:AuthorityA>
    </li>
    <li class="menuitem-width-medium">
      <tags:AuthorityA link="/product/productResult" >
        <jsp:attribute name="icon">/css/images/menu/plan_result.png</jsp:attribute>
        <jsp:attribute name="iconVerticalAlign">-3px</jsp:attribute>
        <jsp:attribute name="message"><bean:message key="menu.product.result"/></jsp:attribute>
      </tags:AuthorityA>
    </li>
    <li class="menuitem-width-short">
      <tags:AuthorityA link="/product/productProgress" >
        <jsp:attribute name="icon">/css/images/menu/progre_ind.png</jsp:attribute>
        <jsp:attribute name="iconVerticalAlign">-3px</jsp:attribute>
        <jsp:attribute name="message"><bean:message key="menu.progress"/></jsp:attribute>
      </tags:AuthorityA>
    </li>
    <li class="menuitem-width-short">
      <tags:AuthorityA link="/master/material" >
        <jsp:attribute name="icon">/css/images/menu/progre_ind.png</jsp:attribute>
        <jsp:attribute name="iconVerticalAlign">-3px</jsp:attribute>
        <jsp:attribute name="message"><bean:message key="menu.material"/></jsp:attribute>
      </tags:AuthorityA>
    </li>
    <li class="menu">
      <tags:AuthorityA link="/master/systemManage" >
        <jsp:attribute name="authority">operation</jsp:attribute>
        <jsp:attribute name="icon">/css/images/menu/sys_manage.png</jsp:attribute>
        <jsp:attribute name="iconVerticalAlign">-3px</jsp:attribute>
        <jsp:attribute name="message"><bean:message key="menu.system"/></jsp:attribute>
      </tags:AuthorityA>
      <ul class="child menuitem-width-large">
        <li class="menuitem-width-large">
          <tags:AuthorityA link="/master/product" >
            <jsp:attribute name="authority">operation</jsp:attribute>
            <jsp:attribute name="message"><bean:message key="product"/></jsp:attribute>
          </tags:AuthorityA>
        </li>
        <li class="menuitem-width-large">
          <tags:AuthorityA link="/master/material" >
            <jsp:attribute name="authority">operation</jsp:attribute>
            <jsp:attribute name="message"><bean:message key="material"/></jsp:attribute>
          </tags:AuthorityA>
        </li>
        <li class="menuitem-width-large">
          <tags:AuthorityA link="/master/spec" >
            <jsp:attribute name="authority">operation</jsp:attribute>
            <jsp:attribute name="message"><bean:message key="spec"/></jsp:attribute>
          </tags:AuthorityA>
        </li>
        <li class="menuitem-width-large">
          <tags:AuthorityA link="/master/users" >
            <jsp:attribute name="authority">operation</jsp:attribute>
            <jsp:attribute name="message"><bean:message key="user"/></jsp:attribute>
          </tags:AuthorityA>
        </li>
        <li class="menuitem-width-large">
          <tags:AuthorityA link="/master/workCalendar" >
            <jsp:attribute name="authority">operation</jsp:attribute>
            <jsp:attribute name="message"><bean:message key="calendar"/></jsp:attribute>
          </tags:AuthorityA>
        </li>
      </ul>
    </li>
    <li class="menu">
      <a href="#" onclick="doLogout();">
        <span class="menuitem">
          <img src="${f:url('/css/images/menu/logout.png')}" style="height: 20px; vertical-align: -6px;" />
            <bean:message key="logout"/>
        </span>
      </a>
    </li>
  </ul>

  <div style="clear:both;"></div>
</div>

