<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>

<script type="text/javascript">
$(function() {

  <% //IE戻る時のOnloadで再表示させない %>
  if ($("#okDialogShowed").val() != "") {
    return;
  }
  $("#okDialogShowed").val("true");

  var dialogData = <%=session.getAttribute("dialogInfo")%>;
  <%session.removeAttribute("dialogInfo");%>

  if (dialogData != null) {

    var dialogOption ={
        title     : dialogData.title,
        buttons   : { "OK": function() { $(this).dialog("close"); } },
        bgiframe  : dialogData.bgiframe,
        autoOpen  : dialogData.autoOpen,
        position  : {at : dialogData.position},
        minWidth  : dialogData.minWidth,
        minHeight : dialogData.minHeight,
        resizable : dialogData.resizable,
        modal     : dialogData.modal
      };

    dialogUtils.newDialog("ui-icon-alert", dialogData.message, dialogOption);
  }
});

<%-- ダイアログ --%>
function dialogUtils() {

}

dialogUtils.defaultProp = function () {

  var dialogProp = {
      buttons : {
                   "OK": function() {
                     $(this).dialog("close");
                     $(this).remove();
                   }
                },
      bgiframe : true,
      autoOpen : true,
      position : {at : 'center'},
      resizable : false,
      modal : true
    };

  return dialogProp;
}

dialogUtils.dialogCount = 0;

dialogUtils.newDialog = function(icon, message, dialogProp) {

  var count = dialogUtils.dialogCount++;

  $("body").append('<div id="dialog' + count + '" class="ui-state-default" style="font-size: 13px; display: none;">'
                 + '  <span id="dialogIcon' + count + '" class="ui-icon"></span><div id="dialogMessage' + count + '" style="padding-left: 15px;"></div>'
                 + '</div>');

  $("#dialogIcon" + count).addClass(icon);
  $("#dialogMessage" + count).html(message);
  $("#dialog" + count).dialog(dialogProp);
}

dialogUtils.open = function(icon, title, message, width, height) {

  var dialogProp = dialogUtils.defaultProp();
  dialogProp.title = title;
  dialogProp.minWidth = width;
  dialogProp.minHeight = height;

  dialogUtils.newDialog(icon, message, dialogProp);
}

dialogUtils.info = function(message) {
  dialogUtils.open("ui-icon-info", "<bean:message key="confirmation"/>", message, 400, 200);
}

dialogUtils.alert = function(message) {
  dialogUtils.open("ui-icon-alert", "<bean:message key="alert"/>", message, 400, 200);
}

dialogUtils.error = function(message) {
  dialogUtils.open("ui-icon-circle-close", "<bean:message key="error"/>", message, 400, 200);
}

dialogUtils.openConfirm = function(icon, title, message, width, height, func) {

  var dialogProp = dialogUtils.defaultProp();
  dialogProp.title = title;
  dialogProp.minWidth = width;
  dialogProp.minHeight = height;

  dialogProp.buttons = {
                         "<bean:message key="yes"/>": function() {
                           func();
                           $(this).dialog("close");
                           $(this).remove();
                         },
                         "<bean:message key="no"/>": function() {
                           $(this).dialog("close");
                           $(this).remove();
                         }
                       };

  dialogProp.modal = false;

  dialogUtils.newDialog(icon, message, dialogProp);
}

dialogUtils.confirm = function(message, func) {
  dialogUtils.openConfirm("ui-icon-info", "<bean:message key="confirmation"/>", message, 400, 200, func);
}
</script>

<input type="hidden" id="okDialogShowed">
