<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="master.material"/>
  </bean:define>

  <tiles:put name="title" value="${title}"/>
  <tiles:put name="content" type="string">

    <script type="text/javascript">

    var grid = new JqGridUtils("#list", "#pager");

    $(function() {

      grid.prop;

      grid.prop.colNames =
        [
          'ID',
          'CRUD',
          '<bean:message key="material.code"/>',
          '<bean:message key="material.name"/>',
        ];

      grid.prop.colModel =
        [
          { name : 'id', key : true, hidden : true },
          { name : 'crud',           hidden : true },
          { name : 'materialId',     width : '130', align : 'left', frozen : true, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: 10}},
          { name : 'materialName',   width : '300', align : 'left', frozen : true, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: 30}},
        ];

      grid.pkey = ['materialId'];

      grid.prop.data =
        [
          {'materialId' : '000000000000001', 'materialName' : 'TESTMATERIAL1'},
          {'materialId' : '000000000000002', 'materialName' : 'TESTMATERIAL2'},
          {'materialId' : '000000000000003', 'materialName' : 'TESTMATERIAL3'},
          {'materialId' : '000000000000004', 'materialName' : 'TESTMATERIAL4'},
          {'materialId' : '000000000000005', 'materialName' : 'TESTMATERIAL5'},
        ];

      grid.prop.data = ${materialJson};
      grid.prop.width = 487;
      grid.prop.height = 520;

      grid.edit(${f:url('/')});

      $(grid.gridId).jqGrid(grid.prop);
    });

    function addRow() {
      grid.addRow();
    }

    function doSearch() {
      $("#mainForm").attr("action", "${f:url('/master/material/')}");
      $("#mainForm").submit();
    }

    function doRegister() {

      dialogUtils.confirm(
        "<bean:message key='info.register'/>",
        function() {
          if (!grid.prop.saveEditRow(grid.lastSelRowId)) return;

          var sendData = grid.getUpdateData();

          ajaxPostJson({
            url: "${f:url('/master/material/register/')}",
            data: {
              materialJson : JSON.stringify(sendData)
            }
          });
        }
      );
    }

    function doDelete() {

      var rowId = grid.selrow();

      if (rowId == null) {
        dialogUtils.alert("<bean:message key='errors.not.select'/>");
        return false;
      }

      dialogUtils.confirm(
        "<bean:message key='info.delete'/>",
        function() {
          if (grid.deleteRow(rowId)) {
            return false;
          } else {
            ajaxPostJson({
              url: "${f:url('/master/material/delete/')}",
              data: {
                deleteMaterialId : $(grid.gridId).getCell(rowId, "materialId")
              }
            });
          }
        }
      );
    }
    </script>

    <s:form method="post" styleId="mainForm">

      <div style="max-width: 490px; text-align: right;">
        <input type="button" value="<bean:message key="add"/>" onclick="addRow()" />
        <input type="button" value="<bean:message key="register"/>" onclick="doRegister()" />
        <input type="button" value="<bean:message key="delete"/>" onclick="doDelete()" />
      </div>

      <div style="margin: 10px 0px;"></div>

      <table class="scroll" id="list"></table>

    </s:form>

  </tiles:put>

</tiles:insert>
