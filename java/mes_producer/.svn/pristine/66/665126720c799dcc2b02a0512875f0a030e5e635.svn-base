var isAutoUpdating = false;
var timer = null;
var btnLabel = [];
var interval = 0;

var options = {
  data: null,
  start: null,
  end: null,
  cellWidth: 61,
  slideWidth: 1555,
  showHolidays: true,
  excludeHolidays: true,
  page: 1,
  pageTotalCount: 1,
  clicked: null,
  dblClicked: function (o) {
    var selectedObj = o;
    if (selectedObj == null) { return; }

    if (selectedObj.data('block-data').seriesId == o.data('block-data').seriesId) {
      $('#ganttData-reset').trigger("click");
    }
  },
  changed: function (o) {
    $("#page").val(o.val());
    doSearch();
  }
};

var autoUpdate = function () {
  var nextPage = $("#ganttChart").getNextPage();
  $("#page").val(nextPage);
  doSearch();
}

function changeAutoUpdateMode(doAutoUpdate) {

  if (timer == null){
    return;
  }

  if (doAutoUpdate) {
    timer.start();
  } else {
    timer.stop();
  }
  isAutoUpdating = doAutoUpdate;
  $("#autoUpdateButton").val(btnLabel[Number(isAutoUpdating)]);
  $("#isAutoUpdating").val(Number(isAutoUpdating));
}

function onclickAutoUpdateButton() {
  var doAutoUpdate = true;

  if (Number($('#isAutoUpdating').val()) == 1) {
    doAutoUpdate = false;
  }
  changeAutoUpdateMode(doAutoUpdate);

}

function resizeGanttChart() {

  $(".ganttview").css("width", "100%");

  var width = $(".ganttview").width();

  if (width < 890) {
    width = 890;
  } else {
    width -= 5;
  }

  $(".ganttview").width(width);
  $(".ganttview-slide-container").width(width-210);
}
