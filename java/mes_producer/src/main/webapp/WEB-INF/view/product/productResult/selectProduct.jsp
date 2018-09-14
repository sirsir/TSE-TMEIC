<%-- コピーダイアログ --%>
<script type="text/javascript">
function selectProduct() {

  var dialogProp = dialogUtils.defaultProp();
  dialogProp.title = "<bean:message key='select.product'/>";
  dialogProp.maxWidth = 200;
  dialogProp.minHeight = 200;
  dialogProp.buttons = [
                         { text: '<bean:message key="ok"/>',
                           click: function() {
                             doSelect();
                           }
                         },
                         { text: '<bean:message key="cancel"/>',
                           click: function() { $("#selectProductDialog").dialog("close"); }
                         }
                       ];

  $("#selectProductDialog").dialog(dialogProp);
}

function doSelect() {
  var barcode = $("#barcode").val().trim();
  if(barcode.length == 0) {
	  alert("<bean:message key='errors.enter.barcode'/>");
	  $("#barcode").focus();
	  return;  
  }
  else if(barcode.length < 5 ||barcode.length > 6)  {
	  alert("<bean:message key='errors.barcode.not.exist'/>");
	  $("#barcode").focus();
	  return;
  }
	  
  $.ajax({
    url: "${f:url('//product/productResult/selectProduct/')}",
    async: false,
    type: "POST",
    data: {
           barcode  : $("#barcode").val()
    },
    dataType: "jsonp",
    success: function(data) {

      if (data.result == "OK") {
    	  $("#displayStartDate").val(data.manufatureDate);
    	  $("#displayEndDate").val(data.manufatureDate);
    	  $("#selectProductPlanNo").val(data.planNo);
        doSearch(); 
       // $("#selectProductDialog").dialog("close");

      } else if (data.result == "NG") {
          dialogUtils.alert(data.message);
      }
    }
  });
}
</script>

<div id="selectProductDialog" style="display: none;">
  <div style="padding-bottom: 10px;"></div>
  <div style="color:#136C8A;font-size:16px"><bean:message key="scan.barcode"/><hr style="background-color: #136C8A; height: 1px; border: 0;"></div>
  <br>
  <div>
  	<label for="barcode"><bean:message key="product.barcode"/></label>
  	<input type="text" id="barcode" maxlength="6" style="width: 100px;">
  </div>
</div>