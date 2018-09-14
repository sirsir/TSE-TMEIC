<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="master.users"/>
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
          '<bean:message key="user.code"/>',
          '<bean:message key="user.name"/>',
          '<bean:message key="password"/>',
          '<bean:message key="authority"/>',
        ];

      grid.prop.colModel =
        [
          { name : 'id', key : true, hidden : true },
          { name : 'crud',           hidden : true },
          { name : 'userId',       width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: 10}},
          { name : 'userName',     width : '120', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: 50}},
          { name : 'userPassword', width : '100', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'text',   editrules:{required : true}, editoptions: {maxlength: 10}},
          { name : 'roleId',       width : '150', align : 'left', frozen : false, sortable : true, sorttype : 'text', editable : true, edittype : 'select', editrules:{required : true}, formatter:'select', editoptions:{value : '${f:h(roles)}', separator : ':::', delimiter : '|||'}},
        ];

      grid.pkey = ['userId'];

      grid.prop.data =
        [
          {'userId' : '0000000001', 'userName' : 'TEST USER1', 'userPassword' : '1234567890', 'roleId' : '2'},
          {'userId' : '0000000002', 'userName' : 'TEST USER2', 'userPassword' : '1234567890', 'roleId' : '1'},
          {'userId' : '0000000003', 'userName' : 'TEST USER3', 'userPassword' : '1234567890', 'roleId' : '1'},
          {'userId' : '0000000004', 'userName' : 'TEST USER4', 'userPassword' : '1234567890', 'roleId' : '0'},
          {'userId' : '0000000005', 'userName' : 'TEST USER5', 'userPassword' : '1234567890', 'roleId' : '0'},
        ];

      grid.prop.data = ${usersJson};
      grid.prop.width = 500;
      grid.prop.height = 520;

      grid.edit(${f:url('/')});

      $(grid.gridId).jqGrid(grid.prop);
    });

    function addRow() {
      grid.addRow();
    }

    function doSearch() {
      $("#mainForm").attr("action", "${f:url('/master/users/')}");
      $("#mainForm").submit();
    }

    function doRegister() {

      dialogUtils.confirm(
        "<bean:message key='info.register'/>",
        function() {
          if (!grid.prop.saveEditRow(grid.lastSelRowId)) return;

          var sendData = grid.getUpdateData();

          ajaxPostJson({
            url: "${f:url('/master/users/register/')}",
            data: {
              usersJson : JSON.stringify(sendData)
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
              url: "${f:url('/master/users/delete/')}",
              data: {
                deleteUserId : $(grid.gridId).getCell(rowId, "userId")
              }
            });
          }
        }
      );
    }
    </script>

    <s:form method="post" styleId="mainForm">

      <div style="max-width: 540px; text-align: right;">
        <input type="button" value="<bean:message key="add"/>" onclick="addRow()" />
        <input type="button" value="<bean:message key="register"/>" onclick="doRegister()" />
        <input type="button" value="<bean:message key="delete"/>" onclick="doDelete()" />
      </div>

      <div style="margin: 10px 0px;"></div>

      <table class="scroll" id="list"></table>

    </s:form>

  </tiles:put>

</tiles:insert>
