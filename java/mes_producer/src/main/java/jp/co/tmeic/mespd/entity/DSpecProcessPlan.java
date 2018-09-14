package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 工程単位仕様計画
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DSpecProcessPlan implements Serializable {

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

    /** 仕様構成No */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer specComponentNo;

    /** 仕様ID */
    @Column(length = 10, nullable = false, unique = false)
    public String specId;

    /** 仕様表示順 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer displayOrder;

    /** DProcessPlan関連プロパティ */
    @ManyToOne
    @JoinColumns( {
        @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no"),
        @JoinColumn(name = "process_component_no", referencedColumnName = "process_component_no") })
    public DProcessPlan DProcessPlan;

    /** DSpecPlan関連プロパティ */
    @ManyToOne
    @JoinColumns( {
        @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no"),
        @JoinColumn(name = "spec_id", referencedColumnName = "spec_id") })
    public DSpecPlan DSpecPlan;

    /** DSpecProcessResult関連プロパティ */
    @OneToOne(mappedBy = "DSpecProcessPlan")
    public DSpecProcessResult DSpecProcessResult;
}