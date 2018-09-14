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
 * 仕様属性マスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MSpecAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 仕様属性ID */
    @Id
    @Column(precision = 10, nullable = false, unique = true)
    public Integer specAttributeId;

    /** 仕様属性名 */
    @Column(length = 30, nullable = false, unique = false)
    public String specAttributeName;

    /** MSpecList関連プロパティ */
    @OneToMany(mappedBy = "MSpecAttribute")
    public List<MSpec> MSpecList;
}