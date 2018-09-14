<style type="text/css">
  .txtShipping{
  	width: 300px;
  }
</style>
<script type="text/javascript">
function openShippingProductDialog() {
	var productJson = '${productJson}';
	var processInfo = new Array($.parseJSON(productJson));
	$(shippingProductGrid.gridId).clearGridData();
    $(shippingProductGrid.gridId).setGridParam({datatype: "local", data: JqGridUtils.setDetailGrid(JSON.stringify(processInfo))});
    $(shippingProductGrid.gridId).trigger("reloadGrid");
    
    $("#customerNameShipping").val(processInfo[0].customerName);
    $("#partNoShipping").val(processInfo[0].partNo);
    $("#partNameShipping").val(processInfo[0].partName);
    $("#modelShipping").val(processInfo[0].model);
    $("#mfgDateShipping").val(processInfo[0].manufactureDate);
    $("#planCodeShipping").val(processInfo[0].productPlanNo);
    $("#packingDateShipping").val('${packingDate}');
    $("#checkerShipping").val('${checker}');
	  var dialogProp = dialogUtils.defaultProp();
	  dialogProp.title = "<bean:message key='process.shipping.ticket.printing'/>";
	  dialogProp.minWidth = 720;
	  dialogProp.minHeight = 530;
	  dialogProp.buttons = [];
	  $("#shippingTicketPrintingDialog").dialog(dialogProp);
}

function createShippingPrinter(){
	var printerShippingsJson = ${printerShippingsJson};
	for(i = 0 ; i < printerShippingsJson.length ; i++)
	{
		$('#shippingPrinter').append('<option value="'+ printerShippingsJson[i].printerId +'">' + printerShippingsJson[i].printerName + '</option>');
	}
}

function createShippingPrinterGrid() {
	shippingProductGrid.prop;
	shippingProductGrid.prop.colNames =
      [
        'ID',
        'CRUD',
        '<bean:message key="product.manifacturedate"/>',
        '<bean:message key="plan.no"/>',
        '<bean:message key="part.no"/>',
        '<bean:message key="part.name"/>',
        '<bean:message key="product.customer.name"/>',
        '<bean:message key="product.model"/>',
        'processComponentNo',
      ];

	shippingProductGrid.prop.colModel =
      [
        { name : 'id', key : true,      hidden : true },
        { name : 'crud',                hidden : true },
        { name : 'manufactureDate',     width : '110', align : 'center', frozen : true, sortable : false, sorttype : 'text', editable : false},
        { name : 'productPlanNo',       width : '110', align : 'center', frozen : true, sortable : false, sorttype : 'text', editable : false},
        { name : 'partNo',              width : '110', align : 'center', frozen : true, sortable : false, sorttype : 'text', editable : false},
        { name : 'partName',            width : '110', align : 'center', frozen : true, sortable : false, sorttype : 'text', editable : false},
        { name : 'customerName',        width : '110', align : 'center', frozen : true, sortable : false, sorttype : 'text', editable : false},
        { name : 'model',               width : '110', align : 'center', frozen : true, sortable : false, sorttype : 'text', editable : false},
        { name : 'processComponentNo',  hidden : true },
      ];

	shippingProductGrid.prop.data =
      [
        {'manufactureDate' : '2015/05/11', 'productPlanNo' : '1505110001', 'productName' : 'product1', 'processComponentNo' : '1'},
      ];

	shippingProductGrid.prop.data = [];
	shippingProductGrid.prop.width = 690;
	shippingProductGrid.prop.height = 26;
	shippingProductGrid.prop.rownumbers = false;
	shippingProductGrid.edit(${f:url('/')});

    $(shippingProductGrid.gridId).jqGrid(shippingProductGrid.prop);
  }
  
    function closeShippingTicketPrintingDialog() {
	   $("#shippingTicketPrintingDialog").dialog("close");
	}
    function doShippingPrint() {
    	var printId = $("#shippingPrinter option:selected").val();	
    	var customerName = $("#customerNameShipping").val();
    	var partNo = $("#partNoShipping").val();
    	var partName = $("#partNameShipping").val();
    	var model = $("#modelShipping").val();
    	var mfgDate = $("#mfgDateShipping").val();
    	var planCode = $("#planCodeShipping").val();
    	var lotNo = $("#lotNoShipping").val();
    	var timeOfDelivery = $("#timeOfDeliveryShipping").val();
    	var checker = $("#checkerShipping").val();
    	var packingDate = $("#packingDateShipping").val();
    	var color = $("#colorShipping").val();
    	var serialNo = $("#serialNoShipping").val();
    	var quantity = $("#quantityShipping").val();
    	var location = $("#locationShipping").val();
    	var qualityRank = $("#qualityRankShipping").val();
    	
  	  ajaxPostJson({
  		    url: "${f:url('//product/productResultInput/printShippingTicket/')}",
  	        data: {
  	        	customerName : customerName,
  	        	partNo : partNo,
  	        	partName : partName,
  	        	model : model,
  	        	mfgDate : mfgDate,
  	        	planCode : planCode,
  	        	lotNo : lotNo,
  	        	timeOfDelivery : timeOfDelivery,
  	        	checker : checker,
  	        	packingDate : packingDate,
  	        	color : color,
  	        	serialNo : serialNo,
  	        	quantity : quantity,
  	        	location : location,
  	        	qualityRank : qualityRank,
  	        	printId : printId
  	        },
  	        success:function(data) {
  	            if (data.result == "OK") {
  	            	$("#shippingTicketPrintingDialog").dialog("close");
  	            } else if (data.result == "NG") {
  	              dialogUtils.alert(data.message);
  	            }
  	          }
  	      });
  }
</script>
    <div id = "shippingTicketPrintingDialog"  style="display: none;">
	    <div style="color:#136C8A;font-size:16px">
  		<bean:message key="print.shipping.ticket"/>
  	</div>
  	<br>
	<div>
    	<table class="scroll" id="shippingProductList"></table>
 	</div>
	
	 <div style="margin-top:15px;">
	  	<table>
	  		<tr><td><bean:message key="shipping.ticket.lotNo"/></td><td><input type="text" id="lotNoShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.mfgDate"/></td><td><input type="text" id="mfgDateShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.planCode"/></td><td><input type="text" id="planCodeShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.timeOfDelivery"/></td><td><input type="text" id="timeOfDeliveryShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.checker"/></td><td><input type="text" id="checkerShipping" class="txtShipping"  maxlength="50"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.packingDate"/></td><td><input type="text" id="packingDateShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.color"/></td><td><input type="text" id="colorShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.serialNo"/></td><td><input type="text" id="serialNoShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.quantity"/></td><td><input type="text" id="quantityShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.location"/></td><td><input type="text" id="locationShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  		<tr><td><bean:message key="shipping.ticket.qualityRank"/></td><td><input type="text" id="qualityRankShipping" class="txtShipping"  maxlength="30"/></td></tr>
	  	</table>
  	</div>
  	<input type="hidden" id="customerNameShipping" class="txtShipping"/>
	<input type="hidden" id="partNoShipping" class="txtShipping"/>
	<input type="hidden" id="partNameShipping" class="txtShipping"/>
	<input type="hidden" id="modelShipping" class="txtShipping"/>
	<br>
	<hr style="background-color: #136C8A; height: 1px; border: 0;">
  	<div style="margin-top:15px;float:right;">
  		<bean:message key="output.printer"/><br>
	  	<select id="shippingPrinter"></select>
	    <input type="button" value="<bean:message key="print"/>" onclick="doShippingPrint()" style="width: 110px;"/>
	    <input type="button" value="<bean:message key="cancel"/>" onclick="closeShippingTicketPrintingDialog()" style="width: 110px;"/>
    </div>
</div>
