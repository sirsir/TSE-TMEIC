package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 製造実績
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DProductResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 製造計画No */
    @Id
    @Column(length = 12, nullable = false, unique = true)
    public String productPlanNo;

    /** 開始日時 */
    @Column(nullable = true, unique = false)
    public Timestamp startDatetime;

    /** 終了日時 */
    @Column(nullable = true, unique = false)
    public Timestamp endDatetime;

    /** 状態 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer status;

    /** DProductPlan関連プロパティ */
    @OneToOne
    @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no")
    public DProductPlan DProductPlan;
}