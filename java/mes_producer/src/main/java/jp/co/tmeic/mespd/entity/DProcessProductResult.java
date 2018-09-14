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
 * 工程内製品単位実績
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DProcessProductResult implements Serializable {

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

    /** 開始日時 */
    @Column(nullable = true, unique = false)
    public Timestamp startDate;

    /** 終了日時 */
    @Column(nullable = true, unique = false)
    public Timestamp endDate;

    /** 実績入力ユーザID */
    @Column(length = 10, nullable = true, unique = false)
    public String userId;

    /** 実績入力ユーザ名 */
    @Column(length = 50, nullable = true, unique = false)
    public String userName;

    /** 状態 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer status;

    /** 良否NG */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer quality;
    
    /**日付をチェックしなさい*/
    @Column(nullable = true, unique = false)
    public Timestamp checkDate;
    
    /** DMaterialProductResultList関連プロパティ */
    @OneToMany(mappedBy = "DProcessProductResult")
    public List<DMaterialProductResult> DMaterialProductResultList;

    /** DProcessPlan関連プロパティ */
    @ManyToOne
    @JoinColumns( {
        @JoinColumn(name = "product_plan_no", referencedColumnName = "product_plan_no"),
        @JoinColumn(name = "process_component_no", referencedColumnName = "process_component_no") })
    public DProcessPlan DProcessPlan;

    /** DSerialNo関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "serial_no", referencedColumnName = "serial_no")
    public DSerialNo DSerialNo;

    /** DSpecProductResultList関連プロパティ */
    @OneToMany(mappedBy = "DProcessProductResult")
    public List<DSpecProductResult> DSpecProductResultList;
}