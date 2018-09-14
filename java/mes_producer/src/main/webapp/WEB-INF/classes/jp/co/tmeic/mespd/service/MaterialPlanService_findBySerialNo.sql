SELECT
  mp.product_plan_no
  , mp.process_component_no
  , mp.material_component_no
  , mp.material_id
  , mp.material_name
  , mp.material_qty AS material_qty_plan
  , mp.material_unit
  , mp.display_order
  , mr.serial_no
  , mr.revision
  , mr.material_qty
FROM
  d_material_plan mp
  LEFT JOIN d_material_product_result mr
    ON mp.product_plan_no = mr.product_plan_no
    AND mp.process_component_no = mr.process_component_no
    AND mp.material_component_no = mr.material_component_no
    AND mr.serial_no = /*serialNo*/''
    /*IF revision != null*/
    AND mr.revision = /*revision*/1
    /*END*/
    /*IF revision == null*/
    AND mr.revision IS NULL
    /*END*/
WHERE
  mp.product_plan_no = /*productPlanNo*/'1505260001'
  AND mp.process_component_no = /*processComponentNo*/1
ORDER BY
  mp.display_order
