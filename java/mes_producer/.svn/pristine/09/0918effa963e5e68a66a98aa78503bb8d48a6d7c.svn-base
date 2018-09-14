<script type="text/javascript">

var breakGrid    = new JqGridUtils("#breakList", "#breakPager");

$(function() {

  <%-- 休憩時間 --%>

  breakGrid.prop;

  breakGrid.prop.colNames =
    [
      'ID',
      '<bean:message key="start.time" />',
      '<bean:message key="end.time" />',
    ];

  breakGrid.prop.colModel =
    [
      { name : 'id', key : true, hidden : true },
      { name : 'breakStartTime', width : '80', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
      { name : 'breakEndTime',   width : '80', align : 'left', frozen : false, sortable : false, sorttype : 'text', editable : true,  edittype : 'text',      editrules:{required : false}, editoptions: {maxlength: 4, dataInit: function (el) {calendarGrid.timePickerFmatter(el, "HHmm");}}},
    ];

  breakGrid.prop.data =
    [
      {'breakStartTime' : null,'breakEndTime' : null},
      {'breakStartTime' : null,'breakEndTime' : null},
      {'breakStartTime' : null,'breakEndTime' : null},
      {'breakStartTime' : null,'breakEndTime' : null},
      {'breakStartTime' : null,'breakEndTime' : null},
    ];

  breakGrid.prop.width  = 200;
  breakGrid.prop.height = 130;

  breakGrid.edit(${f:url('/')});

  $(breakGrid.gridId).jqGrid(breakGrid.prop);

  datetimepicker($("#workStartTime"));
  datetimepicker($("#workEndTime"));

});

function checkInputTimes() {

  if (!checkStartAndEndTime($('#workStartTime').val(), $('#workEndTime').val())) {
    return false;
  }

  var breakIds = $(breakGrid.gridId).jqGrid('getDataIDs');

  for (var i = 0; i < breakIds.length; i++) {

    var startTime = $(breakGrid.gridId).getCell(breakIds[i], "breakStartTime");
    var endTime = $(breakGrid.gridId).getCell(breakIds[i], "breakEndTime");

    if (!checkStartAndEndTime(startTime, endTime)) {
      return false;
    }

	var breakTimeName = "<bean:message key='break.time'/>";

	if ((startTime.length > 0 && endTime.length == 0)) {
      dialogUtils.alert("<bean:message key='errors.invalid' arg0='" + breakTimeName + "'/>");
      return false;
    }

    if (startTime.length == 0 && endTime.length > 0) {
      dialogUtils.alert("<bean:message key='errors.invalid' arg0='" + breakTimeName + "'/>");
      return false;
    }
  }
  return true;
}

function checkStartAndEndTime(startTime, endTime) {

  if (!checkTime(startTime)) {
    return false;
  }

  if (!checkTime(endTime)) {
    return false;
  }

  if (startTime.length == 4 && endTime.length == 4) {
    if (Number(startTime) >=  Number(endTime)) {

      <bean:define id="workCalendarBreakTime">
        <bean:message key="break.time" />
      </bean:define>

      dialogUtils.alert("<bean:message key='errors.out.of.range' arg0='${workCalendarBreakTime}'/>");
      return false;
    }
  }

  return true;
}

function checkTime(time) {
  if (time.length == 4) {
    if (time.match(/[^0-9]+/)) {
      dialogUtils.alert("<bean:message key='errors.integer' arg0='" + time + "' />");
      return false;
    }
  } else {
    if (time.length != 0) {
      // 桁数エラー
      dialogUtils.alert("<bean:message key='errors.invalid' arg0='" + time + "'/>");
      return false;
    }
  }
  return true;
}

function inputDefault() {

  $("#settingDate").text($("#year").val() + "<bean:message key='year'/>" + $("#month").val() + "<bean:message key='month'/>");

  for (var i=0 ; i<=7 ; i++){
    if (weeks[i]==1){
      $("#weekBtn" + String(i)).css("background-color","#ff0000");
    } else {
      $("#weekBtn" + String(i)).css("background-color","#32cd32");
    }
  }

  var dialogProp = dialogUtils.defaultProp();
  dialogProp.title = "<bean:message key='default.input.unit.month'/>";
  dialogProp.minWidth = 400;
  dialogProp.minHeight = 400;
  dialogProp.buttons = [
                         { text: '<bean:message key="reflect"/>',
                           width: 100,
                           click: function() {

                             if (!breakGrid.prop.saveEditRow(breakGrid.lastSelRowId)) return;

                             if (checkInputTimes()) {
                               var ids = $(calendarGrid.gridId).jqGrid('getDataIDs');

                               for (var i = 0; i < ids.length; i++) {

                                 var date = new Date($(calendarGrid.gridId).getCell(ids[i], 'workDay'));

                                 setDefaultCalendar(ids[i], "crud", "U");

                                 if (weeks[date.getDay()] == 0) {
                                   setDefaultCalendar(ids[i], "holiday", "false");
                                   setDefaultCalendar(ids[i], "workStartTime", $("#workStartTime").val());
                                   setDefaultCalendar(ids[i], "workEndTime", $("#workEndTime").val());
                                   setDefaultCalendar(ids[i], "breakStartTime1", $(breakGrid.gridId).getCell("1", "breakStartTime"));
                                   setDefaultCalendar(ids[i], "breakEndTime1", $(breakGrid.gridId).getCell("1", "breakEndTime"));
                                   setDefaultCalendar(ids[i], "breakStartTime2", $(breakGrid.gridId).getCell("2", "breakStartTime"));
                                   setDefaultCalendar(ids[i], "breakEndTime2", $(breakGrid.gridId).getCell("2", "breakEndTime"));
                                   setDefaultCalendar(ids[i], "breakStartTime3", $(breakGrid.gridId).getCell("3", "breakStartTime"));
                                   setDefaultCalendar(ids[i], "breakEndTime3", $(breakGrid.gridId).getCell("3", "breakEndTime"));
                                   setDefaultCalendar(ids[i], "breakStartTime4", $(breakGrid.gridId).getCell("4", "breakStartTime"));
                                   setDefaultCalendar(ids[i], "breakEndTime4", $(breakGrid.gridId).getCell("4", "breakEndTime"));
                                   setDefaultCalendar(ids[i], "breakStartTime5", $(breakGrid.gridId).getCell("5", "breakStartTime"));
                                   setDefaultCalendar(ids[i], "breakEndTime5", $(breakGrid.gridId).getCell("5", "breakEndTime"));

                                 } else {
                                   //休日
                                   setDefaultCalendar(ids[i], "holiday", "true");
                                   setDefaultCalendar(ids[i], "workStartTime", "");
                                   setDefaultCalendar(ids[i], "workEndTime", "");
                                   setDefaultCalendar(ids[i], "breakStartTime1", "");
                                   setDefaultCalendar(ids[i], "breakEndTime1", "");
                                   setDefaultCalendar(ids[i], "breakStartTime2", "");
                                   setDefaultCalendar(ids[i], "breakEndTime2", "");
                                   setDefaultCalendar(ids[i], "breakStartTime3", "");
                                   setDefaultCalendar(ids[i], "breakEndTime3", "");
                                   setDefaultCalendar(ids[i], "breakStartTime4", "");
                                   setDefaultCalendar(ids[i], "breakEndTime4", "");
                                   setDefaultCalendar(ids[i], "breakStartTime5", "");
                                   setDefaultCalendar(ids[i], "breakEndTime5", "");
                                 }
                               }

                               $(calendarGrid.gridId).trigger("reloadGrid");
                               $(calendarGrid.gridId).find("tr.ui-row-ltr").children("td").css("height", "20px");
                               $("#defaultDialog").dialog("close");
                             }
                           }
                         },
                         { text: '<bean:message key="cancel"/>',
                           width: 100,
                           click: function() {
                             clearEditing();
                             $("#defaultDialog").dialog("close");
                           }
                         }
                       ];

  $("#defaultDialog").dialog(dialogProp);
}

function clearEditing() {
  if (breakGrid.lastsel != null) {
    $(breakGrid.gridId).restoreRow(breakGrid.lastsel);
    $(breakGrid.gridId).resetSelection();
    $(breakGrid.gridId).trigger("reloadGrid");
  }
}

function setDefaultCalendar(rowId, cellName, val) {

  if (!val) val = null;

  $(calendarGrid.gridId).setCell(rowId, cellName, val);
}

var weeks = new Array(7);

for (var i=0 ; i<=7 ; i++){
  weeks[i] = 0;
}

function setHoliday(weekId) {
  if (weeks[weekId]==0){
    $("#weekBtn" + String(weekId)).css("background-color","#ff0000");
    weeks[weekId]=1;
  } else {
    $("#weekBtn" + String(weekId)).css("background-color","#32cd32");
    weeks[weekId]=0;
  }
}
</script>

<style type="text/css">
.weekBtn {
  position: static;
  padding: 5px;
  border-style: none;
  color: #ffffff;
  background-color: #32cd32;
  width: 40px;
  cursor: pointer;
}
</style>

<div id="defaultDialog" style="display: none;">
  <div id="settingDate" style="padding-top: 10px;"></div>
  <div style="padding-top: 10px;"></div>

  <img src="${f:url('/img/square.png')}" style="width: 10px;">
  <span><bean:message key="holiday.setting"/></span>
  <table style="border: 1; border-style: solid;  border-color: #A3A3A3;">
    <tr>
      <td>
        <bean:message key="weekly"/>
        <span style="margin-left: 1px;"></span>
        <c:forEach var="weekdayName" varStatus="status" items="${ weekdayNames }">
          <button type="button" class="weekBtn" id="weekBtn${status.index}" onclick="setHoliday(${status.index})"><div>${ weekdayName }</div></button>
        </c:forEach>
      </td>
    </tr>
  </table>
  <div style="padding-top: 10px;"></div>

  <img src="${f:url('/img/square.png')}" style="width: 10px;">
  <span><bean:message key="work.time.setting"/></span>
  <div>
    <input type="text" id="workStartTime" style="width: 100px;" maxlength="4" />
    ～
    <input type="text" id="workEndTime" style="width: 100px;" maxlength="4" />
  </div>
  <div style="padding-top: 10px;"></div>

  <img src="${f:url('/img/square.png')}" style="width: 10px;">
  <span><bean:message key="break.time.setting"/></span>
  <table id="breakList"></table>
</div>

