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

/**
 * 製品単位部材実績
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DMaterialProductResult implements Serializable {

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

    /** シリアルNo */
    @Id
    @Column(length = 12, nullable = false, unique = false)
    public String serialNo;

    /** 実績登録回数 */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer revision;

    /** 部材構成No */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer materialComponentNo;

    /** 使用数 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer materialQty;

    /** DMaterialPlan関連プロパティ */
    @ManyToOne
    @JoinColumns( {
        @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no"),
        @JoinColumn(name = "process_component_no", referencedColumnName = "process_component_no"),
        @JoinColumn(name = "material_component_no", referencedColumnName = "material_component_no") })
    public DMaterialPlan DMaterialPlan;

    /** DProcessProductResult関連プロパティ */
    @ManyToOne
    @JoinColumns( {
        @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no"),
        @JoinColumn(name = "process_component_no", referencedColumnName = "process_component_no"),
        @JoinColumn(name = "serial_no", referencedColumnName = "serial_no"),
        @JoinColumn(name = "revision", referencedColumnName = "revision") })
    public DProcessProductResult DProcessProductResult;
}