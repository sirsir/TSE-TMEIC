<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">

function createProcessProductGrid(productPlanNo, processComponentNo) {

  processProductGrid.prop;

  processProductGrid.prop.colNames =
    [
      'ID',
      'CRUD',
      '<bean:message key="product.serialno"/>',
      '<bean:message key="product.barcode"/>',
      '<bean:message key="product.quality.label"/>',
      'revision',
      '<bean:message key="result.status"/>',
      '<bean:message key="start.time"/>',
      '<bean:message key="complete.time"/>',
      <c:forEach var="i" begin="1" end="${processMaterialPlanMax}" step="1">
      'materialComponentNo',
      '<bean:message key="name"/>',
      '<bean:message key="quantity"/>',
      '<bean:message key="material.unit"/>',
      </c:forEach>
      <c:forEach var="i" begin="1" end="${processProductSpecPlanMax}" step="1">
      'specComponentNo',
      'specId',
      'specAttributeId',
      '<bean:message key="name"/>',
      '<bean:message key="input.value"/>',
      </c:forEach>

    ];

  processProductGrid.prop.colModel =
    [
      { name : 'id', key : true,      hidden : true, frozen : false },
      { name : 'crud',                hidden : true, frozen : false },
      { name : 'serialNo',            width : '120', align : 'left',   frozen : false, sortable : true,  sorttype : 'text', editable : false},
      { name : 'barcode',             width : '120', align : 'left',   frozen : false, sortable : true,  sorttype : 'text', editable : false},
      { name : 'qualityLabel',        hidden : true, width : '120', align : 'left',   frozen : false,  sortable : false, sorttype : 'text', editable : false},
      { name : 'revision',            hidden : true, frozen : false },
      { name : 'status',              width : '100', align : 'center', frozen : false, sortable : true,  sorttype : 'text', editable : false, edittype : 'select', formatter:'select', editoptions:{value : '${f:h(processProductStatus)}'}, cellattr: processProductStatusStyleFmatter },
      { name : 'startDate',           width : '150', align : 'center', frozen : false, sortable : true,  sorttype : 'text', editable : false, edittype : 'text', editrules:{required : false},  editoptions: {maxlength: 20, dataInit:processGrid.dateTimePickerFmatter}},
      { name : 'endDate',             width : '150', align : 'center', frozen : false, sortable : true,  sorttype : 'text', editable : false, edittype : 'text', editrules:{required : false},  editoptions: {maxlength: 20, dataInit:processGrid.dateTimePickerFmatter}},
      <c:forEach var="i" begin="1" end="${processMaterialPlanMax}" step="1">
      { name : 'materialComponentNo${i}', hidden : true, frozen : true },
      { name : 'materialName${i}',        hidden : true, width : '120', align : 'left',   frozen : false, sortable : false, sorttype : 'text', editable : false},
      { name : 'materialQty${i}',         hidden : true, width :  '90', align : 'right',  frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : false},  editoptions: {maxlength: ${materialQuantityMaxLength}}},
      { name : 'materialUnit${i}',        hidden : true, width :  '40', align : 'left',   frozen : false, sortable : false, sorttype : 'text', editable : false},
      </c:forEach>
      <c:forEach var="i" begin="1" end="${processProductSpecPlanMax}" step="1">
      { name : 'specComponentNo${i}',     hidden : true, frozen : true },
      { name : 'specId${i}',              hidden : true, frozen : true },
      { name : 'specAttributeId${i}',     hidden : true, frozen : true },
      { name : 'specName${i}',            hidden : true, width : '120', align : 'left',   frozen : false, sortable : false, sorttype : 'text', editable : false},
      { name : 'inputValue${i}',          hidden : true, width : '120', align : 'left',   frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: ${specInputvalueMaxLength}, separator : ':::', delimiter : '|||'}},
      </c:forEach>
    ];

  processProductGrid.groupHeader = (function() {
    $(processProductGrid.gridId).jqGrid('setGroupHeaders', {
      useColSpanStyle : true,
      groupHeaders : [
                      <c:forEach var="i" begin="1" end="${processMaterialPlanMax}" step="1">
                      {startColumnName: 'materialComponentNo${i}',   numberOfColumns: 4, titleText: '<bean:message key="material"/>${i}'},
                      </c:forEach>
                      <c:forEach var="i" begin="1" end="${processProductSpecPlanMax}" step="1">
                      {startColumnName: 'specComponentNo${i}',   numberOfColumns: 5, titleText: '<bean:message key="spec"/>${i}'},
                      </c:forEach>
                     ]
    });
  });

  processProductGrid.prop.data =
    [
      {'serialNo' : 'SerailNo0001','revision' : '1','status' : '1','startDatetime' : '2015/05/20','endDatetime' : '2015/05/30'},
    ];

  processProductGrid.prop.data = [];

  processProductGrid.prop.width = 1100;
  processProductGrid.prop.height = 278;

  processProductGrid.edit(${f:url('/')});
  
  processProductGrid.prop.onSelectRowEx = function (rowId){
	  
	  $("#qualityLabel").val($(processProductGrid.gridId).getCell(rowId, "barcode"));
  }
  
  processProductGrid.prop.aftersavefuncRowEx = function(rowId) {

    var processGridRowId = processGrid.selrow();
    $(processGrid.gridId).setCell(processGridRowId, "productResults", processProductGrid.exportJson());

    var crud = $(processGrid.gridId).getCell(processGridRowId, "crud");

    if (crud == "R") {
      $(processGrid.gridId).setCell(processGridRowId, "crud", "U");
    }
  }

  $(processProductGrid.gridId).jqGrid(processProductGrid.prop);
}

</script>
