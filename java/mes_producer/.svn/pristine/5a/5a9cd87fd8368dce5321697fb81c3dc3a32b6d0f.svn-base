package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 役割権限付与マスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MRoleAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 役割ID */
    @Id
    @Column(length = 10, nullable = false, unique = false)
    public String roleId;

    /** 権限ID */
    @Id
    @Column(length = 10, nullable = false, unique = false)
    public String authorityId;

    /** MAuthority関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "authority_id", referencedColumnName = "authority_id")
    public MAuthority MAuthority;

    /** MRole関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    public MRole MRole;
}