<script type="text/javascript">
function rePrintQualityLabel() {
	if($("#qualityLabel").val() == ""||$("#qualityLabel").val() == null)
  	{
		 dialogUtils.alert("<bean:message key='errors.not.select'/>");
		 return;
  	}
  var dialogProp = dialogUtils.defaultProp();
  dialogProp.title = "<bean:message key='re.print.quality.label'/>";
  dialogProp.minWidth = 350;
  dialogProp.minHeight = 200;
  dialogProp.buttons = [];
  $("#rePrintQualityLabelDialog").dialog(dialogProp);
}
function createPrinter(){
	var printersJson = ${printersJson};
	for(i = 0 ; i < printersJson.length ; i++)
	{
		$('#printer').append('<option value="'+ printersJson[i].printerId +'">' + printersJson[i].printerName + '</option>');
	}
}
function doPrint() {
	  var barcode = $("#qualityLabel").val().trim();
	  var printId = $("#printer option:selected").val();	
	  if(barcode.length != 5) {
		  dialogUtils.alert("<bean:message key='errors.barcode.not.exist'/>");
		  return;
	  }	
	  ajaxPostJson({
		    url: "${f:url('//product/productResultInput/printQualityLabel/')}",
	        data: {
	          productPlanNo : "${productPlanNo}",
              barcode : barcode,
              printId : printId
	        },
	        success:function(data) {
	            if (data.result == "OK") {
	            	$("#rePrintQualityLabelDialog").dialog("close");
	            } else if (data.result == "NG") {
	              dialogUtils.alert(data.message);
	            }
	          }
	      });
}
function closeRePrintQualityLabelDialog() { 
	$("#rePrintQualityLabelDialog").dialog("close"); 
	}
</script>
<div id="rePrintQualityLabelDialog" style="display: none;">
  <div style="padding-bottom: 10px;"></div>
  <div style="color:#136C8A;font-size:16px">
  	<bean:message key="re.print.quality.label"/>
  	<hr style="background-color: #136C8A; height: 1px; border: 0;">
  </div>
  <br>
  <div>
  	<label for="qualityLabel"><bean:message key="product.quality.label"/></label>
  	<input type="text" id="qualityLabel" maxlength="5" style="width: 100px;" disabled> 
  	<hr style="background-color: #136C8A; height: 1px; border: 0;">	
  </div>
  <div style="margin-top:10px;float:right;">
  	  <bean:message key="output.printer"/><br>
	  <select id="printer"></select>
	  <input type="button" value="<bean:message key="print"/>" onclick="doPrint()" style="width: 110px;"/>
	  <input type="button" value="<bean:message key="cancel"/>" onclick="closeRePrintQualityLabelDialog()" style="width: 110px;"/>
  </div>

</div>
