;(function($){
/**
 * jqGrid Japanese Translation
 * OKADA Yoshitada okada.dev@sth.jp
 * http://trirand.com/blog/
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
**/
$.jgrid = $.jgrid || {};
$.extend($.jgrid,{
	defaults : {
		recordtext: "{2} \u4ef6\u4e2d {0} - {1}",
	    emptyrecords: "\u8868\u793a\u3059\u308b\u30ec\u30b3\u30fc\u30c9\u304c\u3042\u308a\u307e\u305b\u3093",
		loadtext: "\u8aad\u307f\u8fbc\u307f\u4e2d...",
		pgtext : "{0} / {1}",
		savetext: "Saving...",
		pgfirst : "First Page",
		pglast : "Last Page",
		pgnext : "Next Page",
		pgprev : "Previous Page",
		pgrecs : "Records per Page",
		showhide: "Toggle Expand Collapse Grid"
	},
	search : {
	    caption: "\u691c\u7d22...",
	    Find: "\u691c\u7d22",
	    Reset: "\u30ea\u30bb\u30c3\u30c8",
	    odata: [{ oper:'eq', text:"\u6b21\u306b\u7b49\u3057\u3044"}, { oper:'ne', text:"\u6b21\u306b\u7b49\u3057\u304f\u306a\u3044"},
            { oper:'lt', text:"\u6b21\u3088\u308a\u5c0f\u3055\u3044"}, { oper:'le', text:"\u6b21\u306b\u7b49\u3057\u3044\u304b\u5c0f\u3055\u3044"},
            { oper:'gt', text:"\u6b21\u3088\u308a\u5927\u304d\u3044"}, { oper:'ge', text:"\u6b21\u306b\u7b49\u3057\u3044\u304b\u5927\u304d\u3044"},
            { oper:'bw', text:"\u6b21\u3067\u59cb\u307e\u308b"}, { oper:'bn', text:"\u6b21\u3067\u59cb\u307e\u3089\u306a\u3044"},
            { oper:'in', text:"\u6b21\u306b\u542b\u307e\u308c\u308b"}, { oper:'ni', text:"\u6b21\u306b\u542b\u307e\u308c\u306a\u3044"},
            { oper:'ew', text:"\u6b21\u3067\u7d42\u308f\u308b"}, { oper:'en', text:"\u6b21\u3067\u7d42\u308f\u3089\u306a\u3044"},
            { oper:'cn', text:"\u6b21\u3092\u542b\u3080"}, { oper:'nc', text:"\u6b21\u3092\u542b\u307e\u306a\u3044"},
			{ oper:'nu', text:'is null'},{ oper:'nn', text:'is not null'}],
	    groupOps: [{
                op: "AND",
                text: "\u3059\u3079\u3066\u306e"
            },
            {
                op: "OR",
                text: "\u3044\u305a\u308c\u304b\u306e"
            }],
		operandTitle : "Click to select search operation.",
		resetTitle : "Reset Search Value"
	},
	edit : {
	    addCaption: "\u30ec\u30b3\u30fc\u30c9\u8ffd\u52a0",
	    editCaption: "\u30ec\u30b3\u30fc\u30c9\u7de8\u96c6",
	    bSubmit: "\u9001\u4fe1",
	    bCancel: "\u30ad\u30e3\u30f3\u30bb\u30eb",
  		bClose: "\u9589\u3058\u308b",
      saveData: "\u30c7\u30fc\u30bf\u304c\u5909\u66f4\u3055\u308c\u3066\u3044\u307e\u3059\u3002\u4fdd\u5b58\u3057\u307e\u3059\u304b\uff1f",
      bYes: "\u306f\u3044",
      bNo: "\u3044\u3044\u3048",
      bExit: "\u30ad\u30e3\u30f3\u30bb\u30eb",
	    msg: {
	        required:"\u3053\u306e\u9805\u76ee\u306f\u5fc5\u9808\u3067\u3059\u3002",
	        number:"\u6b63\u3057\u3044\u6570\u5024\u3092\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044\u3002",
	        minValue:"\u6b21\u306e\u5024\u4ee5\u4e0a\u3067\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044\u3002",
	        maxValue:"\u6b21\u306e\u5024\u4ee5\u4e0b\u3067\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044\u3002",
	        email: "e-mail\u304c\u6b63\u3057\u304f\u3042\u308a\u307e\u305b\u3093\u3002",
	        integer: "\u6b63\u3057\u3044\u6574\u6570\u5024\u3092\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044\u3002",
    			date: "\u6b63\u3057\u3044\u5024\u3092\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044\u3002",
          url: "\u306f\u6709\u52b9\u306aURL\u3067\u306f\u3042\u308a\u307e\u305b\u3093\u3002\20\u30d7\u30ec\u30d5\u30a3\u30c3\u30af\u30b9\u304c\u5fc5\u8981\u3067\u3059\u3002 ('http://' \u307e\u305f\u306f 'https://')",
          nodefined: " \u304c\u5b9a\u7fa9\u3055\u308c\u3066\u3044\u307e\u305b\u3093",
          novalue: " \u623b\u308a\u5024\u304c\u5fc5\u8981\u3067\u3059",
          customarray: "\u30ab\u30b9\u30bf\u30e0\u95a2\u6570\u306f\u914d\u5217\u3092\u8fd4\u3059\u5fc5\u8981\u304c\u3042\u308a\u307e\u3059",
          customfcheck: "\u30ab\u30b9\u30bf\u30e0\u691c\u8a3c\u306b\u306f\u30ab\u30b9\u30bf\u30e0\u95a2\u6570\u304c\u5fc5\u8981\u3067\u3059"
		}
	},
	view : {
      caption: "\u30ec\u30b3\u30fc\u30c9\u3092\u8868\u793a",
      bClose: "\u9589\u3058\u308b"
	},
	del : {
	    caption: "\u524a\u9664",
	    msg: "\u9078\u629e\u3057\u305f\u30ec\u30b3\u30fc\u30c9\u3092\u524a\u9664\u3057\u307e\u3059\u304b\uff1f",
	    bSubmit: "\u524a\u9664",
	    bCancel: "\u30ad\u30e3\u30f3\u30bb\u30eb"
	},
	nav : {
    	edittext: " ",
	    edittitle: "\u9078\u629e\u3057\u305f\u884c\u3092\u7de8\u96c6",
      addtext:" ",
	    addtitle: "\u884c\u3092\u65b0\u898f\u8ffd\u52a0",
	    deltext: " ",
	    deltitle: "\u9078\u629e\u3057\u305f\u884c\u3092\u524a\u9664",
	    searchtext: " ",
	    searchtitle: "\u30ec\u30b3\u30fc\u30c9\u691c\u7d22",
	    refreshtext: "",
	    refreshtitle: "\u30b0\u30ea\u30c3\u30c9\u3092\u30ea\u30ed\u30fc\u30c9",
	    alertcap: "\u8b66\u544a",
	    alerttext: "\u884c\u3092\u9078\u629e\u3057\u3066\u4e0b\u3055\u3044\u3002",
      viewtext: "",
      viewtitle: "\u9078\u629e\u3057\u305f\u884c\u3092\u8868\u793a",
		savetext: "",
		savetitle: "Save row",
		canceltext: "",
		canceltitle : "Cancel row editing"
	},
	col : {
	    caption: "\u5217\u3092\u8868\u793a\uff0f\u96a0\u3059",
	    bSubmit: "\u9001\u4fe1",
	    bCancel: "\u30ad\u30e3\u30f3\u30bb\u30eb"
	},
	errors : {
		errcap : "\u30a8\u30e9\u30fc",
		nourl : "URL\u304c\u8a2d\u5b9a\u3055\u308c\u3066\u3044\u307e\u305b\u3093\u3002",
		norecords: "\u51e6\u7406\u5bfe\u8c61\u306e\u30ec\u30b3\u30fc\u30c9\u304c\u3042\u308a\u307e\u305b\u3093\u3002",
	    model : "colNames\u306e\u9577\u3055\u304ccolModel\u3068\u4e00\u81f4\u3057\u307e\u305b\u3093\u3002"
	},
	formatter : {
            integer: {
                thousandsSeparator: ",",
                defaultValue: '0'
            },
            number: {
                decimalSeparator: ".",
                thousandsSeparator: ",",
                decimalPlaces: 2,
                defaultValue: '0.00'
            },
            currency: {
                decimalSeparator: ".",
                thousandsSeparator: ",",
                decimalPlaces: 0,
                prefix: "",
                suffix: "",
                defaultValue: '0'
            },
		date : {
			dayNames:   [
				"\u65e5", "\u6708", "\u706b", "\u6c34", "\u6728", "\u91d1", "\u571f",
				"\u65e5", "\u6708", "\u706b", "\u6c34", "\u6728", "\u91d1", "\u571f"
			],
			monthNames: [
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"1\u6708", "2\u6708", "3\u6708", "4\u6708", "5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708", "11\u6708", "12\u6708"
			],
			AmPm : ["am","pm","AM","PM"],
			S: function (j) { return "\u756a\u76ee"; },
			srcformat: 'Y-m-d',
			newformat: 'd/m/Y',
			parseRe : /[#%\\\/:_;.,\t\s-]/,
			masks : {
	            ISO8601Long:"Y-m-d H:i:s",
	            ISO8601Short:"Y-m-d",
	            ShortDate: "n/j/Y",
	            LongDate: "l, F d, Y",
	            FullDateTime: "l, F d, Y g:i:s A",
	            MonthDay: "F d",
	            ShortTime: "g:i A",
	            LongTime: "g:i:s A",
	            SortableDateTime: "Y-m-d\\TH:i:s",
	            UniversalSortableDateTime: "Y-m-d H:i:sO",
	            YearMonth: "F, Y"
	        },
	        reformatAfterEdit : false,
			userLocalTime : false
		},
		baseLinkUrl: '',
		showAction: '',
	    target: '',
	    checkbox : {disabled:true},
		idName : 'id'
	}
});
})(jQuery);
