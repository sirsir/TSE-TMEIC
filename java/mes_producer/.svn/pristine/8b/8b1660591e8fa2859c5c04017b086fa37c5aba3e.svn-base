<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="product.progress"/>
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

      // 表示期間変更
      function doSearch() {
        $("#mainForm").attr("action", "${f:url('/product/productProgress/')}");
        $("#mainForm").submit();
      }

      $(function () {
        $("#updateButton").button();
        $("#displayStartDate").datepicker({dateFormat: "dd/mm/yy"});
        $("#displayEndDate").datepicker({dateFormat: "dd/mm/yy"});

        options.data = ${progressJson};
        options.start = new Date.parse("${displayGanttStartDate}");
        options.end = new Date.parse("${displayGanttEndDate}");
        options.page = ${page};
        options.pageTotalCount = ${pageTotalCount};
        options.clicked = function (o) {
                            selectedObj = o;
                            var data = selectedObj.data('block-data');

                            if (typeof(data) == "undefined") {
                              var pageData = selectedObj.data('page-data');

                              if (typeof(pageData) == "undefined") {
                                return false;
                              }
                              $("#page").val(pageData.page);
                              doSearch();
                              return false;
                            }

                            var itemId = data.itemId;
                            var itemName = data.itemName ? data.itemName : "";
                            var seriesName = data.seriesName ? data.seriesName: "";
                            var text = data.text ? data.text : "";

                            var sYYYY = data.start.getYear();
                            sYYYY = sYYYY < 1000 ? sYYYY + 1900 : sYYYY;
                            var sMM = data.start.getMonth() + 1;
                            var sDD = data.start.getDate();

                            var eYYYY = data.end.getYear();
                            eYYYY = eYYYY < 1000 ? eYYYY + 1900 : eYYYY;
                            var eMM = data.end.getMonth() + 1;
                            var eDD = data.end.getDate();

                            $('#ganttData-item-id').val(itemId);
                            $('#ganttData-item-name').val(itemName);
                            $('#ganttData-series-id').val(data.seriesId);
                            $('#ganttData-series-name').val(seriesName);
                            $('#ganttData-series-start').val(sYYYY + "/" + sMM + "/" + sDD);
                            $('#ganttData-series-end').val(eYYYY + "/" + eMM + "/" + eDD);
                            $('#ganttData-series-text').val(text);
							
                            $("#selectProductPlanNo").val(itemId);
                            $("#page").val("");
                            $("#pageTotalCount").val("");

                            $("#mainForm").attr("action", "${f:url('/product/productProgressProcess/')}");
                            $("#mainForm").submit();
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
            <bean:message key="product.manifacturedate"/>
            <html:text property="displayStartDate" styleId="displayStartDate" style=" width: 100px;" />
            ～
            <html:text property="displayEndDate" styleId="displayEndDate" style=" width: 100px; margin-right: 5px;" />
            <tags:SearchButton id="updateButton" onclick="doSearch()"/>
          </div>
          <tags:ProgressLabel/>
          <div style="text-align: right;">
          </div>
        </div>
      </div>

      <div style="padding-top: 5px;"></div>

      <html:hidden property="selectProductPlanNo" styleId="selectProductPlanNo" />
      <html:hidden property="isAutoUpdating" styleId="isAutoUpdating" />
      <html:hidden property="page" styleId="page"  />
      <html:hidden property="pageTotalCount" styleId="pageTotalCount" />

      <div style="padding-top: 10px;"></div>

      <%-- グラフ表示 --%>
      <div id="ganttChart"></div>

    </s:form>

  </tiles:put>

</tiles:insert>
