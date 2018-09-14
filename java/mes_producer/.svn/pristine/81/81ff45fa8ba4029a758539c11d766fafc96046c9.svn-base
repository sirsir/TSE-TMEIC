package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DLatestQualityBarcode implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** バーコードNo */
    @Id
    @Column(length = 6, nullable = false, unique = true)
    public String barcodeNo;

}
