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
import javax.persistence.OneToOne;

/**
 * 部材計画
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DMaterialPlan implements Serializable {

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

    /** 部材構成No */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer materialComponentNo;

    /** 部材ID */
    @Column(length = 10, nullable = false, unique = false)
    public String materialId;

    /** 部材名 */
    @Column(length = 30, nullable = false, unique = false)
    public String materialName;

    /** 使用数 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer materialQty;

    /** 単位 */
    @Column(length = 10, nullable = true, unique = false)
    public String materialUnit;

    /** 部材表示順 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer displayOrder;

    /** DProcessPlan関連プロパティ */
    @ManyToOne
    @JoinColumns( {
        @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no"),
        @JoinColumn(name = "process_component_no", referencedColumnName = "process_component_no") })
    public DProcessPlan DProcessPlan;

    /** DMaterialProcessResult関連プロパティ */
    @OneToOne(mappedBy = "DMaterialPlan")
    public DMaterialProcessResult DMaterialProcessResult;

    /** DMaterialProductResultList関連プロパティ */
    @OneToMany(mappedBy = "DMaterialPlan")
    public List<DMaterialProductResult> DMaterialProductResultList;
}