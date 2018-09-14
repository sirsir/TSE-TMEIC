/*
jQuery.ganttView v.0.7.2
Copyright (c) 2010 JC Grubbs - jc.grubbs@devmynd.com
MIT License Applies
*/

/*
Options
-----------------
showWeekends: boolean
data: object
start: date
end: date
cellWidth: number
cellHeight: number
slideWidth: number
readOnly: boolean
excludeWeekends: boolean
showDayOfWeek: boolean
showHolidays: boolean
excludeHolidays: boolean
*/

var ChartLang = {
    days: "days"
};

(function (jQuery) {
    jQuery.fn.ganttView = function (options) {
        var els = this;

        var defaults = {
            showWeekends: true,
            cellWidth: 61,
            cellHeight: 21,
            slideWidth: 1900,
            vHeaderWidth: 210,
            blockClick: null,
            changed: null,
            clicked: null,
            dblClicked: null,
            readOnly: false,
            excludeWeekends: false,
            showDayOfWeek: false,
            showHolidays: false,
            excludeHolidays: false,
            color: null,
            page: 1,
            pageTotalCount: 0
        };

        Chart.opts = jQuery.extend(defaults, options);

        var months = Chart.getMonths();

        els.each(function () {
            var container = jQuery(this);
            var div = jQuery("<div>", { "class": "ganttview" });

            Chart.addVtHeader(div);

            var slideDiv = jQuery("<div>", {
                "class": "ganttview-slide-container",
                "css": { "width": Chart.opts.slideWidth + "px" }
            });

            Chart.addHzDaysHeader(slideDiv, months);
//            Chart.addHzHeader(slideDiv, months);
            Chart.addGrid(slideDiv, months);
            if (Chart.opts.data) {
              Chart.addBlockContainers(slideDiv);
              Chart.addBlocks(slideDiv);
              Chart.setBlockContainersWidth(slideDiv);
              // 現在時刻
              Chart.createNowBorder(slideDiv, months);
            }
            Chart.addHzHoursHeader(slideDiv, months);

            div.append(slideDiv);
            container.append(div);

            var w = jQuery("div.ganttview-vtheader", container).outerWidth() +
                jQuery("div.ganttview-slide-container", container).outerWidth();
            div.css("width", w + "px");

            Chart.applyLastClass(container);

            Events.bindBlockClick(container, Chart.opts.blockClick);

            Events.bindVtheaderClick(container, Chart.opts.blockClick);

            // 現在時刻までスクロールする
            Chart.setScrollPosition(container);

       });

    };

    jQuery.fn.getNextPage = function () {
      var pageTotalCount = Chart.opts.pageTotalCount;
      if (pageTotalCount <= 1) {
        return 1;
      }

      var page = $("#page-text").attr("value");
      var nextPage = Number(page) + 1;

      if (nextPage > pageTotalCount) {
        nextPage = 1;
      }

      return nextPage;
    };

    var Chart = {

        selectedBlock: null,

        opts: null,

        monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],

        dayOfWeekNames: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],

        getMonths: function () {
            var opts = Chart.opts;
            var start = Date.parse(opts.start);
            var end = Date.parse(opts.end);
            var yearmanths = {};
            var startYear = start.getYear();
            var endYear = end.getYear();

            yearmanths[startYear + "/" + start.getMonth()] = [];
            yearmanths[startYear + "/" + start.getMonth()][start.getDate() - 1] = [start];
            var last = start;

            while (last.compareTo(end) < 0) {
                var next = last.clone().addHours(1);
                if (!yearmanths[next.getYear() + "/" + next.getMonth()]) { yearmanths[next.getYear() + "/" + next.getMonth()] = []; }
                if (!yearmanths[next.getYear() + "/" + next.getMonth()][next.getDate() - 1]) { yearmanths[next.getYear() + "/" + next.getMonth()][next.getDate() - 1] = []; }
                yearmanths[next.getYear() + "/" + next.getMonth()][next.getDate() - 1].push(next);
                last = next;
            }

            var months = [];
            var count = 0;

            for (var i = startYear; i <= endYear ; i++){
                for (var ii = 0; ii < 12; ii++) {
                    if (yearmanths[i + "/" + ii]) {
                        months[count] = yearmanths[i + "/" + ii];
                        count++;
                    }
                }
            }

            return months;
        },

        addVtHeader: function (div) {
            var opts = Chart.opts;
            var data = opts.data;
            var cellHeight = opts.cellHeight;
            var showDayOfWeek = opts.showDayOfWeek;
            var clicked = opts.clicked;
            var headerDiv = jQuery("<div>", { "class": "ganttview-vtheader" });

            var dowsDiv = jQuery("<div>", {
                    "class": "ganttview-vthheader-dows",
                    "css": { "height": cellHeight + "px" }
                    });

            if (showDayOfWeek) { headerDiv.append(dowsDiv); }

            if (!data) {
              return false;
            }

            var slideContainer = jQuery("<div>", { "class": "ganttview-vtheader-slide-container" });

            var textDecoration = (clicked == null) ? "none" : "underline";
            for (var i = 0; i < data.length; i++) {
                if (!data[i].series) continue;

                var rowCount = (data[i].subName != null) ? 1 : 3;
                var itemHeight = cellHeight * rowCount + 1;
                var nameHeight = cellHeight * rowCount - 1;
                var paddingTop = "3px";

                if (rowCount == 3) {
                  itemHeight = cellHeight * rowCount + 2;
                  nameHeight = cellHeight * rowCount - 2;
                  paddingTop = "5px";
                }

                var itemDiv = jQuery("<div>", {
                        "id": "ganttview-vtheader-item-" + data[i].id,
                        "class": "ganttview-vtheader-item",
                        "css": { "height": itemHeight + "px" }
                        });
                var seriesDiv = jQuery("<div>", { "class": "ganttview-vtheader-series" });
                var nameDiv = jQuery("<div>", {
                        "id": "ganttview-vtheader-item-name-" + data[i].id,
                        "class": "ganttview-vtheader-item-name",
                        "css": { "height": nameHeight + "px",
                          "padding-top": paddingTop,
                          "text-decoration" : textDecoration }
                }).data("block-data", {
                  itemId: data[i].id,
                  itemName: data[i].name,
                  seriesId: data[i].series[0].id,
                  seriesName: data[i].series[0].name,
                  start: Date.parse(data[i].series[0].start),
                  end: Date.parse(data[i].series[0].end),
                  color: data[i].series[0].color,
                  text: data[i].series[0].text,
                  count: 0
                }).dblclick(function() {
                    if (Chart.selectedBlock != null && Chart.selectedBlock.data('block-data').seriesId == $(this).data('block-data').seriesId) {
                        var selItemRowId = Chart.selectedBlock.data('block-data').itemId;
                        var selRowId = Chart.selectedBlock.data('block-data').seriesId;

                        Chart.selectedBlock.removeClass("ganttview-block-selected");
                        jQuery("div#ganttview-vtheader-item-name-" + selItemRowId).removeClass("ganttview-vtheader-item-name-selected");
                        jQuery("div.ganttview-grid-row-cell-" + selRowId).removeClass("ganttview-grid-row-cell-selected");
                        jQuery("div#ganttview-vtheader-series-name-" + selRowId).removeClass("ganttview-vtheader-series-name-selected");
                        Chart.selectedBlock = null;
                    }

                    if (dblClicked != null) { dblClicked($(this)); }
                }).click(function() {
                    if (Chart.selectedBlock != null && Chart.selectedBlock.data('block-data').seriesId != $(this).data('block-data').seriesId) {
                        var selItemRowId = Chart.selectedBlock.data('block-data').itemId;
                        var selRowId = Chart.selectedBlock.data('block-data').seriesId;

                        Chart.selectedBlock.removeClass("ganttview-block-selected");
                        jQuery("div#ganttview-vtheader-item-name-" + selItemRowId).removeClass("ganttview-vtheader-item-name-selected");
                        jQuery("div.ganttview-grid-row-cell-" + selRowId).removeClass("ganttview-grid-row-cell-selected");
                        jQuery("div#ganttview-vtheader-series-name-" + selRowId).removeClass("ganttview-vtheader-series-name-selected");
                        Chart.selectedBlock = null;
                    }

                    if (Chart.selectedBlock == null || Chart.selectedBlock.data('block-data').seriesId != $(this).data('block-data').seriesId) {
                        var curItemRowId = $(this).data('block-data').itemId;
                        var curRowId = $(this).data('block-data').seriesId;

                        $(this).addClass("ganttview-block-selected");
                        jQuery("div#ganttview-vtheader-item-name-" + curItemRowId).addClass("ganttview-vtheader-item-name-selected");
                        jQuery("div.ganttview-grid-row-cell-" + curRowId).addClass("ganttview-grid-row-cell-selected");
                        jQuery("div#ganttview-vtheader-series-name-" + curRowId).addClass("ganttview-vtheader-series-name-selected");
                        Chart.selectedBlock = $(this);
                    }

                    if (clicked != null) { clicked($(this)); }
                 });

                itemDiv.append(nameDiv.append(data[i].name));

                slideContainer.append(itemDiv);

                if (data[i].subName != null) {
                    var sub1ItemDiv = jQuery("<div>", {
                      "class": "ganttview-vtheader-item-sub",
                      "css": { "height": (cellHeight * 2 +1) + "px" }
                      });
                    var subNameDiv = jQuery("<div>", {
                        "id": "ganttview-vtheader-item-name-" + data[i].id,
                        "class": "ganttview-vtheader-item-name",
                        "css": { "height": (cellHeight * 2) + "px",
                                 "border-top": "1px solid #ffffff"
                        }
                    });

                    sub1ItemDiv.append(subNameDiv.append(data[i].subName));

                    slideContainer.append(sub1ItemDiv);
                }
                headerDiv.append(slideContainer);
            }

            if (opts.pageTotalCount > 1) {
              Chart.addPageTable(headerDiv);
            }

            div.append(headerDiv);
        },

        addPageTable: function(headerDiv) {
          var clicked = Chart.opts.clicked;
          var changed = Chart.opts.changed;
          var page = Number(Chart.opts.page);
          var pageTotalCount = Number(Chart.opts.pageTotalCount);

          var pageTable = jQuery("<table>", {
            "class": "ganttview-vtheader-page",
          });
          var pageTd = jQuery("<td>", {
            "class": "ganttview-vtheader-page",
            "align": "center",
            "valign": "middle"
          });

          var pageFirstSpn = jQuery("<span>", {
            "class": "ui-icon ui-icon-seek-first",
          }).data("page-data", {
              page: 1}).click(function() {
                if (clicked != null) { clicked($(this)); }
          });

          if (page <= 1) {
            pageFirstSpn.attr('disabled', true);
          }
          pageTable.append(pageTd.clone().append(pageFirstSpn));

          var pagePrevSpn = jQuery("<span>", {
            "class": "ui-icon ui-icon-seek-prev",
          }).data("page-data", {
              page: page - 1}).click(function() {
                if (clicked != null) { clicked($(this)); }
          });

          if ((page - 1) == 0) {
            pagePrevSpn.attr('disabled', true);
          }
          pageTable.append(pageTd.clone().append(pagePrevSpn));

          var pageTextIpt = jQuery("<input>", {
            "id": "page-text",
            "type": "text",
            "maxlength": 7,
            "value": page,
            "css": {
              "height": 12 + "px" ,
              "width": 21 + "px",
              "font-size": 11 + "px"
            }
          }).change(function() {
            if (changed != null) { changed($(this)); }
          }).keypress(function(e) {
            if (e.which == 13 && changed != null && page != $(this).val()) { changed($(this)); }
          });

          var pageTotalCountSpn = jQuery("<span>", {
              "css": {
              "font-weight": "bold"
              }
            }).append(" / " + pageTotalCount);

          var pageTextTd = pageTd.clone().append(pageTextIpt);
          pageTextTd.append(pageTotalCountSpn);

          if (pageTotalCount <= 1) {
              pageTextTd.attr('disabled', true);
            }
            pageTable.append(pageTextTd);

          var pageNextSpn = jQuery("<span>", {
            "class": "ui-icon ui-icon-seek-next",
          }).data("page-data", {
              page: page + 1}).click(function() {
                if (clicked != null) { clicked($(this)); }
              });

          if (page >= pageTotalCount) {
            pageNextSpn.attr('disabled', true);
          }
          pageTable.append(pageTd.clone().append(pageNextSpn));

          var pageEndSpan = jQuery("<span>", {
            "class": "ui-icon ui-icon-seek-end",
          }).data("page-data", {
              page: pageTotalCount}).click(function() {
                if (clicked != null) { clicked($(this)); }
          });

          if (page >= pageTotalCount) {
            pageEndSpan.attr('disabled', true);
          }
          pageTable.append(pageTd.clone().append(pageEndSpan));

          headerDiv.append(jQuery("<div>", {
            "align": "center",
              "css": {
                "border-top": "1px solid #d0d0d0"
              }
            }).append(pageTable));
        },

        addHzHeader: function (div, months) {
            var opts = Chart.opts;
            var cellWidth = opts.cellWidth;
            var showWeekends = opts.showWeekends;
            var showDayOfWeek = opts.showDayOfWeek;
            var showHolidays = opts.showHolidays;
            var totalW = 0;
            var headerDiv = jQuery("<div>", { "class": "ganttview-hzheader" });
            var monthsDiv = jQuery("<div>", { "class": "ganttview-hzheader-months" });
            var dowsDiv = jQuery("<div>", { "class": "ganttview-hzheader-dows" });
            var daysDiv = jQuery("<div>", { "class": "ganttview-hzheader-days" });

            for (var i = 0; i < months.length; i++) {
                if (months[i]) {
                	for (var j = 0; j < months[i].length; j++) {
	                    if (!months[i][j]) {
	                    	continue;
	                    }

	                    var w = months[i][j].length * cellWidth;
	                    totalW = totalW + w;
	                    var x = 0;

	                    for (var k = 0; k < months[i][j].length; k++) {
	                        var cellDate = months[i][j][k];

	                        if ((DateUtils.isWeekend(cellDate) && showWeekends) || !DateUtils.isWeekend(cellDate)) {
	                            var dowDiv = jQuery("<div>", { "class": "ganttview-hzheader-dow" });
	                            var dayDiv = jQuery("<div>", { "class": "ganttview-hzheader-day" });

	                            if (DateUtils.isSaturday(cellDate)) { dowDiv.addClass("ganttview-saturday") };

	                            if (DateUtils.isSunday(cellDate)) { dowDiv.addClass("ganttview-sunday") };

	                            if (DateUtils.isSaturday(cellDate)) { dayDiv.addClass("ganttview-saturday") };

	                            if (DateUtils.isSunday(cellDate)) { dayDiv.addClass("ganttview-sunday") };

	                            if (showHolidays) {
	                                for (var h in Holidays) {
	                                    var holiday = Holidays[h];

	                                    if (holiday.at.getTime() == Date.parse(cellDate).getTime()) {
	                                        dowDiv.addClass("ganttview-holiday");
	                                        dayDiv.addClass("ganttview-holiday");

	                                        if (holiday.name) { dayDiv.attr("title", holiday.name); }

	                                        if (holiday.color) {
	                                            dowDiv.css("color", holiday.color);
	                                            dayDiv.css("color", holiday.color);
	                                        }

	                                        if (holiday.backgroundColor) {
	                                            dowDiv.css("background-color", holiday.backgroundColor);
	                                            dayDiv.css("background-color", holiday.backgroundColor);
	                                        }

	                                        break;
	                                    }
	                                }
                            }

                            dowsDiv.append(dowDiv.append(Chart.dayOfWeekNames[cellDate.getDay()]));
                            daysDiv.append(dayDiv.append(("0"+cellDate.getHours()).slice(-2) + ":00"));
	                        } else {
	                            x += cellWidth;
	                        }
		                }

	                    monthsDiv.append(jQuery("<div>", {
	                        "class": "ganttview-hzheader-month",
	                        "css": { "width": (w - x - 1) + "px" }
	                    }).append( months[i][j][0].getDate() + "/" + (months[i][j][0].getMonth() + 1) +  "/" + months[i][j][0].getFullYear()));

			            monthsDiv.css("width", totalW + "px");
			            dowsDiv.css("width", totalW + "px");
			            daysDiv.css("width", totalW + "px");

			            if (showDayOfWeek) {
			                headerDiv.append(monthsDiv).append(daysDiv).append(dowsDiv);
			            } else {
			                headerDiv.append(monthsDiv).append(daysDiv);
			            }
	                }
                }
            }
            div.append(headerDiv);

        },

        addHzDaysHeader: function (div, months) {
          var opts = Chart.opts;
          var cellWidth = opts.cellWidth;
          var showWeekends = opts.showWeekends;
          var showDayOfWeek = opts.showDayOfWeek;
          var showHolidays = opts.showHolidays;
          var totalW = 0;
          var headerDiv = jQuery("<div>", { "class": "ganttview-hzheader" });
          var monthsDiv = jQuery("<div>", { "class": "ganttview-hzheader-months" });

          var blankW = cellWidth;
          totalW = blankW;
          monthsDiv.append(jQuery("<div>", {
            "class": "ganttview-hzheader-month",
            "css": { "width": (blankW - 1) + "px", "border-right": "1px solid #ffffff" }
          }));

          monthsDiv.css("width", totalW + "px");

          headerDiv.append(monthsDiv);

          for (var i = 0; i < months.length; i++) {
              if (months[i]) {
                for (var j = 0; j < months[i].length; j++) {
                    if (!months[i][j]) {
                      continue;
                    }

                    var w = months[i][j].length * cellWidth;
                    totalW = totalW + w;

                    monthsDiv.append(jQuery("<div>", {
                        "class": "ganttview-hzheader-month",
                        "css": { "width": (w - 1) + "px" }
                    }).append(months[i][j][0].getDate() + "/" + (months[i][j][0].getMonth() + 1) +  "/" + months[i][j][0].getFullYear()));

                    monthsDiv.css("width", totalW + "px");

                    headerDiv.append(monthsDiv);
                }
              }
          }

          div.append(headerDiv);

        },

        addHzHoursHeader: function (div, months) {
          var opts = Chart.opts;
          var cellWidth = opts.cellWidth;
          var totalW = 0;
          var headerDiv = jQuery("<div>", { "class": "ganttview-hzheader" });
          var daysDiv = jQuery("<div>", { "class": "ganttview-hzheader-days" });


          var blankW = cellWidth;
          totalW = blankW;
          daysDiv.append(jQuery("<div>", {
            "class": "ganttview-hzheader-day",
            "css": { "width": (blankW / 2 - 1) + "px" }
          }));

          daysDiv.css("width", totalW + "px");

          headerDiv.append(daysDiv);

          for (var i = 0; i < months.length; i++) {
              if (months[i]) {
                for (var j = 0; j < months[i].length; j++) {
                    if (!months[i][j]) {
                      continue;
                    }

                    var w = months[i][j].length * cellWidth;
                    totalW = totalW + w;
                    var x = 0;

                    for (var k = 0; k < months[i][j].length; k++) {
                        var cellDate = months[i][j][k];

                        var dayDiv = jQuery("<div>", { "class": "ganttview-hzheader-day" });

                        daysDiv.append(dayDiv.append(("0"+cellDate.getHours()).slice(-2) + ":00"));

                        if (i == (months.length - 1) && j == (months[i].length - 1) && k == (months[i][j].length - 1)) {
                            daysDiv.append(jQuery("<div>", {
                            "class": "ganttview-hzheader-day",
                            "css": { "width": (blankW /2 - 1) + "px" }
                          }));
                          totalW = totalW + blankW;
                        }
                    }

                    daysDiv.css("width", totalW + "px");

                    headerDiv.append(daysDiv);
                }
              }
          }

          div.append(headerDiv);

      },

      addGrid: function (div, months) {
            var rowDiv = Chart.createGrid(months);
            var gridDiv = jQuery("<div>", { "class": "ganttview-grid" });

            var opts = Chart.opts;
            var cellWidth = opts.cellWidth;
            var data = Chart.opts.data;

//            var w = (jQuery("div.ganttview-grid-row-cell", rowDiv).length - 1) * cellWidth + (cellWidth / 2);
            var w = (jQuery("div.ganttview-grid-row-cell", rowDiv).length - 1) * cellWidth + cellWidth;
            rowDiv.css("width", w + "px");
            gridDiv.css("width", w + "px");

            for (var i = 0; i < data.length; i++) {
              var rowCol = (i % 2 == 0) ? "even" : "odd";
                for (var j = 0; j < data[i].series.length; j++) {
                    var cloneDiv = rowDiv.clone();
                    cloneDiv.attr("id", "ganttview-grid-row-" + data[i].series[j].id);
                    cloneDiv.addClass("ganttview-grid-row-" + data[i].id);
                    cloneDiv.children().addClass("ganttview-grid-row-cell-" + data[i].series[j].id + " ganttview-grid-row-" + rowCol);
                    gridDiv.append(cloneDiv.clone());
                }
            }

            var gridContainerDiv = jQuery("<div>", { "class": "ganttview-grid-container" });
            gridContainerDiv.append(gridDiv);

//            div.append(gridDiv);
            div.append(gridContainerDiv);
        },

        addBlockContainers: function (div) {
            var opts = Chart.opts;
            var data = opts.data;
            var showDayOfWeek = opts.showDayOfWeek;
            var blocksDiv = jQuery("<div>", { "class": "ganttview-blocks" });

            if (showDayOfWeek) { blocksDiv.addClass("ganttview-with-day-of-week") };

            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].series.length; j++) {
                    containerDiv = jQuery("<div>", {
                                "class": "ganttview-block-container ganttview-block-container-" + data[i].id,
                                "id": "ganttview-block-container-" + data[i].series[j].id
                    });
                    containerDiv.css("height", opts.cellHeight - 3 + "px");
                    blocksDiv.append(containerDiv);
                }
            }

            div.append(blocksDiv);
        },

        setBlockContainersWidth: function(div) {
            var opts = Chart.opts;
            var data = opts.data;

            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].series.length; j++) {
                    var gridDiv = jQuery("div#ganttview-grid-row-" + data[i].series[j].id, div);
                    jQuery("div.ganttview-block-container", div).css("width", gridDiv.css("width"));
                }
            }
        },

        addBlocks: function (div) {
            var opts = Chart.opts;
            var data = opts.data;
            var rows = jQuery("div.ganttview-blocks div.ganttview-block-container", div);
            var rowIdx = 0;

            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].series.length; j++) {
                    var series = data[i].series[j];

                    Chart.createBlock(i, series, rows, rowIdx, opts);

                    rowIdx++;
                }
            }
        },

        applyLastClass: function (div) {
            jQuery("div.ganttview-grid-row div.ganttview-grid-row-cell:last-child", div).addClass("last");
            jQuery("div.ganttview-hzheader-days div.ganttview-hzheader-day:last-child", div).addClass("last");
            jQuery("div.ganttview-hzheader-months div.ganttview-hzheader-month:last-child", div).addClass("last");
        },

        setCount: function (obj, start, end) {
            var opts = Chart.opts;
            var weekends = 0;
            var seriesName = obj.data('block-data').seriesName;
            var count = DateUtils.daysBetween(start, end, false, false);
            var text = obj.data('block-data').text;
            var excludeWeekends = opts.excludeWeekends;
            var showWeekends = opts.showWeekends;
            var excludeHolidays = opts.excludeHolidays;

            if (excludeWeekends && showWeekends) { weekends = DateUtils.daysBetween(start, end, true, false); }

            if (excludeHolidays) { weekends += DateUtils.daysBetween(start, end, null, true); }

            count -= weekends;

            obj.data('block-data').count = count;

            if (!text || (text && text == null)) { obj.children('.ganttview-block-text').text(count); }

            obj.attr("title", Utils.getTitle(seriesName, start, end));
        },

        createGrid: function(months) {
            var opts = Chart.opts;
            var data = opts.data;
            var cellWidth = opts.cellWidth;
            var cellHeight = opts.cellHeight;
            var showWeekends = opts.showWeekends;
            var showHolidays = opts.showHolidays;
            var rowDiv = jQuery("<div>", { "class": "ganttview-grid-row" });

            for (var i = 0; i < months.length; i++) {

            	if (!months[i]) {
            		continue;
            	}

            	for (var j = 0; j < months[i].length; j++) {
	                if (!months[i]) {
	                  continue;
	                }
	                if (!months[i][j]) {
	                  continue;
	                }
                  for (var k = 0; k < months[i][j].length; k++) {
                      var cellDiv = jQuery("<div>", {
                              "class": "ganttview-grid-row-cell cell-" + k,
                              "css": { "width": cellWidth - 1 + "px", "height": cellHeight + "px" }
                      });

                      var cellDate = months[i][j][k];

                      if ((DateUtils.isWeekend(cellDate) && showWeekends) || !DateUtils.isWeekend(cellDate)) {

                          rowDiv.append(cellDiv.clone());
                      }
                  }
	            }
            }

            rowDiv.append(jQuery("<div>", {
              "class": "ganttview-grid-row-cell",
              "css": { "width": (cellWidth - 1) + "px", "height": cellHeight + "px" }
            }));

            return rowDiv;
        },

        createBlock: function(itemIdx, series, rows, rowIdx, opts) {
            var data = opts.data;
            var start = new Date.parse(opts.start);
            var end = new Date.parse(opts.end);
            var cellWidth = opts.cellWidth;
            var excludeWeekends = opts.excludeWeekends;
            var showWeekends = opts.showWeekends;
            var excludeHolidays = opts.excludeHolidays;
            var changed = opts.changed;
            var clicked = opts.clicked;
            var dblClicked = opts.dblClicked;
            var seriesStart = new Date.parse(series.start);
            var seriesEnd = Date.parse(series.end);
            var size = DateUtils.daysBetween(seriesStart, seriesEnd, false, false);

            if (!showWeekends) { size -= DateUtils.daysBetween(seriesStart, seriesEnd, true, false); }

            if (size && size > 0) {
                if (size > 365) { size = 365; } // Keep blocks from overflowing a year

                var count = size;

                if (excludeWeekends && showWeekends) { count -= DateUtils.daysBetween(seriesStart, seriesEnd, true, false); }

                if (excludeHolidays) { count -= DateUtils.daysBetween(seriesStart, seriesEnd, null, true); }

                var offset = DateUtils.daysBetween(start, seriesStart, false, false);

                if (!showWeekends) { offset -= DateUtils.daysBetween(start, seriesStart, true, false); }

                var readOnly = series.readOnly ? series.readOnly : false;

                var startDecrement = 0;
                var minutesMargin = 0;

                var width = Math.floor(cellWidth / 60 * ((seriesEnd.getTime() - seriesStart.getTime()) / 1000 / 60));

                if (seriesStart.getMinutes() != 0 || seriesStart.getSeconds() != 0) {
                  startDecrement = 1;
                  minutesMargin =  Math.floor(cellWidth / 60 * seriesStart.getMinutes());
                  if (seriesStart.getSeconds() != 0) {
                    minutesMargin = minutesMargin + 1;
                  }
                }

                var blockDiv = jQuery("<div>", {
                    "id": "ganttview-block-" + series.id,
                    "class": "ganttview-block",
                    "title": Utils.getTitle(series.name, series.start, series.end),
                    "css": {
                        "width": width + "px",
                        "margin-left": (((offset - startDecrement) * cellWidth) + minutesMargin + (cellWidth - 1)) + "px",
                        "left": "0px"
                    }
                }).data("block-data", {
                    itemId: data[itemIdx].id,
                    itemName: data[itemIdx].name,
                    seriesId: series.id,
                    seriesName: series.name,
                    start: Date.parse(seriesStart),
                    end: Date.parse(seriesEnd),
                    color: series.color,
                    text: series.text,
                    count: count
                }).dblclick(function() {
                    if (Chart.selectedBlock != null && Chart.selectedBlock.data('block-data').seriesId == $(this).data('block-data').seriesId) {
                        var selItemRowId = Chart.selectedBlock.data('block-data').itemId;
                        var selRowId = Chart.selectedBlock.data('block-data').seriesId;

                        Chart.selectedBlock.removeClass("ganttview-block-selected");
                        jQuery("div#ganttview-vtheader-item-name-" + selItemRowId).removeClass("ganttview-vtheader-item-name-selected");
                        jQuery("div.ganttview-grid-row-cell-" + selRowId).removeClass("ganttview-grid-row-cell-selected");
                        jQuery("div#ganttview-vtheader-series-name-" + selRowId).removeClass("ganttview-vtheader-series-name-selected");
                        Chart.selectedBlock = null;
                    }

                    if (dblClicked != null) { dblClicked($(this)); }
                }).click(function() {
                    if (Chart.selectedBlock != null && Chart.selectedBlock.data('block-data').seriesId != $(this).data('block-data').seriesId) {
                        var selItemRowId = Chart.selectedBlock.data('block-data').itemId;
                        var selRowId = Chart.selectedBlock.data('block-data').seriesId;

                        Chart.selectedBlock.removeClass("ganttview-block-selected");
                        jQuery("div#ganttview-vtheader-item-name-" + selItemRowId).removeClass("ganttview-vtheader-item-name-selected");
                        jQuery("div.ganttview-grid-row-cell-" + selRowId).removeClass("ganttview-grid-row-cell-selected");
                        jQuery("div#ganttview-vtheader-series-name-" + selRowId).removeClass("ganttview-vtheader-series-name-selected");
                        Chart.selectedBlock = null;
                    }

                    if (Chart.selectedBlock == null || Chart.selectedBlock.data('block-data').seriesId != $(this).data('block-data').seriesId) {
                        var curItemRowId = $(this).data('block-data').itemId;
                        var curRowId = $(this).data('block-data').seriesId;

                        $(this).addClass("ganttview-block-selected");
                        jQuery("div#ganttview-vtheader-item-name-" + curItemRowId).addClass("ganttview-vtheader-item-name-selected");
                        jQuery("div.ganttview-grid-row-cell-" + curRowId).addClass("ganttview-grid-row-cell-selected");
                        jQuery("div#ganttview-vtheader-series-name-" + curRowId).addClass("ganttview-vtheader-series-name-selected");
                        Chart.selectedBlock = $(this);
                    }

                    if (clicked != null) { clicked($(this)); }
                 }).draggable({
                    disabled: readOnly,
                    axis: 'x',
                    containment: 'parent',
                    grid: [cellWidth, 0],
                    stop: function(event, ui) {
                        var distance = (ui.position.left) / cellWidth;
                        //console.debug('Position: %o, Distance: %o', ui.position.left, distance);

                        var s = $(this).data('block-data').start.addDays(distance);
                        var e = $(this).data('block-data').end.addDays(distance);
                        //console.debug('Start: %o, End: %o', s, e);

                        //var m = $(this).css("margin-left").replace(/px/, "");
                        //var n = parseInt(m) + parseInt(ui.position.left);
                        //ui.position.left = 0;

                        var n = DateUtils.daysBetween(start, s, false, false) * cellWidth + 3;
                        $(this).css("margin-left", n + "px");
                        $(this).css("left", "0px");

                        Chart.setCount($(this), s, e);

                        if (changed != null) { changed($(this)); }
                   }
               }).resizable({
                    disabled: readOnly,
                    containment: 'parent',
                    grid: [cellWidth, 0],
                    handles: 'e',
                    resize: function(event, ui) {
                        $(this).css("position", "");
                        $(this).css("top", "");
                        $(this).css("left", "0px");
                    },
                    stop: function(event, ui) {
                        var rdistance = Math.ceil(ui.size.width / cellWidth);
                        //console.debug('width: %o, originalSize: %o, day: %o', ui.size.width, ui.originalSize.width, rdistance);

                        var s = $(this).data('block-data').start;
                        var e = $(this).data('block-data').end;
                        var prevCount = DateUtils.daysBetween(s, e, false, false);

                        e.addDays(rdistance - prevCount);
                        //console.debug('Start: %o, End: %o', s, e);

                        $(this).css("position", "");
                        $(this).css("top", "");
                        $(this).css("left", "0px");
                        ui.position.left = 0;

                        Chart.setCount($(this), s, e);

                        if (changed != null) { changed($(this)); }
                    }
                });

                if (series.color) { blockDiv.addClass("ganttview-" + series.color); }

//                if (series.color) { blockDiv.css("background-color", series.color); }

                if(series.text && series.text != null) {
                    blockDiv.append($("<div>", {
                        "id": "ganttview-block-text-"+ series.id,
                        "class": "ganttview-block-text"
                    }).text(series.text));
                }else{
                    var weekends = 0;

                    if (excludeWeekends && showWeekends) { weekends = DateUtils.daysBetween(seriesStart, seriesEnd, true, false); }

                    if (excludeHolidays) { weekends += DateUtils.daysBetween(seriesStart, seriesEnd, null, true); }
                }

                jQuery(rows[rowIdx]).append(blockDiv);
            }
        },

        addSeries: function(d, s, itemIdx, mergedPoint, newPoint) {
            var div = jQuery("div.ganttview-slide-container");

            var vtheaderItem = jQuery("div#ganttview-vtheader-item-name-" + d.id);
            var vtheaderSeries = jQuery("div.ganttview-vtheader-series-name-" + d.id);

            var gridDiv = jQuery("div.ganttview-grid div.ganttview-grid-row-" + d.id, div);
            var months = Chart.getMonths();
            var rowDiv = Chart.createGrid(months);
            var cellWidth = Chart.opts.cellWidth;
            var w = jQuery("div.ganttview-grid-row-cell", rowDiv).length * cellWidth;
            rowDiv.css("width", w + "px");

            var rows= jQuery("div.ganttview-blocks div.ganttview-block-container-" + d.id, div);
            var newRow = jQuery("<div>", {
                "class": "ganttview-block-container ganttview-block-container-" + d.id,
                "id": "ganttview-block-container-" + s.id
            });
            newRow.css("height", Chart.opts.cellHeight - 3 + "px");
            newRow.css("width", w + "px");

            var newVTHeader = jQuery("<div>", {
                "class": "ganttview-vtheader-series-name ganttview-vtheader-series-name-" + d.id,
                "id": "ganttview-vtheader-series-name-" + s.id
            }).append(s.name);

            var m = vtheaderItem.css("height");

            m = (m != null) ? m.replace(/px/, "") : "0";

            var n = parseInt(m) + Chart.opts.cellHeight;
            vtheaderItem.css("height", n + "px");

            if (vtheaderSeries.length == 0) {
                if (newPoint == null) {
                    vtheaderItem.next().append(newVTHeader);
                } else {
                    vtheaderSeries = jQuery("div#ganttview-vtheader-item-name-" + newPoint);
                    vtheaderSeries.next().append(newVTHeader);
                }

            } else {
                if (mergedPoint != null) {
                    vtheaderSeries = jQuery("div#ganttview-vtheader-series-name-" + mergedPoint);
                }

                vtheaderSeries.last().after(newVTHeader);
            }

            var cloneDiv = rowDiv.clone();
            cloneDiv.attr("id", "ganttview-grid-row-" + s.id);
            cloneDiv.addClass("ganttview-grid-row-" + d.id);
            cloneDiv.children().addClass("ganttview-grid-row-cell-" + s.id);

            if (gridDiv.length == 0) {
                if (newPoint == null) {
                    jQuery("div.ganttview-grid").append(cloneDiv);
                } else {
                    gridDiv = jQuery("div#ganttview-grid-row-" + mergedPoint, div);
                    gridDiv.after(cloneDiv);
                }
            } else {
                if (mergedPoint != null) {
                    gridDiv = jQuery("div#ganttview-grid-row-" + mergedPoint, div);
                }

                gridDiv.last().after(cloneDiv);
            }

            var newRows = $.makeArray(newRow.clone());
            Chart.createBlock(itemIdx, s, newRows, 0, Chart.opts);

            if (rows.length == 0) {
                if (newPoint == null) {
                    jQuery("div.ganttview-blocks").append(newRows);
                } else {
                    rows = jQuery("div#ganttview-block-container-" + mergedPoint, div);
                    rows.after(newRows);
                }
            } else {
                if (mergedPoint != null) {
                    rows = jQuery("div#ganttview-block-container-" + mergedPoint, div);
                }

                rows.last().after(newRows);
            }

            s.merged = true;
        },

	    setScrollPosition: function(container) {
	    	var toDay = new Date();
        var startDate = Date.parse(Chart.opts.start);
        var endDate = Date.parse(Chart.opts.end);

	    	if (startDate.getTime() >= toDay.getTime() || endDate.getTime() < toDay.getTime()) {
	    		return false;
	    	}

	    	var containerWidth = jQuery("div.ganttview-slide-container", container).width();

	    	var count = DateUtils.daysBetween(startDate, toDay, Chart.opts.excludeWeekends, Chart.opts.holidayOnly);

        var minuteWidth = 0;
        if (toDay.getMinutes() != 0) {
          count = count - 1;
          minuteWidth = Math.floor((Chart.opts.cellWidth / 60) * toDay.getMinutes());
        }

        var size = ((Chart.opts.cellWidth) * count) + minuteWidth;
        var toDayPosition = size - (containerWidth / 2) + (Chart.opts.cellWidth - 1);

		    jQuery("div.ganttview-slide-container", container).scrollLeft(toDayPosition);
	    },

      createNowBorder: function(div, months) {
          var toDay = new Date();
          var startDate = Date.parse(Chart.opts.start);
          var endDate = Date.parse(Chart.opts.end);

          if (startDate.getTime() >= toDay.getTime() || endDate.getTime() < toDay.getTime()) {
            return false;
          }

          // 行数
        	var rowLength = 0;
        	for (var i = 0; i < Chart.opts.data.length; i++) {
        	  rowLength += Chart.opts.data[i].series.length;
        	}

        	if (rowLength == 0) {
        	  return false;
        	}

        	var count = DateUtils.daysBetween(startDate, toDay, Chart.opts.excludeWeekends, Chart.opts.holidayOnly);

        	var minuteWidth = 0;
        	if (toDay.getMinutes() != 0) {
        	  count = count - 1;
        	  minuteWidth = Math.floor((Chart.opts.cellWidth / 60) * toDay.getMinutes());
        	}

          var size = ((Chart.opts.cellWidth) * count) + minuteWidth + (Chart.opts.cellWidth - 1);

          var container = jQuery("<div>", {"class": "ganttview-now-container"});

          container.append(jQuery("<div>", {
            "class": "ganttview-now",
            "css": {
              "margin-top": -(rowLength * Chart.opts.cellHeight + rowLength) + "px",
              "margin-left": size + "px",
              "height": (rowLength * Chart.opts.cellHeight + rowLength) + "px"
            }
          }));

          jQuery("div.ganttview-blocks", div).append(container);
	    }

    };

    var Events = {
        bindBlockClick: function (div, callback) {
           $("div.ganttview-block").on("click", "selector", function () {
                if (callback) { callback($(this).data("block-data")); }
            });
        },
        bindVtheaderClick: function (div, callback) {
        	$("div.ganttview-vtheader-item").on("click", "selector", function () {
                if (callback) { callback($(this).data("block-data")); }
            });
        }
    };

    var ArrayUtils = {
        contains: function (arr, obj) {
            var has = false;
            for (var i = 0; i < arr.length; i++) { if (arr[i] == obj) { has = true; } }
            return has;
        }
    };

    var DateUtils = {
        daysBetween: function (start, end, excludeWeekends, holidayOnly) {
            if (!start || !end) { return 0; }

            start = Date.parse(start);
            end = Date.parse(end);

            if (start.getYear() == 1901 || end.getYear() == 8099) { return 0; }

            var count = 0, date = start.clone();

//            while (date.compareTo(end) == -1) {
            while (date.compareTo(end) < 0) {
                if (holidayOnly) {
                    for (var h in Holidays) {
                        if (Holidays[h].at.getTime() == date.getTime()) {
                            count = count + 1;
                            break;
                        }
                    }
                } else if ((excludeWeekends && DateUtils.isWeekend(date)) || !excludeWeekends) {
                    count = count + 1;
                }

                date.addHours(1);
            }

            return count;
        },
        isWeekend: function (date) {
            return date.getDay() % 6 == 0;
        },
        isSaturday: function (date) {
            return date.getDay() == 6;
        },
        isSunday: function (date) {
            return date.getDay() == 0;
        }
    };

//    var Utils = {
//        getTitle: function (name, distance) {
//            return name + ", " + distance + ChartLang.days;
//        }
//    };

    var Utils = {
        getTitle: function (name, start, end) {
            return name + ", " + start + "-" + end;
        }
    };

    jQuery.fn.extend({

        refreshGanttData: function() {
            var opts = Chart.opts;
            var d = Chart.opts.data;
            var data = $(this).data('block-data');

            for (var h = 0; h < d.length; h++) {
                if (d[h].id == data.itemId) {
                    d[h].name = data.itemName;

                    for (var i = 0; i < d[h].series.length; i++) {
                        if (d[h].series[i].id == data.seriesId) {
                            d[h].series[i].name = data.seriesName;
                            d[h].series[i].start = data.start;
                            d[h].series[i].end = data.end;
                            d[h].series[i].text = data.text;

                            break;
                        }
                    }

                    break;
                }
            }

            jQuery("div#ganttview-vtheader-item-name-" + data.itemId).text(data.itemName);
            jQuery("div#ganttview-vtheader-series-name-" + data.seriesId).text(data.seriesName);

            var blockDiv = jQuery("div#ganttview-block-" + data.seriesId);
            var size = DateUtils.daysBetween(data.start, data.end, false, false);
            var offset = DateUtils.daysBetween(opts.start, data.start, false, false);

            blockDiv.css({
                "width": ((size * opts.cellWidth) - 9) + "px",
                "margin-left": ((offset * opts.cellWidth) + 3) + "px"
            });

            var weekends = 0;

            if (opts.excludeWeekends && opts.showWeekends) { weekends = DateUtils.daysBetween(data.start, data.end, true, false); }

            if (opts.excludeHolidays) { weekends += DateUtils.daysBetween(data.start, data.end, null, true); }

            data.count = size - weekends;
            blockDiv.attr("title", Utils.getTitle(data.seriesName, data.start, data.end));

            if(data.text && data.text != null) {
                jQuery("div#ganttview-block-text-" + data.seriesId).text(data.text);
            } else {
                jQuery("div#ganttview-block-text-" + data.seriesId).text(data.count);
            }
        },

        deleteGanttData: function(o) {
            var opts = Chart.opts;
            var data = opts.data;
            var deleted = false;
            var seriesDeleted = false;
            var iId = o.data('block-data').itemId;
            var sId = o.data('block-data').seriesId;

            for (var m = 0; m < data.length; m++) {
                for (var n = 0; n < data[m].series.length; n++) {
                    if (data[m].series[n].id == o.data('block-data').seriesId) {
                        data[m].series.splice(n, 1);

                        if (data[m].series.length == 0) {
                            data.splice(m, 1);
                            seriesDeleted = true;
                        }

                        deleted = true;
                        break;
                    }
                }

                if (deleted) { break; }
            }

            if (deleted == false) { return; }

            jQuery("div#ganttview-grid-row-" + sId).remove();
            jQuery("div#ganttview-block-container-" + sId).remove();
            jQuery("div#ganttview-vtheader-series-name-" + sId).remove();

            if (seriesDeleted) {
                jQuery("div#ganttview-vtheader-item-" + iId).remove();
            } else {
                var itemDiv = jQuery("div#ganttview-vtheader-item-name-" + iId);
                itemDiv.removeClass("ganttview-vtheader-item-name-selected");
                var m = itemDiv.css("height").replace(/px/, "");
                var n = parseInt(m) - opts.cellHeight;
                itemDiv.css("height", n + "px");
            }

            Chart.selectedBlock = null;
        }
    });

    jQuery.extend(jQuery.fn.ganttView, {
        addData: function(d) {
            var data = Chart.opts.data;

            for (var h= 0; h < d.length; h++) {
                for (var i = 0; i < d[h].series.length; i++) {
                    d[h].series[i].merged = false;
                }
            }

            for (var h = 0; h < d.length; h++) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].id == d[h].id) {
                        if (Chart.selectedBlock == null) { Chart.opts.data[i].series.push(d[h].series); }

                        for (var j = 0; j < d[h].series.length; j++) {
                            if (Chart.selectedBlock == null) {
                                Chart.addSeries(d[h], d[h].series[j], i, null, null);
                            } else {
                                Chart.addSeries(d[h], d[h].series[j], i, Chart.selectedBlock.data('block-data').seriesId, null);
                            }
                        }

                        break;
                    }
                }
            }

            var header = jQuery("div.ganttview-vtheader");
            var newItem = jQuery("<div>", { "class": "ganttview-vtheader-item" });
            var newItemName = jQuery("<div>", { "class": "ganttview-vtheader-item-name" });
            var newSeries = jQuery("<div>", { "class": "ganttview-vtheader-series" });
            var newIdx = 0;

            if (Chart.selectedBlock != null) {
                for (var h = 0; h < data.length; h++) {
                    for (var i = 0; i < data[h].series.length; i++) {
                        if (Chart.selectedBlock.data('block-data').seriesId == data[h].series[i].id) {
                            var dataMerged = false;

                            for (var m = 0; m < d.length; m++) {
                                if (d[m].id == data[h].id) {
                                    var f = data[h].series.slice(0, i + 1);
                                    var g = data[h].series.slice(i + 1, data[h].series.length);
                                    $.merge(f, d[m].series);
                                    $.merge(f, g);

                                    delete data[h].series;
                                    Chart.opts.data[h].series = f;

                                    newIdx = h;

                                    dataMerged = true;
                                }
                            }

                            if (dataMerged == false) {
                                var f = data.slice(0, h + 1);
                                var g = data.slice(h + 1, data.length);
                                $.merge(f, d);
                                $.merge(f, g);
                                Chart.opts.data = f;

                                newIdx = h + 1;
                            }

                            break;
                        }
                    }
                }
            } else if (data.length == 0) {
                Chart.opts.data = d;
            }

            for (var h = 0; h < d.length; h++) {
                for (var i = 0; i < d[h].series.length; i++) {
                    if (d[h].series[i].merged == false) {
                        var cloneNewItem = newItem.clone();
                        cloneNewItem.attr("id", "ganttview-vtheader-item-" + d[h].id);
                        var cloneNewItemName = newItemName.clone();
                        cloneNewItemName.attr("id", "ganttview-vtheader-item-name-" + d[h].id);
                        cloneNewItemName.css("height", "0px");
                        cloneNewItemName.append(d[h].name);
                        cloneNewItem.append(cloneNewItemName);
                        cloneNewItem.append(newSeries.clone());

                        if (Chart.selectedBlock == null) {
                            Chart.opts.data.push(d[h]);
                            header.append(cloneNewItem);
                            Chart.addSeries(d[h], d[h].series[i], Chart.opts.data.length - 1, null, null);
                        } else {
                            header = jQuery("div#ganttview-vtheader-series-name-" + Chart.selectedBlock.data('block-data').seriesId).parent().parent();
                            header.after(cloneNewItem);
                            Chart.addSeries(d[h], d[h].series[i], newIdx, Chart.selectedBlock.data('block-data').seriesId, d[h].id);
                        }
                    }
                }
            }

            //console.debug("%o", data);
        }
    });
})(jQuery);
