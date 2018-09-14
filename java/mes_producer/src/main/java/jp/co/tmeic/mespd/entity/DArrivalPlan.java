package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 到着
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DArrivalPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 到着No */
    @Id
    @Column(length = 12, nullable = false, unique = true)
    public String arrivalNo;

    /** 到着の日付*/
    @Column(nullable = false, unique = false)
    public Date arrivalDate;

    /** 部分No */
    @Column(length = 16, nullable = false, unique = false)
    public String partNo;

    /** 部分的な名前 */
    @Column(length = 36, nullable = false, unique = false)
    public String partName;

    /** 顧客名 */
    @Column(length = 16, nullable = true, unique = false)
    public String customerName;
    
    /** モデル */
    @Column(length = 16, nullable = true, unique = false)
    public String model;
    
    /** アイテムNo */
    @Column(length = 8, nullable = false, unique = false)
    public String itemNo;

    /** 予定数量 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer planQty;
}