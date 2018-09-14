SELECT
    dShipping.create_date
  , dShipping.update_date
  , dShipping.shipping_no
  , dShipping.shipping_date
  , dShipping.part_no
  , mProduct.part_name
  , mProduct.customer_name
  , mProduct.model
  , mProduct.item_no
  , dShipping.plan_qty
  , (select dShippingResult.result_qty from d_shipping_result dShippingResult where dShippingResult.shipping_no = dShipping.shipping_no and dShippingResult.result_qty<>0) result_qty
FROM
  d_shipping_plan dShipping
  LEFT OUTER JOIN
    m_product mProduct
    USING(part_no)
WHERE
  dShipping.shipping_date
    BETWEEN
      /*shippingStartDate*/'2015/06/01'
    AND
      /*shippingEndDate*/'2015/06/30'
ORDER BY
  dShipping.shipping_date
  , dShipping.shipping_no