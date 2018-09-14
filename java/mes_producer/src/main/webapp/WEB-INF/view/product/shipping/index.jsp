<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="shipping"/>
  </bean:define>

  <tiles:put name="title" value="${title}"/>
  <tiles:put name="content" type="string">

    <jsp:include page="grid.jsp"/>

    <script type="text/javascript">

    var requiredOfShippingNo = true;

    if (${shippingNoNumberingMethod} == "1") {
      requiredOfShippingNo = false;
    }

    $(function() {
      $("#updateButton").button();
      $("#displayStartDate").datepicker({dateFormat: "dd/mm/yy"});
      $("#displayEndDate").datepicker({dateFormat: "dd/mm/yy"});

      createGrid();
    });


    function doSearch() {
      $("#mainForm").attr("action", "${f:url('/product/shipping/')}");
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
          var sendData = grid.getUpdateData();
          ajaxPostJson({
            url: "${f:url('/product/shipping/register/')}",
            data: {
              displayStartDate : $("#displayStartDate").val(),
              displayEndDate : $("#displayEndDate").val(),
              shippingJson : JSON.stringify(sendData)
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
              url: "${f:url('/product/shipping/delete/')}",
              data: {
                deleteShippingNo : $(grid.gridId).getCell(rowId, "shippingNo")
              }
            });
          }
        }
      );
    }
    </script>

    <s:form method="post" styleId="mainForm">

      <div style="position: absolute; margin-left: 5px;">
        <bean:message key="shipping.date"/>
        <html:text property="displayStartDate" styleId="displayStartDate" style=" width: 100px; margin-left: 5px;" />
        ～
        <html:text property="displayEndDate" styleId="displayEndDate" style=" width: 100px; margin-right: 5px;" />
        <button type="button" id="updateButton" onclick="update()" style="width: 40px;">
          <img src="${f:url('/css/images/update.png')}">
        </button>
      </div>

      <div style="margin-left: 995px;">
        <input type="button" value="<bean:message key="add"/>" onclick="addRow()" />
        <input type="button" value="<bean:message key="register"/>" onclick="doRegister()" />
        <input type="button" value="<bean:message key="delete"/>" onclick="doDelete()" />
      </div>

      <div style="margin: 10px 0px;"></div>

      <table class="scroll" id="list"></table>
      <div class="scroll" id="pager" style="text-align: center;"></div>

    </s:form>

  </tiles:put>

</tiles:insert>
