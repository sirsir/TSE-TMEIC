<!DOCTYPE html>
<html>
<head>
<title><tiles:getAsString name="title" /></title>

<link rel="stylesheet" href="${f:url('/css/jquery-ui.css')}" type="text/css"/>
<link rel="stylesheet" href="${f:url('/css/jquery-ui-timepicker-addon.min.css')}" type="text/css" />
<link rel="stylesheet" href="${f:url('/css/jqGrid/ui.jqgrid.css')}" type="text/css" />
<link rel="stylesheet" href="${f:url('/css/jqGrid/ui.multiselect.css')}" type="text/css" />
<link rel="stylesheet" href="${f:url('/css/main.css')}" type="text/css"/>
<link rel="stylesheet" href="${f:url('/css/menu.css')}" type="text/css"/>

<script type="text/javascript" src="${f:url('/js/jquery.min.js')}"></script>
<script type="text/javascript" src="${f:url('/js/jquery-ui.min.js')}"></script>
<script type="text/javascript" src="${f:url('/js/jquery-ui-timepicker-addon.min.js')}"></script>
<script type="text/javascript" src="${f:url('/js/jqGrid/i18n/grid.locale-' += pageContext.request.locale.language += '.js')}"></script>
<script type="text/javascript" src="${f:url('/js/jqGrid/jquery.jqGrid.min.js')}"></script>
<script type="text/javascript" src="${f:url('/js/json2.js')}"></script>
<script type="text/javascript" src="${f:url('/js/jquery.ba-throttle-debounce.js')}"></script>
<script type="text/javascript" src="${f:url('/js/jqGridUtils.js')}"></script>
<script type="text/javascript" src="${f:url('/js/main.js')}"></script>

<link rel="shortcut icon" href="${f:url('/img/favicon.ico')}" type="image/vnd.microsoft.icon">
<link rel="icon" href="${f:url('/img/favicon.ico')}" type="image/vnd.microsoft.icon">

<script type="text/javascript">

$(function() {
  $("input[type=submit], input[type=button]").button();
});
</script>
</head>
<body style="margin: 0px;">

<tags:Dialog/>

<tiles:insert page="header.jsp" />

<table style="width: 100%; border-collapse: collapse; margin-top: 5px;">
  <tr>
    <td style="padding: 0px 10px;">
      <div style="display: table; width: 100%;">
        <div style="display: table-cell;">
          <span style="font-weight: bold; font-size: 25px; color: #F2687D;">
            <tiles:getAsString name="title" />
          </span>
        </div>
        <div style="display: table-cell; text-align: right;">
          <tiles:getAsString name="titleRight" ignore="true" />
        </div>
      </div>
      <hr style="height: 2px; background-color: #6AA4D9; border: none; color: #6AA4D9" />
    </td>
  </tr>
  <tr>
    <td style="padding: 0px 10px;">
      <tiles:insert attribute="content" />
    </td>
  </tr>
</table>
</body>
</html>