<html style="margin: 0 auto; height: 100%; padding: 0; display: table;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" href="${f:url('/css/jquery-ui.css')}" type="text/css"/>
<link rel="stylesheet" href="${f:url('/css/jquery-ui-ex.css')}" type="text/css"/>
<link rel="stylesheet" href="${f:url('/css/main.css')}" type="text/css"/>

<script type="text/javascript" src="${f:url('/js/jquery.min.js')}"></script>
<script type="text/javascript" src="${f:url('/js/jquery-ui.min.js')}"></script>
<script type="text/javascript">

  $(function () {
    $("input[type=submit]").button();
  });
</script>

</head>
<body style="margin: 0 auto; min-height: 100%; display: table-cell; vertical-align: middle;">

  <bean:define id="login">
    <bean:message key="login"/>
  </bean:define>

  <tags:Dialog/>
  <tags:ValidatorErrorsDialog/>

  <div style="text-align: center;">
    <img border="0" src="${f:url('/css/images/logo.png')}" alt="logo" />
  </div>

  <div style="text-align: center; margin-top: 10px;">
    <span style="font-weight: bold; font-size: 25px; color: #FF00FF;">${login}</span>
  </div>

  <s:form method="POST" style="margin: 0px; margin-top: 10px;">

    <table style="margin-left: auto; margin-right: auto;">
      <tr>
        <td><bean:message key="user.code"/></td>
        <td><html:text property="userCode" style=" width: 150px;" /></td>
      </tr>
      <tr>
        <td><bean:message key="password"/></td>
        <td><html:password property="password" style=" width: 150px;" /></td>
      </tr>
      <tr>
        <td colspan="2" style="text-align: center; height: 50px;">
          <s:submit property="login" value="${login}" />
        </td>
      </tr>
    </table>

    <div style="text-align: center; width: 100%; margin-top: 50px;">
      Copyright (C) 2015 Toshiba Mitsubishi-Electric Industrial Systems Corporation. All Rights Reserved.
    </div>

  </s:form>

</body>
</html>