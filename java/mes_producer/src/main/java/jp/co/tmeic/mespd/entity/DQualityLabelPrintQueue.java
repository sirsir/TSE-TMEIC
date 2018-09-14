package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DQualityLabelPrintQueue implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;
    
    /** プリンタId*/
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String printerId;
    
    /** 連続No*/
    @Id
    @Column(precision = 19, nullable = false, unique = false)
    public Long sequenceNo; 
   
    /** タイプをプリントしなさい*/
    @Column(length = 10, nullable = false, unique = true)
    public String printType;
   
    /** 品質バーコード No */
    @Column(length = 6, nullable = false, unique = true)
    public String qcBarcodeNo;
    
    /** バーコードNo */
    @Column(length = 6, nullable = false, unique = true)
    public String barcodeNo;
}
