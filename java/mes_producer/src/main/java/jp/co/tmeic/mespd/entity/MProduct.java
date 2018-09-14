package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 製品マスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 部分No */
    @Id
    @Column(length = 16, nullable = false, unique = false)
    public String partNo;

    /** 部分的な名前 */
    @Column(length = 36, nullable = false, unique = false)
    public String partName;

    /** 顧客名 */
    @Column(length = 16, nullable = true, unique = false)
    public String customerName;
    
    /** アイテムNo */
    @Column(length = 8, nullable = false, unique = false)
    public String itemNo;

    /** 通過設備(EP6/EP9) */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer platingMachine;
    
    /** モデル */
    @Column(length = 16, nullable = true, unique = false)
    public String model;

    /** 製品種別 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer productKind;

    /** MProcessComponentList関連プロパティ */
    @OneToMany(mappedBy = "MProduct")
    public List<MProcessComponent> MProcessComponentList;
}