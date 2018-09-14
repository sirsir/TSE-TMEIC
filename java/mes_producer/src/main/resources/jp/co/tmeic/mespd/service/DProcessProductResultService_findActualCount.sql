SELECT
  COUNT(*) AS count
FROM
  (
    SELECT
      serial_no
    FROM
      d_process_product_result
    WHERE
      product_plan_no = /*productPlanNo*/'1506160001'
      AND process_component_no = /*processComponentNo*/1
      AND status = 2
      AND quality = 1
      GROUP BY serial_no
  ) Actual