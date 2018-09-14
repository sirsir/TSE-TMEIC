package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 操業カレンダマスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MWorkCalendar implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 操業カレンダID */
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String workCalendarId;

    /** 設定日付 */
    @Column(nullable = false, unique = true)
    public Date workDay;

    /** 休日設定 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean holiday;

    /** 稼働時間開始 */
    @Column(nullable = true, unique = false)
    public Time workStartTime;

    /** 稼働時間終了 */
    @Column(nullable = true, unique = false)
    public Time workEndTime;

    /** MWorkCalendarBreakList関連プロパティ */
    @OneToMany(mappedBy = "MWorkCalendar")
    public List<MWorkCalendarBreak> MWorkCalendarBreakList;
}