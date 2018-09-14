SELECT
  spp.spec_component_no
  , spp.spec_id
  , sp.spec_name
  , spp.display_order
  , sp.spec_attribute_id
  , sap.spec_attribute_name
  , sp.spec_parts_01
  , sp.spec_parts_02
  , sp.spec_parts_03
  , sp.spec_parts_04
  , sp.spec_parts_05
  , sp.spec_parts_06
  , sp.spec_parts_07
  , sp.spec_parts_08
  , sp.spec_parts_09
  , sp.spec_parts_10
  , spr.input_value
FROM
  d_spec_process_plan spp
  INNER JOIN d_spec_plan sp
    USING (product_plan_no, spec_id)
  INNER JOIN d_spec_attribute_plan sap
    USING (product_plan_no, spec_attribute_id)
  LEFT JOIN d_spec_process_result spr
    USING (product_plan_no, process_component_no, spec_component_no)
WHERE
  spp.product_plan_no = /*productPlanNo*/'1505260001'
  AND spp.process_component_no = /*processComponentNo*/1
ORDER BY
  spp.display_order
