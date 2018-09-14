SELECT
  d_product_plan.part_no
FROM
  d_process_product_result
  INNER JOIN d_product_plan
    USING (product_plan_no)
WHERE
  serial_no = /*serialNo*/'150702000046'
ORDER BY
  d_process_product_result.create_date DESC
