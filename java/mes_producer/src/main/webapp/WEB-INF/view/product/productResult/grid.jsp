<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">

var productResultGrid = new JqGridUtils("#productResultList", "#productResultListPager");

function createGrid() {

  productResultGrid.prop;

  productResultGrid.prop.colNames =
    [
      'ID',
      'CRUD',
      '<bean:message key="product.manifacturedate"/>',
      '<bean:message key="plan.no"/>',
      '<bean:message key="part.no"/>',
      '<bean:message key="part.name"/>',
      '<bean:message key="product.customer.name"/>',
      '<bean:message key="product.model"/>',
      '<bean:message key="planned.quantity.br"/>',
      '<bean:message key="result.quantity.br"/>',
      '<bean:message key="plan.start.time"/>',
      '<bean:message key="plan.end.time"/>',
      '<bean:message key="start.time"/>',
      '<bean:message key="finish.time"/>',
      '<bean:message key="result.status"/>',
      '<bean:message key="result"/>',
    ];

  productResultGrid.prop.colModel =
    [
      { name : 'id', key : true, hidden : true },
      { name : 'crud',           hidden : true },
      { name : 'manufactureDate', width :  '70', align : 'center', frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'productPlanNo',   width : '76', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'partNo',          width : '76', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'partName',        width : '110', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'customerName',    width : '110', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'model',       	  width : '84', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'planQty',         width :  '49', align : 'right',  frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'resultQty',       width :  '49', align : 'right',  frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'planStartTime',   width :  '50', align : 'center', frozen : true, sortable : true, sorttype : 'text', editable : false, formatter: timeFmatter},
      { name : 'planEndDate',     width : '105', align : 'center', frozen : true, sortable : true, sorttype : 'text', editable : false, formatter: dateTimeFmatter},
      { name : 'startDatetime',   width : '105', align : 'center', frozen : true, sortable : true, sorttype : 'text', editable : false, formatter: dateTimeFmatter},
      { name : 'endDatetime',     width : '105', align : 'center', frozen : true, sortable : true, sorttype : 'text', editable : false, formatter: dateTimeFmatter},
      { name : 'status',          width : '80', align : 'center', frozen : true, sortable : true, sorttype : 'text', editable : false, edittype : 'select', formatter:'select', editoptions:{value : '${f:h(productStatus)}'}, cellattr: statusStyleFmatter },
      { name : 'resultsInputBtn', width :  '80', align : 'center', frozen : true, sortable : true, sorttype : 'text', editable : false, formatter: resultsInputBtnFmatter},
    ];

  productResultGrid.prop.data =
    [
      {'manufactureDate' : '2015/05/11', 'productPlanNo' : '1505110001', 'partNo' : '1','partName': 'xxxx','customerName': 'xxxx','model': 'xxxx', 'planQty' : '9999', 'resultQty' : '9999', 'planStartTime' : '09:15', 'planEndDate' : '2015/05/11 13:00', 'startDatetime' : '2014/05/11 09:20', 'endDatetime' : '2014/05/11 12:00', 'resultStatus' : 'finish'},
    ];

  productResultGrid.prop.data = ${productResultJson};

  productResultGrid.prop.width = 1248;
  productResultGrid.prop.height = 520;

  productResultGrid.edit(${f:url('/')});

  var defaultGridComplete = productResultGrid.prop.gridComplete;

  productResultGrid.prop.gridComplete = function () {

    changeBackgroundColorByStatus(productResultGrid);
    defaultGridComplete();

  };

  $(productResultGrid.gridId).jqGrid(productResultGrid.prop);
}

var statusStyleFmatter = function(rowId, val, rowObject, cm, rdata, name){

  if (!rdata.status){
    return "";
  }

  var color = productStatusColor[rdata.status];

  return  'style="color: ' + color + ';"';
}

var timeFmatter = function(cellvalue, options, rowObject) {

  if (cellvalue == null) { return ""; }

  if (cellvalue.match(/:/)) {
    return cellvalue;
  }

  return cellvalue.substring(0,2) + ":" + cellvalue.substring(2,4);
}

var dateTimeFmatter = function(cellvalue, options, rowObject) {

  if (cellvalue == null) { return ""; }

  return cellvalue.substring(0,16);
}

// 実績入力
var resultsInputBtnFmatter = function(cellvalue, options, rowObject) {
  var status = rowObject.status;
  if (status == null) {
    return '<bean:message key="not.set"/>';
  }

  return '<input type="button" value="<bean:message key="input"/>" onclick="inputOfResult(\'' + options['rowId'] + '\')"/>';
}

function inputOfResult(rowId) {
  var productPlanNo = $(productResultGrid.gridId).getCell(rowId, "productPlanNo");
  $("#mainForm").attr("action", "${f:url('/product/productResultInput/')}" + "?productPlanNo=" + productPlanNo);
  $("#callerDisplayStartDate").val($("#displayStartDate").val());
  $("#callerDisplayEndDate").val($("#displayEndDate").val());
  $("#mainForm").submit();
}

</script>