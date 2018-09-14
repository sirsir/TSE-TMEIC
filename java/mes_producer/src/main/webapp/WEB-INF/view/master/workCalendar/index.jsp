<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">

  <bean:define id="title">
    <bean:message key="master.work.calendar" />
  </bean:define>

  <tiles:put name="title" value="${title}" />
  <tiles:put name="content" type="string">

    <script type="text/javascript">

    var calendarGrid = new JqGridUtils("#calendarList", "#calendarPager");
    var breakGrid    = new JqGridUtils("#breakList", "#breakPager");

    $(function() {
      $("#updateButton").button();

      calendarGrid.prop;

      calendarGrid.prop.colNames =
        [
          'ID',
          'CRUD',
          'WCID',
          'WorkDay',
          '<bean:message key="day"/>',
          '<bean:message key="holiday"/>',
          '<bean:message key="start"/>',
          '<bean:message key="end"/>',
          '<bean:message key="start"/>',
          '<bean:message key="end"/>',
          '<bean:message key="start"/>',
          '<bean:message key="end"/>',
          '<bean:message key="start"/>',
          '<bean:message key="end"/>',
          '<bean:message key="start"/>',
          '<bean:message key="end"/>',
          '<bean:message key="start"/>',
          '<bean:message key="end"/>',
        ];

      calendarGrid.prop.colModel =
        [
          { name : 'id', key : true, hidden : true },
          { name : 'crud',           hidden : true },
          { name : 'workCalendarId', hidden : true },
          { name : 'workDay',        hidden : true },
          { name : 'workDayAndWeek',  width : '50', align : 'left', frozen : true,  sortable : false, sorttype : 'text', editable : false, edittype : 'text',      editrules:{required : true},  editoptions: {maxlength: 4}},
          { name : 'holiday',         width : '50', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'checkbox',  editrules:{required : false}, formatter: 'checkbox'
            , editoptions: { value:"true:false",  dataEvents :[
                                                               { type: 'change', fn: function(e)
                                                                 {
                                                                    var id;

                                                                    if (e.target.id) {
                                                                      id = e.target.id.split("_")[0];
                                                                    }

                                                                    if (!id) {
                                                                      return;
                                                                    }

                                                                    if (e.target.checked) {
                                                                      $(calendarGrid.gridId).setRowData(id, false, { background:'#c0c0c0' });
                                                                      return;
                                                                    }

                                                                    if (Number(id) % 2 == 0) {
                                                                      $(calendarGrid.gridId).setRowData(id, false, { background:'#FFFFFF' });
                                                                      return;
                                                                    }

                                                                    $(calendarGrid.gridId).setRowData(id, false, { background:'#CED6EA' });
                                                                 }
                                                              }
                                                          ]
                            }},
          { name : 'workStartTime',   width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'workEndTime',     width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakStartTime1', width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakEndTime1',   width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakStartTime2', width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakEndTime2',   width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakStartTime3', width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakEndTime3',   width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakStartTime4', width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakEndTime4',   width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakStartTime5', width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
          { name : 'breakEndTime5',   width : '55', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
        ];

      calendarGrid.pkey = ['workDayAndWeek'];

      calendarGrid.prop.data =
        [
          {'workCalendarId' : null,'workDay' : '20150501','workDayAndWeek' : '1(mon)', 'holiday' : 'true',  'workStartTime' : '0800', 'workEndTime' : '2200','breakStartTime1' : '1200', 'breakEndTime1' : '1300','breakStartTime2' : '1500', 'breakEndTime2' : '1515','breakStartTime3' : '1400', 'breakEndTime3' : '1415','breakStartTime4' : '1700', 'breakEndTime4' : '1715','breakStartTime5' : '1800', 'breakEndTime5' : '1830'},
//          {'workDayAndWeek' : ' 2(tue)', 'holiday' : 'No',  'workStartTime' : '0900', 'workEndTime' : '1800','breakStartTime1' : '1200', 'breakEndTime1' : '1300'},
//          {'workDayAndWeek' : ' 3(wed)', 'holiday' : 'Yes', 'workStartTime' : '0900', 'workEndTime' : '1800','breakStartTime1' : '1200', 'breakEndTime1' : '1300'},
//          {'workDayAndWeek' : ' 4(thu)', 'holiday' : 'Yes', 'workStartTime' : '0900', 'workEndTime' : '1800','breakStartTime1' : '1200', 'breakEndTime1' : '1300'},
//          {'workDayAndWeek' : ' 5(fri)', 'holiday' : 'Yes', 'workStartTime' : '0900', 'workEndTime' : '1800','breakStartTime1' : '1200', 'breakEndTime1' : '1300'},
//          {'workDayAndWeek' : ' 6(sat)', 'holiday' : 'Yes', 'workStartTime' : '0900', 'workEndTime' : '1800','breakStartTime1' : '1200', 'breakEndTime1' : '1300'},
//          {'workDayAndWeek' : ' 7(sun)', 'holiday' : 'Yes', 'workStartTime' : '0900', 'workEndTime' : '1800','breakStartTime1' : '1200', 'breakEndTime1' : '1300'},
        ];

      calendarGrid.prop.data = ${workCalendarJson};
      calendarGrid.prop.width = 827;
      calendarGrid.prop.height = 714;
      calendarGrid.prop.rownumbers = false;

      calendarGrid.groupHeader = (function() {
        $(calendarGrid.gridId).jqGrid('setGroupHeaders', {
          useColSpanStyle : true,
          groupHeaders : [
                          {startColumnName: 'workStartTime',   numberOfColumns: 2, titleText: '<em><bean:message key="work.time"/></em>'},
                          {startColumnName: 'breakStartTime1', numberOfColumns: 2, titleText: '<em><bean:message key="break.time"/>1</em>'},
                          {startColumnName: 'breakStartTime2', numberOfColumns: 2, titleText: '<em><bean:message key="break.time"/>2</em>'},
                          {startColumnName: 'breakStartTime3', numberOfColumns: 2, titleText: '<em><bean:message key="break.time"/>3</em>'},
                          {startColumnName: 'breakStartTime4', numberOfColumns: 2, titleText: '<em><bean:message key="break.time"/>4</em>'},
                          {startColumnName: 'breakStartTime5', numberOfColumns: 2, titleText: '<em><bean:message key="break.time"/>5</em>'},
                         ]
        });
      });

      calendarGrid.edit(${f:url('/')});

      var defaultGridComplete = calendarGrid.prop.gridComplete;

      calendarGrid.prop.gridComplete = function () {

        var ids = $(calendarGrid.gridId).jqGrid('getDataIDs');

        for (var i = 0; i < ids.length; i++) {

          if ($(calendarGrid.gridId).getCell(ids[i], "holiday") == "true") {
            $(calendarGrid.gridId).setRowData(ids[i], false, { background:'#c0c0c0' });
          }
        }
        defaultGridComplete();

      };

      $(calendarGrid.gridId).jqGrid(calendarGrid.prop);

      $(calendarGrid.gridId).find("tr.ui-row-ltr").children("td").css("height", "20px");
    });

    function doSearch() {
      $("#mainForm").attr("action", "${f:url('/master/workCalendar/')}");
      $("#mainForm").submit();
    }

    function update() {
      if (calendarGrid.isEditing()) {
        dialogUtils.confirm(
          "<bean:message key='info.editing'/>",
            function() {
              doSearch();
            });
      }else{
        doSearch();
      }
    }

    function doChangeMonth(y, m) {
        $("#changeYear").val(y);
        $("#changeMonth").val(m);
        doSearch();
    }

    function checkBackMonth() {
      var y = parseInt($("#year").val());
      var m = parseInt($("#month").val());
      if (m != 1) {
        m = m - 1;
      } else {
        y = y - 1;
        m = 12;
      }
      doChangeMonth(String(y), ("0" + String(m)).slice(-2));
    }

    function checkEditAndBackMonth() {
      if (calendarGrid.isEditing()) {
        dialogUtils.confirm(
          "<bean:message key='info.editing'/>",
            function() { checkBackMonth(); });
      } else {
        checkBackMonth();
      }
    }

    function chackNextMonth() {
      var y = parseInt($("#year").val());
      var m = parseInt($("#month").val());
      var year, month;
      var yearMax = $("#year").children().length;

      if (m != 12) {
        m = m + 1;
      } else {
        y = y + 1;
        m = 1;
      }
      doChangeMonth(String(y), ("0" + String(m)).slice(-2));
    }

    function checkEditAndNextMonth() {
      if (calendarGrid.isEditing()) {
        dialogUtils.confirm(
          "<bean:message key='info.editing'/>",
          function() { chackNextMonth(); });
      } else {
        chackNextMonth();
      }
    }

    function doRegister() {

      dialogUtils.confirm(
        "<bean:message key='info.register'/>",
        function() {
          if (!calendarGrid.prop.saveEditRow(calendarGrid.lastSelRowId)) return;

          var sendData = calendarGrid.getUpdateData();

          ajaxPostJson({
            url: "${f:url('/master/workCalendar/register/')}",
            data: {
              workCalendarJson : JSON.stringify(sendData)
            }
          });
        }
      );
    }
    </script>

    <s:form method="post" styleId="mainForm">

      <div style="display: table; min-width: 830px;">
        <div style="display: table-cell;">
          <bean:message key="setting.month"/>

          <html:select styleId="year" property="year">
            <html:optionsCollection property="yearOptionItems" />
          </html:select>
          <bean:message key="year"/>

          <html:select styleId="month" property="month">
            <html:optionsCollection property="monthOptionItems" />
          </html:select>
          <bean:message key="month"/>

          <html:hidden property="changeYear" styleId="changeYear" />
          <html:hidden property="changeMonth" styleId="changeMonth" />

          <tags:SearchButton id="updateButton" onclick="update()"/>
          <input type="button" value="<bean:message key="month.prev"/>" onclick="checkEditAndBackMonth()" style="margin-left: 10px;"/>
          <input type="button" value="<bean:message key="month.next"/>" onclick="checkEditAndNextMonth()" />
        </div>
        <div style="display: table-cell; text-align: right;">
          <input type="button" value="<bean:message key="default.input"/>" onclick="inputDefault()" />
          <input type="button" value="<bean:message key="register"/>" onclick="doRegister()" />
        </div>
      </div>

      <div style="margin: 5px 0px;"></div>

      <table class="scroll" id="calendarList"></table>

      <jsp:include page="defaultDialog.jsp"/>

    </s:form>

  </tiles:put>
</tiles:insert>
