package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 製造計画
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DProductPlan implements Serializable {

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

    /** 製造日 */
    @Column(nullable = false, unique = false)
    public Date manufactureDate;

    /** 部分的な数 */
    @Column(length = 16, nullable = false, unique = true)
    public String partNo;

    /** 部分的な名前 */
    @Column(length = 30, nullable = false, unique = false)
    public String partName;
    
    /** 顧客名 */
    @Column(length = 16, nullable = true, unique = false)
	public String customerName;
	
	/** モデル*/
    @Column(length = 16, nullable = true, unique = false)
	public String model;

    /** アイテムNo */
    @Column(length = 8, nullable = false, unique = false)
    public String itemNo;

    /** 通過設備(EP6/EP9) */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer platingMachine;
    
    /** 製品種別 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer productKind;

    /** 予定数量 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer planQty;

    /** 開始予定日時 */
    @Column(nullable = false, unique = false)
    public Timestamp planStartDate;

    /** 終了予定日時 */
    @Column(nullable = false, unique = false)
    public Timestamp planEndDate;

    /** DProcessPlanList関連プロパティ */
    @OneToMany(mappedBy = "DProductPlan")
    public List<DProcessPlan> DProcessPlanList;

    /** DProductResult関連プロパティ */
    @OneToOne(mappedBy = "DProductPlan")
    public DProductResult DProductResult;
}