package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

/**
 * 工程実績
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DProcessResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 製造計画No */
    @Id
    @Column(length = 12, nullable = false, unique = false)
    public String productPlanNo;

    /** 工程構成No */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer processComponentNo;

    /** 開始日時 */
    @Column(nullable = true, unique = false)
    public Timestamp startDatetime;

    /** 終了日時 */
    @Column(nullable = true, unique = false)
    public Timestamp endDatetime;

    /** 実績数量 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer resultQty;

    /** 不良数量 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer inferiorQty;

    /** 状態 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer status;

    /** 製品実績最終更新日時 */
    @Column(nullable = true, unique = false)
    public Timestamp productResultLastdate;

    /** DProcessPlan関連プロパティ */
    @OneToOne
    @JoinColumns( {
        @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no"),
        @JoinColumn(name = "process_component_no", referencedColumnName = "process_component_no") })
    public DProcessPlan DProcessPlan;
}