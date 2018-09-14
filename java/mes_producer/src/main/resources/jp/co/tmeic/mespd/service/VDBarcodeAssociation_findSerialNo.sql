SELECT
   barcode_no
  , product_plan_no
  , serial_no
FROM
  v_d_barcode_association
WHERE
  barcode_no = /*barcodeNo*/
    AND
  product_plan_no =/*productPlanNo*/
