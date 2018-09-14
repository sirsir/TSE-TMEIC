function JqGridUtils(gridId, pagerId) {
  var util = this;
  this.gridId = gridId;
  this.pagerId = pagerId;
  this.deleteDatas = new Array();
  this.pkey = [];
  this.lastSelRowId;
  this.lastSelRow;
  this.isLoaded = false;
  this.datetimepiker = {"dateformat":"dd/mm/yy", "timeformat":"HH:mm:ss"};
  this.datepicker = {"dateformat":"dd/mm/yyyy"};
  this.fitWidth = true;
  this.prop = this.initProp(pagerId);
  this.prop.loadComplete = (function() {
    $("option[value=10000]").text('ALL');
  });

  this.prop.gridComplete = (function() {

    $('tbody > tr:odd', util.gridId).removeClass('ui-row-even');
    $('tbody > tr:even', util.gridId).addClass('ui-row-even');

    if (!util.isLoaded == true) {
      if (typeof(util.groupHeader) == "function") {
        util.groupHeader();
      }

      $(util.gridId).jqGrid('setFrozenColumns');

      util.isLoaded = true;

      if (util.fitWidth) {

        $(window).bind('resize', function () {
          util.prop.fitResize();
        }).trigger('resize');
      }
    }

    util.lastSelRow = null;
  });

  this.prop.onSelectRow = (function(id, status, e) {

    if (!id) return;

    if (id != util.lastSelRowId) {

      if (util.prop.isEditMode(util.lastSelRowId)) {
        if (!util.prop.saveEditRow(util.lastSelRowId)) return;
      }

      if ($.isFunction(util.prop.onSelectRowEx)) {

        var results = util.prop.onSelectRowEx(id);

        if(results) {

          if (("canEdit" in results) && (results.canEdit == false)) {
            util.prop.lastSelRowForget();
            return;
          }
        }
      }

      util.lastSelRowId = id;
      util.lastSelRow = $(gridId).jqGrid('getRowData', id);
      util.prop.editRow(id);
    }
  });

  this.prop.editRow = function(rowId) {

    var crud = $(util.gridId).getCell(rowId, "crud");
    var isEditable = (typeof crud === "undefined" || crud == "" || crud == "C");

    var dataIDs = $(util.gridId).jqGrid('getDataIDs');

    for (var i=0;i<dataIDs.length;i++) {

      var dataID=dataIDs[i];

      $.each(util.pkey, function() {
        $(util.gridId).setColProp(this.toString(), {editable:isEditable});
      });
    }

    $(util.gridId + '_rbtn' + rowId).prop("checked", "checked");

    $(gridId).jqGrid('editRow',rowId,
    {
      keys : true,
      url : 'clientArray',
      aftersavefunc: function(rowId) {

        util.prop.updateRowCrud(rowId);

        if ($.isFunction(util.prop.aftersavefuncRowEx)) {
          util.prop.aftersavefuncRowEx(rowId);
        }

        util.prop.lastSelRowForget();
      },
      afterrestorefunc: function(rowId) {
        util.prop.lastSelRowForget();
      }
    });
  }

  this.prop.saveEditRow = function(rowId) {

    if (util.prop.isEditMode(rowId)) {

      var saveRowRet = $(gridId).jqGrid('saveRow', rowId);

      if (!saveRowRet) {
        $(gridId).jqGrid('setSelection', rowId);
        return false;
      }

      util.prop.updateRowCrud(rowId);

      if ($.isFunction(util.prop.aftersavefuncRowEx)) {
        util.prop.aftersavefuncRowEx(rowId);
      }
    }

    util.prop.lastSelRowForget();

    return true;
  }

  this.prop.isEditMode = function(rowId) {
    return ($($(gridId).jqGrid("getInd",rowId,true)).attr("editable") === "1");
  }

  this.prop.updateRowCrud = function(rowId) {

    var dataArray = $(gridId).jqGrid('getGridParam', 'data');
    var indexes = $(gridId).jqGrid('getGridParam', '_index');

    var afterRow = dataArray[indexes[rowId]];

    for (var prop in util.lastSelRow) {

      if (!(prop in afterRow)) continue;

      var beforeVal = util.lastSelRow[prop];
      var afterVal = afterRow[prop];

      if (beforeVal == null) beforeVal = "";
      if (afterVal == null) afterVal = "";

      if(beforeVal != afterVal) {
        util.prop.updateCrud(rowId);
        break;
      }
    }
  }

  this.prop.updateCrud = function(rowId) {

    var crud = $(util.gridId).getCell(rowId, "crud");

    if (crud == "") {
      $(util.gridId).setCell(rowId, "crud", "C");
    } else if (crud == "R") {
      $(util.gridId).setCell(rowId, "crud", "U");
    }
  }

  this.prop.afterSaveCell = (function(rowid, cellname, value, iRow, iCol) {
    util.prop.updateRowCrud(rowid);
  });

  this.prop.fitResize = (function() {

    var tableWidth = $(util.gridId).width();
    var tableHeight = $(util.gridId).height();
    var width = $(window).width() - 40;

    if (tableWidth < width) {
      width = tableWidth;
    }

    if (tableHeight > util.prop.height) {
      width += 17;
    }

    if (width < util.prop.width) {
      width = util.prop.width;
    }

    $(util.gridId).setGridWidth(width);
  });

  this.prop.lastSelRowForget = (function() {
    util.lastSelRowId = null;
    util.lastSelRow = null;
  });
}

JqGridUtils.prototype.initProp = function(pagerId) {

  return {
    datatype : "local",
    width : 967,
    height : 510,
    rownumbers : true,
    rowNum : 100,
    rowList : [ 20, 50, 100, 1000, 10000 ],
    scrollrows : true,
    shrinkToFit : false,
    multiselect : false,
    viewrecords : true,
    pager : pagerId
  }
}

JqGridUtils.prototype.edit = function(editurl) {
  this.prop.cellsubmit = 'clientArray';
  this.prop.editurl = editurl;
}

JqGridUtils.prototype.selrow = function() {
  return $(this.gridId).getGridParam("selrow");
}

JqGridUtils.prototype.addRow = function(obj) {

  $(this.gridId).jqGrid('restoreRow', this.lastSelRow);
  this.lastSelRow = null;

  var newRowData = {crud : "C"};

  $.extend(newRowData, obj);

  $(this.gridId).addRowData(
      undefined,
      newRowData,
      "last"
  );

  $(this.gridId).closest(".ui-jqgrid-bdiv").scrollTop($(this.gridId).height());
}

JqGridUtils.prototype.deleteRow = function(rowid) {

  var crud = $(this.gridId).getCell(rowid, "crud");

  if (crud == "C") {
    return $(this.gridId).delRowData(rowid);
  }

  return false;
}

JqGridUtils.prototype.exportJson = function() {

  return this.exportJson();
}

JqGridUtils.prototype.getUpdateData = function() {

  var util = this;
  var updateData = new Array();

  $.each($.parseJSON(this.exportJson()), function(key, data) {
    if (data.crud != "R") {
      data = util.decodeJson(data);
      updateData.push(data);
    }
  });

  return updateData;
}

JqGridUtils.prototype.decodeJson = function(data) {

  var util = this;

  for (var prop in data) {

    if (data[prop] == null) continue;
    if (typeof data[prop] != "string") continue;
    if (data[prop].indexOf("[") != 0) continue;
    if (data[prop].lastIndexOf("]") != data[prop].length -1) continue;

    data[prop] = $.parseJSON(data[prop]);

    $.each(data[prop], function(key, subData) {
      subData = util.decodeJson(subData);
    });
  }

  return data;
}

JqGridUtils.prototype.exportJsonObject = function exportJsonObject() {
  var exportJson = $(this.gridId).jqGrid('jqGridExport', {exptype:"jsonstring", ident:""});
  var jsonData = eval("(" + exportJson + ")").grid.data;
  return jsonData;
}

JqGridUtils.prototype.exportJson = function exportJson() {
  return JSON.stringify(this.exportJsonObject());
}

JqGridUtils.prototype.radioFmatter = function(cellvalue, options, rowObject) {
  return '<input type="radio"' +
                     ' name="' + options.gid + '_rbtn"' +
                     ' id="'   + options.gid + '_rbtn' + options.rowId + '"' +
                     ' onclick="$(\'#' + options.gid + '\').jqGrid(\'setSelection\',' + options.rowId + ');"' +
         ' />';
}

JqGridUtils.prototype.dateTimePickerFmatter = function (element, dateformat, timeformat){

  $(element).datetimepicker({
    controlType: 'select',
    oneLine: true,
    dateFormat: "dd/mm/yy",
    timeFormat: "HH:mm:ss"
  });

}

JqGridUtils.prototype.datePickerFmatter = function (element, dateformat){

  $(element).datepicker({
    dateFormat: "dd/mm/yy"
  });

}

JqGridUtils.prototype.timePickerFmatter = function (element, timeformat){

  $(element).datetimepicker({
    controlType: 'select',
    oneLine: true,
    timeOnly: true,
    timeFormat: "HHmm"
  });

}

JqGridUtils.setDetailGrid = function(jsonstring) {

  var jsonObject = $.parseJSON(jsonstring);

  $.each(jsonObject, function() {
    for (var prop in this) {
      if (this[prop] instanceof Array) {
        this[prop] = JSON.stringify(this[prop]);
      }
    }
  });

  return jsonObject;
}

JqGridUtils.prototype.isEditing = function() {

  var ret = false;

  $.each($.parseJSON(this.exportJson()), function(key, data) {
    if (data.crud != "R") {
      ret = true;
      return false;
    }
  });

  return ret;
}

JqGridUtils.prototype.upData = function() {

  var gridId = this.gridId;
  var selRowId = $(this.gridId).getGridParam("selrow");

  var befDataArray = jQuery(this.gridId).jqGrid('getRowData');
  var rowIds = jQuery(this.gridId).jqGrid('getDataIDs');

  var i = 0;
  $.each(rowIds, function (key, data) {
    if (data == selRowId) {
      if (i != 0) {
        var befRowId = rowIds[i-1];
        var befRowData = $(gridId).getRowData(befRowId);
        $(gridId).jqGrid("delRowData",befRowId);
        $(gridId).addRowData(befRowId, befRowData, 'after', selRowId);
      }
    }
    i++;
  });
  $(this.gridId).setSelection(selRowId);
}

JqGridUtils.prototype.downData = function() {

  var gridId = this.gridId;
  var selRowId = $(this.gridId).getGridParam("selrow");

  var aftDataArray = jQuery(this.gridId).jqGrid('getRowData');
  var rowIds = jQuery(this.gridId).jqGrid('getDataIDs');

  var i = 0;
  $.each(rowIds, function (key, data) {
    if (data == selRowId) {
      if (i != (rowIds.length-1)) {
        var aftRowId = rowIds[i+1];
        var aftRowData = $(gridId).getRowData(aftRowId);
        $(gridId).jqGrid("delRowData",aftRowId);
        $(gridId).addRowData(aftRowId, aftRowData, 'before', selRowId);
      }
    }
    i++;
  });
  $(this.gridId).setSelection(selRowId);
}
