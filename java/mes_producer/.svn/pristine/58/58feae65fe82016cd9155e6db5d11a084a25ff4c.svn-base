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
 * 前工程マスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MBeforeProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 部品No */
    @Id
    @Column(length = 10, nullable = false, unique = false)
    public String partNo;

    /** 工程構成No */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer processComponentNo;

    /** 前工程構成No */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer beforeProcessComponentNo;

    /** MProcessComponent関連プロパティ */
    @ManyToOne
    @JoinColumns( {
        @JoinColumn(name = "part_no", referencedColumnName = "part_no"),
        @JoinColumn(name = "process_component_no", referencedColumnName = "process_component_no") })
    public MProcessComponent MProcessComponent;
}