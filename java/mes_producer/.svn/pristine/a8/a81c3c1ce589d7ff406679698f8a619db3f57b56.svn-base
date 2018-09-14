SELECT
  material_id
  , material_name
  , material_unit
  , SUM(material_qty) AS material_qty
FROM
  (
    SELECT
      product_plan.manufacture_date
      , material_plan.material_id
      , material_plan.material_name
      , material_plan.material_unit
      , process_product_result.end_date AS result_date
      , CASE
        WHEN material_product_result.material_qty IS NULL
        THEN 0
        ELSE material_product_result.material_qty
        END material_qty
    FROM
      d_product_plan product_plan
      INNER JOIN d_material_plan material_plan
        USING (product_plan_no)
      LEFT JOIN d_material_product_result material_product_result
        USING (
          product_plan_no
          , process_component_no
          , material_component_no
        )
      INNER JOIN d_process_product_result process_product_result
        USING (
          product_plan_no
          , process_component_no
          , serial_no
          , revision
        )
    WHERE
      end_date BETWEEN /*startDay*/'2015/06/01' AND /*endDay*/'2015/06/30 23:59:59'
  ) material_result
GROUP BY
  material_id
  , material_name
  , material_unit
ORDER BY
  material_name
