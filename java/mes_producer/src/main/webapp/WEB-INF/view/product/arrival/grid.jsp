<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">

  var grid = new JqGridUtils("#list", "#pager");

  function createGrid() {
    grid.prop;
    grid.prop.colNames =
      [
        'ID',
        'CRUD',
        '<bean:message key="arrival.date"/>',
        '<bean:message key="arrival.no"/>',
        '<bean:message key="part.no"/>',
        '<bean:message key="part.name"/>',
        '<bean:message key="product.customer.name"/>',
        '<bean:message key="product.model"/>',
        '<bean:message key="product.item.no"/>',
        '<bean:message key="plan.qty.br"/>',
        '<bean:message key="arrival.qty"/>',
      ];

    grid.prop.colModel =
      [
        { name : 'id', key : true, hidden : true },
        { name : 'crud',           hidden : true },
        { name : 'arrivalDate',    width : '110', align : 'center', frozen : true, sortable : true, sorttype : 'date', editable : true,  edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: 10, dataInit:grid.datePickerFmatter}},
        { name : 'arrivalNo',      width : '110', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false, edittype : 'text',   editrules:{required : requiredOfArrivalNo}, editoptions: {maxlength: 10}},
        { name : 'partNo',         width : '150', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : true,  edittype : 'select', editrules:{required : true}, formatter: 'select',    editoptions:{value : '${f:h(productList)}', 
        	dataEvents:[{
            type:"change",
            fn:function(e){
            	var rowid = $("#list").jqGrid('getGridParam','selrow');
            	//var rowData = $("#list").getRowData(rowid);
            	var id = this.value;
            	var pd = '${f:h(productDetail)}';
            	var rows = pd.split("_n-");
            	for(var i=0; i<rows.length; i++) {
            		var cols = rows[i].split("\t");
            		if(cols.length == 5) {
	            		if(cols[0] == id) {
	            			$("#list").jqGrid('setCell', rowid, 'partName', cols[1]);
	            			$("#list").jqGrid('setCell', rowid, 'customerName', cols[2]);
	            			$("#list").jqGrid('setCell', rowid, 'model', cols[3]);
	            			$("#list").jqGrid('setCell', rowid, 'itemNo', cols[4]);
	            			break;
	            		}
            		}
            	}
            }
        }]}},
        { name : 'partName',     width : '200', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false,  edittype : 'text', editrules:{required : false}},
        { name : 'customerName', width : '200', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false,  edittype : 'text', editrules:{required : false}},
        { name : 'model',        width : '184', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false,  edittype : 'text', editrules:{required : false}},
        { name : 'itemNo',       hidden : true },
        { name : 'planQty',      width : '80', align : 'right',  frozen : true, sortable : true, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: '${planQtyMaxLength}'}},
        { name : 'resultQty',    width : '80', align : 'right',  frozen : true, sortable : true, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: '${planQtyMaxLength}'}}
      ];

    grid.pkey = ['arrivalNo'];

    grid.prop.data =
      [
        {'arrivalDate' : '2015/05/11', 'arrivalNo' : '1505110001', 'partNo' : '1', 'partName' : '1', 'customerName' : '1', 'model' : '1', 'planQty' : '9999'},
      ];

    grid.prop.data = ${arrivalJson};
    grid.prop.width = 1180;

    grid.edit(${f:url('/')});
/* 
    var defaultGridComplete = grid.prop.gridComplete;

    grid.prop.gridComplete = function () {

      changeBackgroundColorByStatus(grid);
      defaultGridComplete();

    };

    var defaultOnSelectRow = grid.prop.onSelectRow;

    grid.prop.onSelectRow = function(){

      var rowId = grid.selrow();

      if (rowId == null) {
        return;
      }

      if ($(grid.gridId).getCell(rowId, "arrivalDate")) {

        if (grid.lastsel != null) {
          $(grid.gridId).restoreRow(grid.lastsel);
        }

        $(grid.gridId).resetSelection();
        return;
      }

      defaultOnSelectRow(rowId);

    }; */

    $(grid.gridId).jqGrid(grid.prop);
  }
  var statusStyleFmatter = function(rowId, val, rawObject, cm, rdata, name){

    if (!rdata.status){
      return "";
    }

    var color = productStatusColor[rdata.status];

    return  'style="color: ' + color + ';"';
  }

  function timeElement(value, options) {
    var el = document.createElement("input");
    el.type="text";
    el.value = value.replace(":", "");
    return el;
  }

  function timeValue(elem, operation, value) {
    if(operation === 'get') {
       return $(elem).val();
    } else if(operation === 'set') {
      return $(elem).val(value.replace(":", ""));
    }
  }

  timeFmatter = function(cellvalue, options, rowObject) {

    if (cellvalue == null) { return ""; }

    if (cellvalue.match(/:/)) {
      return cellvalue;
    }

    return cellvalue.substring(0,2) + ":" + cellvalue.substring(2,4);
  }

  dateTimeFmatter = function(cellvalue, options, rowObject) {

    if (cellvalue == null) { return ""; }

    return cellvalue.substring(0,16);
  }
</script>
