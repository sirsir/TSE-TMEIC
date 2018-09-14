package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class DShippingTicketPrintQueue implements Serializable {

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
   
    /** 顧客名 */
    @Column(length = 16, nullable = true, unique = false)
	public String customerName;
    
    /** 部分的な数 */
    @Column(length = 16, nullable = true, unique = true)
    public String partNo;

    /** 部分的な名前 */
    @Column(length = 30, nullable = true, unique = false)
    public String partName;
   
    /** ロット No  */
    @Column(length = 30, nullable = true, unique = false)
    public String lotNo;
    
    /** 製造日 */
    @Column(length = 30, nullable = true, unique = false)
    public String mfgDate;
    
    /** プランコード*/
    @Column(length = 30, nullable = true, unique = false)
    public String planCode;
    
    /** 配達の時間*/
    @Column(length = 30, nullable = true, unique = false)
    public String timeOfDelivery;
    
    /** チェッカー*/
    @Column(length = 50, nullable = true, unique = false)
    public String checker;
    
    /** パッキング日付*/
    @Column(length = 30, nullable = true, unique = false)
    public String packingDate;
	
	/** モデル */
    @Column(length = 16, nullable = true, unique = false)
	public String model;
    
    /** 色 */
    @Column(length = 30, nullable = true, unique = false)
    public String color;
    
    /** シリアルNo*/
    @Column(length = 30, nullable = true, unique = false)
    public String serialNo;
    
    /** 数量 */
    @Column(length = 30, nullable = true, unique = false)
    public String quantity;
    
    /** 場所*/
    @Column(length = 30, nullable = true, unique = false)
    public String location;
    
    /** 品質ランク*/
    @Column(length = 30, nullable = true, unique = false)
    public String qualityRank;
   
}
