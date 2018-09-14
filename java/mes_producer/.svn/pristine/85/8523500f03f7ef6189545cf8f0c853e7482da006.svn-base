<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="product.plan"/>
  </bean:define>

  <tiles:put name="title" value="${title}"/>
  <tiles:put name="content" type="string">

    <jsp:include page="grid.jsp"/>

    <script type="text/javascript">

    var requiredOfProductPlanNo = true;

    if (${planNoNumberingMethod} == "1") {
      requiredOfProductPlanNo = false;
    }

    $(function() {
      $("#updateButton").button();
      $("#displayStartDate").datepicker({dateFormat: "dd/mm/yy"});
      $("#displayEndDate").datepicker({dateFormat: "dd/mm/yy"});

      createGrid();
    });


    function doSearch() {
      $("#mainForm").attr("action", "${f:url('/product/productPlan/')}");
      $("#mainForm").submit();
    }

    function update() {

      if (typeof(grid) == "undefined") {
        doSearch();
      }

      if (grid.isEditing()) {

        dialogUtils.confirm(
          "<bean:message key='info.editing'/>",
            function() {
            	doSearch();
            });

      } else {
        doSearch();
      }
    }

    function addRow() {
      grid.addRow();
    }

    function doRegister() {

      dialogUtils.confirm(
        "<bean:message key='info.register'/>",
        function() {
          if (!grid.prop.saveEditRow(grid.lastSelRowId)) return;

          var sendData = grid.getUpdateData();

          ajaxPostJson({
            url: "${f:url('/product/productPlan/register/')}",
            data: {
              displayStartDate : $("#displayStartDate").val(),
              displayEndDate : $("#displayEndDate").val(),
              productPlanJson : JSON.stringify(sendData)
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
              url: "${f:url('/product/productPlan/delete/')}",
              data: {
                deleteProductPlanNo : $(grid.gridId).getCell(rowId, "productPlanNo")
              }
            });
          }
        }
      );
    }
    </script>

    <s:form method="post" styleId="mainForm">

      <div style="display: table; min-width: 1185px;">
        <div style="display: table-cell;">
          <bean:message key="product.manifacturedate"/>
          <html:text property="displayStartDate" styleId="displayStartDate" style=" width: 100px; margin-left: 5px;" />
          ～
          <html:text property="displayEndDate" styleId="displayEndDate" style=" width: 100px; margin-right: 5px;" />
          <tags:SearchButton id="updateButton" onclick="update()"/>
        </div>
        <div style="display: table-cell; text-align: right;">
          <input type="button" value="<bean:message key="add"/>" onclick="addRow()" />
          <input type="button" value="<bean:message key="register"/>" onclick="doRegister()" />
          <input type="button" value="<bean:message key="delete"/>" onclick="doDelete()" />
        </div>
      </div>

      <div style="margin: 10px 0px;"></div>

      <table class="scroll" id="list"></table>
      <div class="scroll" id="pager" style="text-align: center;"></div>

    </s:form>

  </tiles:put>

</tiles:insert>
