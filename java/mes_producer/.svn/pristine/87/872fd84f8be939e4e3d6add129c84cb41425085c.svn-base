SELECT
    dProductPlan.create_date
  , dProductPlan.update_date
  , dProductPlan.product_plan_no
  , dProductPlan.manufacture_date
  , dProductPlan.part_no
  , (
        CASE
          WHEN (dProductResult.status IS NULL OR dProductResult.status = 0) THEN   -- 未開始のデータ
            mProduct.part_name
          ELSE
            dProductPlan.part_name
       END
    ) part_name
  , (
      CASE
        WHEN (dProductResult.status IS NULL OR dProductResult.status = 0) THEN   -- 未開始のデータ
          mProduct.product_kind
        ELSE
          dProductPlan.product_kind
      END
    ) product_kind
  , mProduct.customer_name
  , mProduct.model
  , dProductPlan.plan_qty
  , dProcessResult.result_qty
  , dProductPlan.plan_start_date
  , dProductPlan.plan_end_date
  , dProductResult.start_datetime
  , dProductResult.end_datetime
  , dProductResult.status
FROM
  d_product_plan dProductPlan
  LEFT OUTER JOIN
    m_product mProduct
    USING(part_no)
  LEFT OUTER JOIN
    d_product_result dProductResult
    USING(product_plan_no)
  LEFT OUTER JOIN
    (
      SELECT
        product_plan_no
        , result_qty
      FROM
        d_process_result
      WHERE
        (product_plan_no, process_component_no) IN (
          SELECT
            product_plan_no
            , MAX(process_component_no) AS process_component_no
          FROM
            d_process_plan
          GROUP BY
            product_plan_no
        )
    ) dProcessResult
      ON dProductPlan.product_plan_no = dProcessResult.product_plan_no
WHERE
  dProductPlan.manufacture_date
    BETWEEN
      /*manufactureStartDate*/'2015/06/01'
    AND
      /*ManufactureEndDate*/'2015/06/30'
ORDER BY
  dProductPlan.manufacture_date
  , dProductPlan.plan_start_date
  , dProductPlan.plan_end_date
  , dProductPlan.product_plan_no