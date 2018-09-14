<script type="text/javascript">

var productGrid = new JqGridUtils("#productList", "#productPager");
var processGrid = new JqGridUtils("#processList", "#processPager");
var specProcessGrid = new JqGridUtils("#specProcessList", "#specProcessPager");
var specProductGrid = new JqGridUtils("#specProductList", "#specProductPager");
var materialGrid = new JqGridUtils("#materialList", "#materialPager");

<%-- 製品一覧 --%>
function createProductGrid() {

  productGrid.prop;

  productGrid.prop.colNames =
    [
      'ID',
      'CRUD',
      '<bean:message key="target"/>',
      '<bean:message key="part.no"/>',
      '<bean:message key="part.name"/>',
      '<bean:message key="product.customer.name"/>',
      '<bean:message key="product.model"/>',
      '<bean:message key="product.item.no"/>',
      '<bean:message key="product.plating.machine"/>',
      '<bean:message key="kind"/>',
      '',
    ];

  productGrid.prop.colModel =
    [
      { name : 'id', key : true, hidden : true },
      { name : 'crud',           hidden : true },
      { name : 'target',      	 width : '40',  align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : false, edittype : 'text',   editrules:{required : false}, formatter: productGrid.radioFmatter},
      { name : 'partNo',  		 width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : true }, editoptions: {maxlength: 10}},
      { name : 'partName', 		 width : '120', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : true }, editoptions: {maxlength: 50}},
      { name : 'customerName',   width : '120', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : false }, editoptions: {maxlength: 50}},
      { name : 'model',       	 width : '120', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : false }, editoptions: {maxlength: 50}},
      { name : 'itemNo',       	 width : '120', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : true }, editoptions: {maxlength: 50}},
      { name : 'platingMachine', width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true,  edittype : 'select', editrules:{required : true }, formatter:'select', editoptions:{value : '${f:h(platingMachine)}', separator : ':::', delimiter : '|||'}},
      { name : 'productKind',    width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true,  edittype : 'select', editrules:{required : false }, formatter:'select', editoptions:{value : '${f:h(productKind)}', separator : ':::', delimiter : '|||'}},
      { name : 'process',        hidden : true },
    ];

  productGrid.pkey = ['partNo'];

  productGrid.prop.data =
    [
      {'target' : '', 'partNo' : 'XXXXXXXXXX', 'partName' : 'ProductName','customerName': 'xxxx','model': 'xxxx','model': 'xxxx', 'platingMachine' : '1', 'productKind' : '1'},
      {'target' : '', 'partNo' : 'XXXXXXXXXX', 'partName' : 'ProductName','customerName': 'xxxx','model': 'xxxx','model': 'xxxx', 'platingMachine' : '1', 'productKind' : '1'},
      {'target' : '', 'partNo' : 'XXXXXXXXXX', 'partName' : 'ProductName','customerName': 'xxxx','model': 'xxxx','model': 'xxxx', 'platingMachine' : '1', 'productKind' : '1'},
      {'target' : '', 'partNo' : 'XXXXXXXXXX', 'partName' : 'ProductName','customerName': 'xxxx','model': 'xxxx','model': 'xxxx', 'platingMachine' : '1', 'productKind' : '1'},
      {'target' : '', 'partNo' : 'XXXXXXXXXX', 'partName' : 'ProductName','customerName': 'xxxx','model': 'xxxx','model': 'xxxx', 'platingMachine' : '1', 'productKind' : '1'},
    ];

  productGrid.prop.data = ${productJson};
  productGrid.prop.width = 427;
  productGrid.prop.height = 130;

  productGrid.edit(${f:url('/')});

  productGrid.prop.onSelectRowEx = function(rowId) {

    var partName = $(productGrid.gridId).getCell(rowId, "partName");

    if (partName.indexOf("class=\"editable\"") >= 0) {
      return;
    }

    $("#selectProduct").text(partName);

    var processes = $(productGrid.gridId).getCell(rowId, "process");

    if (!processes) {
      $.ajax({
        url: "${f:url('/master/product/processOfProduct/')}",
        async: false,
        type: "POST",
        data: {
          partNo : $(productGrid.gridId).getCell(rowId, "partNo")
        },
        dataType: "jsonp",
        success: function(data) {
          if (data.result == "OK") {
            processes = data.processList;
            $(productGrid.gridId).setCell(rowId, "process", data.processList);
          } else if (data.result == "NG") {
            dialogUtils.alert(data.message);
          }
        }
      });
    }

    $(processGrid.gridId).setGridParam({datatype: "local", data: JqGridUtils.setDetailGrid(processes)});
    $(processGrid.gridId).trigger("reloadGrid");
    processGrid.prop.lastSelRowForget();

    $(productGrid.gridId).setCell(rowId, "process", processGrid.exportJson());

    $(specProcessGrid.gridId).clearGridData();
    $(specProductGrid.gridId).clearGridData();
    $(materialGrid.gridId).clearGridData();
  }

  $(productGrid.gridId).jqGrid(productGrid.prop);
}

<%-- 工程一覧 --%>
function createProcessGrid() {

  processGrid.prop;

  processGrid.prop.colNames =
    [
      'ID',
      'CRUD',
      'product.id',
      'process.component.no',
      '<bean:message key="target"/>',
      '<bean:message key="process.name"/>',
      '<bean:message key="process.contents"/>',
      '<bean:message key="process.time"/>',
      '<bean:message key="personnel.required"/>',
      '<bean:message key="parallel.work"/>',
      '<bean:message key="product.unit.size"/>',
      'processOrder',
      'specProducts',
      'specProcesses',
      'materials',
    ];

  processGrid.prop.colModel =
    [
      { name : 'id', key : true,      hidden : true, frozen : true },
      { name : 'crud',                hidden : true, frozen : true },
      { name : 'partNo',              hidden : true, frozen : true },
      { name : 'processComponentNo',  hidden : true, frozen : true },
      { name : 'target',              width : '40',  align : 'left',  frozen : false, sortable : false, sorttype : 'text', editable : false, edittype : 'text',     editrules:{required : false}, formatter: processGrid.radioFmatter},
      { name : 'processId',           width : '100', align : 'left',  frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'select',   editrules:{required : false}, formatter:'select', editoptions:{value : '${f:h(mProcess)}', separator : ':::', delimiter : '|||'}},
      { name : 'processContents',     width : '100', align : 'left',  frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',     editrules:{required : false}, editoptions: {maxlength: 30}},
      { name : 'processTime',         width : '100', align : 'right', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',     editrules:{required : false}, editoptions: {maxlength: 10}},
      { name : 'personnelRequired',   width : '100', align : 'right', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',     editrules:{required : false}, editoptions: {maxlength: 10}},
      { name : 'parallelWork',        width : '100', align : 'right', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',     editrules:{required : false}, editoptions: {maxlength: 10}},
      { name : 'unitSize',            width : '100', align : 'right', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',     editrules:{required : true}, editoptions: {maxlength: 10}}, { name : 'processOrder',        hidden : true },
      { name : 'specProducts',        hidden : true },
      { name : 'specProcesses',       hidden : true },
      { name : 'materials',           hidden : true },
    ];

  processGrid.pkey = ['partNo'];

  processGrid.prop.data = [];

  processGrid.prop.width = 723;
  processGrid.prop.height = 130;

  processGrid.edit(${f:url('/')});

  processGrid.prop.onSelectRowEx = function(rowId) {

    var productGridRowId = productGrid.selrow();

    var specProducts = $(processGrid.gridId).getCell(rowId, "specProducts");
    var specProcesses = $(processGrid.gridId).getCell(rowId, "specProcesses");
    var materials = $(processGrid.gridId).getCell(rowId, "materials");

    $(specProductGrid.gridId).setGridParam({datatype: "local", data: JqGridUtils.setDetailGrid(specProducts)});
    $(specProductGrid.gridId).trigger("reloadGrid");
    specProcessGrid.prop.lastSelRowForget();

    $(specProcessGrid.gridId).setGridParam({datatype: "local", data: JqGridUtils.setDetailGrid(specProcesses)});
    $(specProcessGrid.gridId).trigger("reloadGrid");
    specProductGrid.prop.lastSelRowForget();

    $(materialGrid.gridId).setGridParam({datatype: "local", data: JqGridUtils.setDetailGrid(materials)});
    $(materialGrid.gridId).trigger("reloadGrid");
    materialGrid.prop.lastSelRowForget();
  }

  processGrid.prop.aftersavefuncRowEx = function(rowId) {

    var productGridRowId = productGrid.selrow();

    $(productGrid.gridId).setCell(productGridRowId, "process", processGrid.exportJson());
    productGrid.prop.updateRowCrud(productGridRowId);
  }

  $(processGrid.gridId).jqGrid(processGrid.prop);
}

function setProcessGridCell(cellName, val) {

  var processGridRowId = processGrid.selrow();

  $(processGrid.gridId).setCell(processGridRowId, cellName, val);
  processGrid.prop.updateRowCrud(processGridRowId);

  processGrid.prop.aftersavefuncRowEx(processGridRowId);
}
<%-- 製品単位仕様一覧 --%>
function createSpecProductGrid() {

  specProductGrid.prop;

  specProductGrid.prop.colNames =
    [
      'ID',
      'CRUD',
      '<bean:message key="spec.code"/>',
      '<bean:message key="spec.name"/>',
    ];

  specProductGrid.prop.colModel =
    [
      { name : 'id', key : true,  hidden : true, frozen : true },
      { name : 'crud',            hidden : true },
      { name : 'specComponentNo', hidden : true },
      { name : 'specId', width : '100', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true, edittype : 'select', editrules:{required : false }, formatter:'select', editoptions:{value : '${f:h(mSpec)}', separator : ':::', delimiter : '|||'}},
    ];

  specProductGrid.pkey = ['specComponentNo'];

  specProductGrid.prop.data = [];

  specProductGrid.prop.width = 152;
  specProductGrid.prop.height = 130;

  specProductGrid.prop.caption = '<bean:message key="product.spec"/>';
  specProductGrid.prop.hidegrid = false;

  specProductGrid.edit(${f:url('/')});

  specProductGrid.prop.aftersavefuncRowEx = function(rowId) {
    setProcessGridCell("specProducts", specProductGrid.exportJson());
  }

  $(specProductGrid.gridId).jqGrid(specProductGrid.prop);
}

<%-- 工程単位仕様一覧 --%>
function createSpecProcessGrid() {

  specProcessGrid.prop;

  specProcessGrid.prop.colNames =
    [
      'ID',
      'CRUD',
      '<bean:message key="spec.code"/>',
      '<bean:message key="spec.name"/>',
    ];

  specProcessGrid.prop.colModel =
    [
      { name : 'id', key : true,  hidden : true, frozen : true },
      { name : 'crud',            hidden : true },
      { name : 'specComponentNo', hidden : true },
      { name : 'specId', width : '100', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true, edittype : 'select', editrules:{required : false }, formatter:'select', editoptions:{value : '${f:h(mSpec)}', separator : ':::', delimiter : '|||'}},
    ];

  specProcessGrid.pkey = ['specComponentNo'];

  specProcessGrid.prop.data = [];

  specProcessGrid.prop.width = 152;
  specProcessGrid.prop.height = 130;

  specProcessGrid.prop.caption = '<bean:message key="process.spec"/>';
  specProcessGrid.prop.hidegrid = false;

  specProcessGrid.edit(${f:url('/')});

  specProcessGrid.prop.aftersavefuncRowEx = function(rowId) {
    setProcessGridCell("specProcesses", specProcessGrid.exportJson());
  }

  $(specProcessGrid.gridId).jqGrid(specProcessGrid.prop);
}

<%-- 部材一覧 --%>
function createMaterialGrid() {

  materialGrid.prop;

  materialGrid.prop.colNames =
    [
      'ID',
      'CRUD',
      'materialComponentNo',
      '<bean:message key="material.code"/>',
      '<bean:message key="material.name"/>',
      '<bean:message key="quantity"/>',
      '<bean:message key="material.unit"/>',
    ];

  materialGrid.prop.colModel =
    [
      { name : 'id', key : true,      hidden : true, frozen : true },
      { name : 'crud',                hidden : true },
      { name : 'materialComponentNo', hidden : true },
      { name : 'materialId',          width : '125', align : 'left',  frozen : false, sortable : false, sorttype : 'text', editable : false },
      { name : 'materialId',          width : '100', align : 'left',  frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'select', editrules:{required : false }, formatter:'select', editoptions:{value : '${f:h(mMaterial)}', separator : ':::', delimiter : '|||'}},
      { name : 'materialQty',         width : '50',  align : 'right', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: ${materialQuantityMaxLength}}},
      { name : 'materialUnit',        width : '50',  align : 'left',  frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 10}},
    ];

  materialGrid.pkey = ['materialComponentNo'];

  materialGrid.prop.data = [];

  materialGrid.prop.width = 392;
  materialGrid.prop.height = 130;

  materialGrid.prop.caption = '<bean:message key="material"/>';
  materialGrid.prop.hidegrid = false;

  materialGrid.edit(${f:url('/')});

  materialGrid.prop.aftersavefuncRowEx = function(rowId) {
    setProcessGridCell("materials", materialGrid.exportJson());
  }

  $(materialGrid.gridId).jqGrid(materialGrid.prop);
}
</script>
