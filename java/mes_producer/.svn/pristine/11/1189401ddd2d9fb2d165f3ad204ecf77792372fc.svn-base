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
 * ユーザマスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** ユーザID */
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String userId;

    /** ユーザ名 */
    @Column(length = 50, nullable = false, unique = false)
    public String userName;

    /** パスワード */
    @Column(length = 10, nullable = false, unique = false)
    public String userPassword;

    /** 表示フラグ */
    @Column(length = 1, nullable = false, unique = false)
    public Boolean visibleFlag;

    /** MUsersRoleList関連プロパティ */
    @OneToMany(mappedBy = "MUsers")
    public List<MUsersRole> MUsersRoleList;
}