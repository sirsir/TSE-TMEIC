package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * プリンタマスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MPrinter implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** プリンタID */
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String printerId;

    /** プリンタ名 */
    @Column(length = 10, nullable = false, unique = false)
    public String printerName;

    /** IPアドレス */
    @Column(length = 15, nullable = true, unique = false)
    public String ipAddress;

    /** プリンタ種別 */
    @Column(length = 10, nullable = false, unique = false)
    public String printerType;
}