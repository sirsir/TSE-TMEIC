<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

 <script type="text/javascript">
  $(function() {
    if ($.trim($("#validatorErrorsDialog").text()) != "") {
      $("#validatorErrorsDialog").dialog({
        title: "入力エラー",
        buttons: { "OK": function() { $(this).dialog("close"); } },
        bgiframe : true,
        autoOpen : true,
        position : {at : 'center'},
        width : 400,
        minHeight : 200,
        resizable : false,
        modal : true
      });
    }
  });
  </script>

  <div id="validatorErrorsDialog" style="font-size: 13px; display: none;">
    <html:errors/>
  </div>
