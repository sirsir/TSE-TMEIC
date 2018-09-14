package jp.co.tmeic.mespd.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 操業カレンダ休憩設定マスタ
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"})
public class MWorkCalendarBreak implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 追加日 */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** 更新日 */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** 操業カレンダID */
    @Id
    @Column(length = 10, nullable = false, unique = false)
    public String workCalendarId;

    /** 休憩時間ID */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer breakTimeId;

    /** 休憩開始時間 */
    @Column(nullable = true, unique = false)
    public Time breakStartTime;

    /** 休憩終了時間 */
    @Column(nullable = true, unique = false)
    public Time breakEndTime;

    /** MWorkCalendar関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "work_calendar_id", referencedColumnName = "work_calendar_id")
    public MWorkCalendar MWorkCalendar;
}