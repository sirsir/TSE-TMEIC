<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">

function createProductGrid() {

  productGrid.prop;

  productGrid.prop.colNames =
    [
      'ID',
      'CRUD',
      '<bean:message key="product.manifacturedate"/>',
      '<bean:message key="plan.no"/>',
      '<bean:message key="part.no"/>',
      '<bean:message key="part.name"/>',
      '<bean:message key="product.customer.name"/>',
      '<bean:message key="product.model"/>',
    ];

  productGrid.prop.colModel =
    [
      { name : 'id', key : true, hidden : true },
      { name : 'crud',           hidden : true },
      { name : 'manufactureDate', width : '110', align : 'center', frozen : true, sortable : false, sorttype : 'text', editable : false},
      { name : 'productPlanNo',   width : '110', align : 'center', frozen : true, sortable : false, sorttype : 'text', editable : false},
      { name : 'partNo',          width : '140', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'partName',        width : '140', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'customerName',    width : '140', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
      { name : 'model',       	  width : '140', align : 'left',   frozen : true, sortable : true, sorttype : 'text', editable : false},
    ];

  productGrid.prop.data =
    [
      {'manufactureDate' : '2015/05/11', 'productPlanNo' : '1505110001', 'partNo' : '1','partName': 'xxxx','customerName': 'xxxx','model': 'xxxx'},
    ];

  productGrid.prop.data = [${productJson}];

  productGrid.prop.width = 810;
  productGrid.prop.height = 26;

  productGrid.prop.rownumbers = false;

  productGrid.edit(${f:url('/')});

  $(productGrid.gridId).jqGrid(productGrid.prop);
}

</script>
