SELECT
  material.product_plan_no
  , material.process_component_no
  , material.material_component_no
  , SUM(material.material_qty) AS material_qty
FROM
  d_material_product_result material
  LEFT JOIN d_process_product_result product_result
    USING (
      product_plan_no
      , process_component_no
      , serial_no
      , revision
    )
WHERE
  material.product_plan_no = /*productPlanNo*/'1505260001'
  AND material.process_component_no = /*processComponentNo*/1
  AND product_result.status = 2
GROUP BY
  material.product_plan_no
  , material.process_component_no
  , material.material_component_no
ORDER BY
  material.product_plan_no
  , material.process_component_no
  , material.material_component_no
