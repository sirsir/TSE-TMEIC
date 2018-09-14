SELECT
    *
FROM
    m_spec_attribute
WHERE
    spec_attribute_id IN
    (
        SELECT
            spec_attribute_id
        FROM
            m_spec
        WHERE
            spec_attribute_id IN
            (
                (
                    SELECT
                        spec_attribute_id
                    FROM
                        m_spec_process_component
                    WHERE
                        part_no = /*partNo*/'A'
                )
                UNION
                (
                    SELECT
                        spec_attribute_id
                    FROM
                        m_spec_product_component
                    WHERE
                        part_no = /*partNo*/'A'
                )
            )
    )
ORDER BY
    spec_attribute_id