<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="master.spec" />
  </bean:define>

  <tiles:put name="title" value="${title}" />
  <tiles:put name="content" type="string">

    <script type="text/javascript">

    var grid = new JqGridUtils("#list", "#pager");

    $(function() {

      grid.prop;

      grid.prop.colNames =
        [
          'ID',
          'CRUD',
          '<bean:message key="spec.code"/>',
          '<bean:message key="spec.name"/>',
          '<bean:message key="attribute"/>',
          '1','2','3','4','5','6','7','8','9','10',
        ];

      grid.prop.colModel =
        [
          { name : 'id', key : true, hidden : true, frozen : true },
          { name : 'crud',           hidden : true, frozen : true },
          { name : 'specId',          width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: 10}},
          { name : 'specName',        width : '120', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: 30}},
          { name : 'specAttributeId', width : '120', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'select', editrules:{required : true}, formatter:'select', editoptions:{value : '${f:h(attributes)}', separator : ':::', delimiter : '|||'}},
          { name : 'specParts01',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts02',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts03',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts04',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts05',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts06',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts07',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts08',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts09',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
          { name : 'specParts10',     width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : false}, editoptions: {maxlength: 7}},
        ];

      grid.pkey = ['specId'];

      grid.prop.data =
        [
         {'specId' : '0000000001', 'specName' : 'TESTNAME1', 'specAttributeId' : '2','specParts01' : '','specParts02' : '','specParts03' : '','specParts04' : '','specParts05' : '','specParts06' : '','specParts07' : '','specParts08' : '','specParts09' : '','specParts101' : ''},
         {'specId' : '0000000002', 'specName' : 'TESTNAME2', 'specAttributeId' : '1','specParts01' : '','specParts02' : '','specParts03' : '','specParts04' : '','specParts05' : '','specParts06' : '','specParts07' : '','specParts08' : '','specParts09' : '','specParts101' : ''},
         {'specId' : '0000000003', 'specName' : 'TESTNAME3', 'specAttributeId' : '0','specParts01' : '','specParts02' : '','specParts03' : '','specParts04' : '','specParts05' : '','specParts06' : '','specParts07' : '','specParts08' : '','specParts09' : '','specParts101' : ''},
         {'specId' : '0000000004', 'specName' : 'TESTNAME4', 'specAttributeId' : '3','specParts01' : '','specParts02' : '','specParts03' : '','specParts04' : '','specParts05' : '','specParts06' : '','specParts07' : '','specParts08' : '','specParts09' : '','specParts101' : ''},
         {'specId' : '0000000005', 'specName' : 'TESTNAME5', 'specAttributeId' : '4','specParts01' : '','specParts02' : '','specParts03' : '','specParts04' : '','specParts05' : '','specParts06' : '','specParts07' : '','specParts08' : '','specParts09' : '','specParts101' : ''},
        ];

      grid.prop.data = ${specJson};
      grid.prop.width = 1100;
      grid.prop.height = 520;

      grid.groupHeader = (function() {
        $(grid.gridId).jqGrid('setGroupHeaders', {
          useColSpanStyle : true,
          groupHeaders : [
                           { startColumnName : 'specParts01',
                             numberOfColumns : 10,
                             titleText       : '<bean:message key="parts"/>'
                           }
                         ]
        });
      });

      grid.edit(${f:url('/')});

      $(grid.gridId).jqGrid(grid.prop);
    });

    function addRow() {
      grid.addRow();
    }

    function doSearch() {
      $("#mainForm").attr("action", "${f:url('/master/spec/')}");
      $("#mainForm").submit();
    }

    function doRegister() {

      dialogUtils.confirm(
        "<bean:message key='info.register'/>",
        function() {
          if (!grid.prop.saveEditRow(grid.lastSelRowId)) return;

          var sendData = grid.getUpdateData();

          ajaxPostJson({
            url: "${f:url('/master/spec/register/')}",
            data: {
              specJson : JSON.stringify(sendData)
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
              url: "${f:url('/master/spec/delete/')}",
              data: {
                deleteSpecId : $(grid.gridId).getCell(rowId, "specId")
              }
            });
          }
        }
      );
    }
    </script>


    <s:form method="post" styleId="mainForm">

      <div style="max-width: 1450px; text-align: right;">
        <input type="button" value="<bean:message key="add"/>" onclick="addRow()" />
        <input type="button" value="<bean:message key="register"/>" onclick="doRegister()" />
        <input type="button" value="<bean:message key="delete"/>" onclick="doDelete()" />
      </div>

      <div style="margin: 10px 0px;"></div>

      <table class="scroll" id="list"></table>

    </s:form>
  </tiles:put>

</tiles:insert>
