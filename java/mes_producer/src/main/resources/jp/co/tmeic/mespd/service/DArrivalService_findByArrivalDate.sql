SELECT
    dArrival.create_date
  , dArrival.update_date
  , dArrival.arrival_no
  , dArrival.arrival_date
  , dArrival.part_no
  , mProduct.part_name
  , mProduct.customer_name
  , mProduct.model
  , mProduct.item_no
  , dArrival.plan_qty
  , (select dArrivalResult.result_qty from d_arrival_result dArrivalResult where dArrivalResult.arrival_no = dArrival.arrival_no) result_qty
FROM
  d_arrival_plan dArrival
  LEFT OUTER JOIN
    m_product mProduct
    USING(part_no)
WHERE
  dArrival.arrival_date
    BETWEEN
      /*arrivalStartDate*/'2015/06/01'
    AND
      /*arrivalEndDate*/'2015/06/30'
ORDER BY
  dArrival.arrival_date
  , dArrival.arrival_no