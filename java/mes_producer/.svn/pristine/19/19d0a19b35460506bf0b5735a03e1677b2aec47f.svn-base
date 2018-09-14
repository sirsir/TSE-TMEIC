package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 仕様計画
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DSpecPlan implements Serializable {

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

    /** 仕様ID */
    @Id
    @Column(length = 10, nullable = false, unique = false)
    public String specId;

    /** 仕様名 */
    @Column(length = 30, nullable = false, unique = false)
    public String specName;

    /** 仕様属性ID */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer specAttributeId;

    /** 仕様部品01 */
    @Column(name = "spec_parts_01", length = 7, nullable = true, unique = false)
    public String specParts01;

    /** 仕様部品02 */
    @Column(name = "spec_parts_02", length = 7, nullable = true, unique = false)
    public String specParts02;

    /** 仕様部品03 */
    @Column(name = "spec_parts_03", length = 7, nullable = true, unique = false)
    public String specParts03;

    /** 仕様部品04 */
    @Column(name = "spec_parts_04", length = 7, nullable = true, unique = false)
    public String specParts04;

    /** 仕様部品05 */
    @Column(name = "spec_parts_05", length = 7, nullable = true, unique = false)
    public String specParts05;

    /** 仕様部品06 */
    @Column(name = "spec_parts_06", length = 7, nullable = true, unique = false)
    public String specParts06;

    /** 仕様部品07 */
    @Column(name = "spec_parts_07", length = 7, nullable = true, unique = false)
    public String specParts07;

    /** 仕様部品08 */
    @Column(name = "spec_parts_08", length = 7, nullable = true, unique = false)
    public String specParts08;

    /** 仕様部品09 */
    @Column(name = "spec_parts_09", length = 7, nullable = true, unique = false)
    public String specParts09;

    /** 仕様部品10 */
    @Column(name = "spec_parts_10", length = 7, nullable = true, unique = false)
    public String specParts10;

    /** DSpecAttributePlan関連プロパティ */
    @ManyToOne
    @JoinColumns( {
        @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no"),
        @JoinColumn(name = "spec_attribute_id", referencedColumnName = "spec_attribute_id") })
    public DSpecAttributePlan DSpecAttributePlan;

    /** DSpecProcessPlanList関連プロパティ */
    @OneToMany(mappedBy = "DSpecPlan")
    public List<DSpecProcessPlan> DSpecProcessPlanList;

    /** DSpecProductPlanList関連プロパティ */
    @OneToMany(mappedBy = "DSpecPlan")
    public List<DSpecProductPlan> DSpecProductPlanList;
}