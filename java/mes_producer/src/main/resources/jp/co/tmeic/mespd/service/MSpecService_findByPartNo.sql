SELECT
    *
FROM
    m_spec
WHERE
    spec_id IN
    (
        (
            SELECT
                spec_id
            FROM
                m_spec_process_component
            WHERE
                part_no = /*partNo*/'A'
        )
        UNION
        (
            SELECT
                spec_id
            FROM
                m_spec_product_component
            WHERE
                part_no = /*partNo*/'A'
        )
    )
ORDER BY
    spec_id