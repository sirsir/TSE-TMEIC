package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 工程計画
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DProcessPlan implements Serializable {

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

    /** 工程ID */
    @Column(length = 10, nullable = true, unique = false)
    public String processId;

    /** 工程名 */
    @Column(length = 30, nullable = false, unique = false)
    public String processName;

    /** 予定数量 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer planQty;

    /** 工程内容 */
    @Column(length = 30, nullable = true, unique = false)
    public String processContents;

    /** 標準時間(sec) */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer processTime;

    /** 標準人員 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer personnelRequired;

    /** 並列作業数 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer parallelWork;

    /** ユニットサイズ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer unitSize;

    /** 開始予定日時 */
    @Column(nullable = false, unique = false)
    public Timestamp planStartDate;

    /** 終了予定日時 */
    @Column(nullable = false, unique = false)
    public Timestamp planEndDate;

    /** DMaterialPlanList関連プロパティ */
    @OneToMany(mappedBy = "DProcessPlan")
    public List<DMaterialPlan> DMaterialPlanList;

    /** DProductPlan関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no")
    public DProductPlan DProductPlan;

    /** DProcessProductResultList関連プロパティ */
    @OneToMany(mappedBy = "DProcessPlan")
    public List<DProcessProductResult> DProcessProductResultList;

    /** DProcessResult関連プロパティ */
    @OneToOne(mappedBy = "DProcessPlan")
    public DProcessResult DProcessResult;

    /** DSpecProcessPlanList関連プロパティ */
    @OneToMany(mappedBy = "DProcessPlan")
    public List<DSpecProcessPlan> DSpecProcessPlanList;

    /** DSpecProductPlanList関連プロパティ */
    @OneToMany(mappedBy = "DProcessPlan")
    public List<DSpecProductPlan> DSpecProductPlanList;
}