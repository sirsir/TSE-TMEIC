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
 * 工程マスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 工程ID */
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String processId;

    /** 工程名 */
    @Column(length = 30, nullable = false, unique = false)
    public String processName;

    /** 管理されるので器用である */
    @Column(length = 1, nullable = false, unique = false)
    public Boolean handyManaged;
    
    /** MProcessComponentList関連プロパティ */
    @OneToMany(mappedBy = "MProcess")
    public List<MProcessComponent> MProcessComponentList;
}