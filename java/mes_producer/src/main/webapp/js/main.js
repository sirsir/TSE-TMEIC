var productStatusColor = {'1': 'green' , '2': 'blue', '3': 'red', '4': 'black'};
var processStatusColor = {'1': 'green' , '2': 'black'};
var processProductStatusColor = {'1': 'green' , '2': 'black'};

function isDate(val) {

  var date = new Date(val);

  return !(date.toString() === "Invalid Date");
}


function datetimepicker(element) {
  $(element).datetimepicker({
    controlType: 'select',
    oneLine: true,
    timeOnly: true,
    timeFormat: "HHmm"
  });
}

function htmlEncode(value){
  return $('<div/>').text(value).html();
}

function htmlDecode(value){
  return $('<div/>').html(value).text();
}

var ajaxPostJson = function(obj) {

  var prop = {
    async: false,
    type: "POST",
    dataType: "jsonp",
    success: function(data) {
      if (data.result == "OK") {
        doSearch();
      } else if (data.result == "NG") {
        dialogUtils.alert(data.message);
      }
    }
  }

  $.extend(prop, obj);

  $.ajax(prop);
}

var changeBackgroundColorByStatus = function (g) {

  var ids = $(g.gridId).jqGrid('getDataIDs');

  for (var i = 0; i < ids.length; i++) {

    if ( $(g.gridId).getCell(ids[i], "status") == "3" || $(g.gridId).getCell(ids[i], "status") == "4" ) {
      $(g.gridId).setRowData(ids[i], false, { background:'#c0c0c0' });
    }
  }
}

/**
 * Set the edit control of the specification column
 */
var setSpecEditControlToGrid = function(gridId, inputValueColName, specId, specAttributeId, specParts) {

  if (!specId) {
    $(gridId).setColProp(inputValueColName, {editable:false});
    return;
  }

  switch (specAttributeId) {

    case "1":
    case "2":
      $(gridId).setColProp(inputValueColName,
          {
            edittype:"text",
            editable:true
          }
      );
      break;

    default:
      $(gridId).setColProp(inputValueColName,
          {
            edittype:"select",
            editable:true,
            editoptions:{value: specParts}
          }
      );
      break;

  }
}

/**
 * Change column visible.
 *   Column names are valid in the case of the "base name + index".
 */
var getColumnVisibleByNameWithIndex = function (gridId, maxColumnSize, validColumnSize, columnNames) {

  var showColumnNames = [];
  var hideColumnNames = [];

  for (var columnIdx = 1; columnIdx <= maxColumnSize; columnIdx++) {

    var enabled = false;

    if (columnIdx <= validColumnSize) {
      enabled = true;
    }

    $.each(columnNames, function(i, columnBaseName) {

      if (enabled) {
        showColumnNames.push(columnBaseName + columnIdx);
      } else {
        hideColumnNames.push(columnBaseName + columnIdx);
      }
    });
  }

  return {
    showColumnNames : showColumnNames,
    hideColumnNames : hideColumnNames
  };
}