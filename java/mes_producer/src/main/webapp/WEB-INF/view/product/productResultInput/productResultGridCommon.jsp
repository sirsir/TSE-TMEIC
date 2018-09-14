<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">

function setProcessProductGridColumnState(gridId, materialPlanSize, specPlanSize, productPlans) {

  // set of material columns visible
  var processMaterialPlanMax = ${processMaterialPlanMax};
  var processMaterialSize = parseInt(materialPlanSize);
  var materialColumns = ["materialName","materialQty","materialUnit"];
  var materialColumnVisible = getColumnVisibleByNameWithIndex(gridId, processMaterialPlanMax, processMaterialSize, materialColumns);

  // set of spec columns visible
  var processProductSpecMax = ${processProductSpecPlanMax};
  var processProductSpecSize = parseInt(specPlanSize);
  var specColumns = ["specName","inputValue"];
  var specColumnVisible =getColumnVisibleByNameWithIndex(gridId, processProductSpecMax, processProductSpecSize, specColumns);

  $(gridId).showCol($.merge(materialColumnVisible.showColumnNames, specColumnVisible.showColumnNames));
  $(gridId).hideCol($.merge(materialColumnVisible.hideColumnNames, specColumnVisible.hideColumnNames));

  // set of spec columns
  setProcessProductSpecColumn(gridId, productPlans);
}

function setProcessProductSpecColumn(gridId, productPlans) {

  var processProductSpecMax = ${processProductSpecPlanMax};

  for (var i = 1; i <= processProductSpecMax; i++) {

    if ((("specId" + i) in productPlans) && (("specAttributeId") + i in productPlans)) {

          var specId = productPlans["specId" + i];
          var specAttributeId = String(productPlans["specAttributeId" + i]);
          var specParts = productPlans["specParts" + i];

          setSpecEditControlToGrid(gridId, "inputValue" + i, specId, specAttributeId, specParts);
    }
  }
}

/**
 * Based on the result data list, and combine the production plan data
 */
function combineResultListAndPlan(results, plan){

  var productData = results;

  $.each(productData, function(){
    combineResultAndPlan(this, plan);
  });

  return productData;
}

/**
 * Based on the result data, and combine the production plan data
 */
function combineResultAndPlan(results, plan){

  var productData = results;

  $.extend(productData, plan);

  return productData;
}

var processProductStatusStyleFmatter = function(rowId, val, rawObject, cm, rdata, name){

  if (!rdata.status){
    return "";
  }

  var color = processProductStatusColor[rdata.status];

  return  'style="color: ' + color + ';"';
}

</script>
