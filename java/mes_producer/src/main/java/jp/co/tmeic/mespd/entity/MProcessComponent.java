package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 工程構成マスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MProcessComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 部品No */
    @Id
    @Column(length = 16, nullable = false, unique = false)
    public String partNo;

    /** 工程構成No */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer processComponentNo;

    /** 工程ID */
    @Column(length = 10, nullable = false, unique = false)
    public String processId;

    /** 工程内容 */
    @Column(length = 30, nullable = true, unique = false)
    public String processContents;

    /** 標準時間(sec) */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer processTime;

    /** 標準人員 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer personnelRequired;

    /** 並列作業数 */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer parallelWork;

    /** ユニットサイズ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer unitSize;

    /** 工程順 */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer processOrder;

    /** MBeforeProcessList関連プロパティ */
    @OneToMany(mappedBy = "MProcessComponent")
    public List<MBeforeProcess> MBeforeProcessList;

    /** MMaterialComponentList関連プロパティ */
    @OneToMany(mappedBy = "MProcessComponent")
    public List<MMaterialComponent> MMaterialComponentList;

    /** MProcess関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "process_id", referencedColumnName = "process_id")
    public MProcess MProcess;

    /** MProduct関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "part_no", referencedColumnName = "part_no")
    public MProduct MProduct;

    /** MSpecProcessComponentList関連プロパティ */
    @OneToMany(mappedBy = "MProcessComponent")
    public List<MSpecProcessComponent> MSpecProcessComponentList;

    /** MSpecProductComponentList関連プロパティ */
    @OneToMany(mappedBy = "MProcessComponent")
    public List<MSpecProductComponent> MSpecProductComponentList;
}