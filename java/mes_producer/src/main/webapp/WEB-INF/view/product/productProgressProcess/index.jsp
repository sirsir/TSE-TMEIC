<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="product.progress.process"/>
  </bean:define>

  <tiles:put name="title" value="${title}"/>
  <tiles:put name="titleRight">
    <input type="button" id="autoUpdateButton" onclick="onclickAutoUpdateButton()" style="width: 225px; margin-left: 60px;" />
  </tiles:put>
  <tiles:put name="content" type="string">

    <script type="text/javascript" src="${f:url('/js/timerUtils.js')}"></script>

    <link rel="stylesheet" type="text/css" media="screen" href="${f:url('/css/gantt/jquery.ganttView.css')}" />
    <script type="text/javascript" src="${f:url('/js/gantt/date.js')}"></script>
    <script type="text/javascript" src="${f:url('/js/gantt/holidays.js')}"></script>
    <script type="text/javascript" src="${f:url('/js/gantt/jquery.ganttView.js')}"></script>
    <script type="text/javascript" src="${f:url('/js/progress.js')}"></script>
    <script type="text/javascript">

      btnLabel = ['<bean:message key="automatic.update.restart"/>', '<bean:message key="automatic.update.stop"/>'];

      //自動更新設定
      interval = parseInt(${interval});

      // 表示更新
      function doSearch() {
        $("#mainForm").attr("action", "${f:url('/product/productProgressProcess/')}");
        $("#mainForm").submit();
      }

      function doProductProgress() {
        $("#mainForm").attr("action", "${f:url('/product/productProgress/')}");
        $("#mainForm").submit();
      }

      $(function () {

        options.data = ${progressJson};
        options.start = new Date.parse("${displayGanttStartDate}");
        options.end = new Date.parse("${displayGanttEndDate}");
        options.page = ${page};
        options.pageTotalCount = ${pageTotalCount};
        options.clicked = function (o) {
                            selectedObj = o;
                            var pageData = selectedObj.data('page-data');

                            if (typeof(pageData) == "undefined") {
                              return false;
                            }

                            $("#page").val(pageData.page);
                            doSearch();
                            return false;
        };

        if ("${displayGanttStartDate}".length != 0) {
          $("#ganttChart").ganttView(options);
        }

		var isAutoUpdatingBeforeSubmit = ${isAutoUpdating};

        timer = new timerUtils(interval, autoUpdate);
        $("#autoUpdateButton").val(btnLabel[isAutoUpdatingBeforeSubmit]);
        changeAutoUpdateMode((isAutoUpdatingBeforeSubmit == 1));

        $(window).on('resize', $.debounce (0, function(){
          resizeGanttChart();
          })
        );

        resizeGanttChart();
      });

    </script>

    <s:form method="post" styleId="mainForm">

      <div class="tableStyle" style="padding-left: 30px; width: 100%;">
        <div>
          <div style="width: 350px;">
            <table style="margin-top: 5px; font-size: 13px; border-collapse: collapse;">
              <tr style="background: #CED6EA; background-color: #2E75B6; color: #FFFFFF;">
                <th style="width: 100px; border: solid 1px #A3A3A3;"><bean:message key="plan.no"/></th>
                <th style="width: 200px; border: solid 1px #A3A3A3;"><bean:message key="product.name"/></th>
              </tr>
              <tr style="background: #CED6EA;">
                <td style="width: 100px; border: solid 1px #A3A3A3;">
                  ${selectProductPlanNo}
                </td>
                <td style="width: 200px; border: solid 1px #A3A3A3;">${productName}</td>
              </tr>
            </table>
          </div>
          <tags:ProgressLabel/>
          <div style="text-align: right;">
            <input type="button" id="btnProductProgress" onclick="doProductProgress()" value="<bean:message key="product.progress"/>" style="width: 225px; margin-left: 60px;" />
          </div>
        </div>
      </div>

      <html:hidden property="selectProductPlanNo" styleId="selectProductPlanNo" />
      <html:hidden property="isAutoUpdating" styleId="isAutoUpdating" />
      <html:hidden property="page" styleId="page"  />
      <html:hidden property="pageTotalCount" styleId="pageTotalCount" />

      <div style="padding-top: 5px;"></div>

      <%-- グラフ表示 --%>
      <div id="ganttChart"></div>

    </s:form>

  </tiles:put>

</tiles:insert>
