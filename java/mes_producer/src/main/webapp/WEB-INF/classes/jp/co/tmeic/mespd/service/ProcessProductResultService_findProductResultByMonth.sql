SELECT
  d_product_plan.part_no
  , d_product_plan.part_name
  , product_quality.end_date AS resultDate
  , CASE
    WHEN product_quality.quality IS NULL
    THEN 0
    ELSE product_quality.quality
    END
FROM
  (
    SELECT
      product_plan_no
    FROM
      d_product_plan
    WHERE
      plan_start_date BETWEEN /*startDay*/'2016/01/01' AND /*endDay*/'2016/01/01 23:59:59'
      OR plan_end_date BETWEEN /*startDay*/'2016/01/01' AND /*endDay*/'2016/01/01 23:59:59'
    UNION
    SELECT
      product_plan_no
    FROM
      d_process_product_result
    WHERE
      end_date BETWEEN /*startDay*/'2016/01/01' AND /*endDay*/'2016/01/01 23:59:59'
  ) target_product_plan
  INNER JOIN d_product_plan
    USING (product_plan_no)
  LEFT JOIN (
    SELECT
      product_plan_no
      , end_date
      , quality
    FROM
      d_process_product_result product_quality
    WHERE
      quality = 1
      AND process_component_no = (
        SELECT
          MAX(process_component_no) AS process_component_no
        FROM
          d_process_plan
        WHERE
          product_plan_no = product_quality.product_plan_no
        GROUP BY
          product_plan_no
      )
    UNION
    SELECT
      product_plan_no
      , end_date
      , quality
    FROM
      d_process_product_result
    WHERE
      quality = 3
  ) product_quality
    USING (product_plan_no)
WHERE
  end_date BETWEEN /*startDay*/'2016/01/01' AND /*endDay*/'2016/01/01 23:59:59'
ORDER BY
  d_product_plan.product_name

