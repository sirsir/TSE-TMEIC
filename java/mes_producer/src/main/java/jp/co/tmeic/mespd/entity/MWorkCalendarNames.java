package jp.co.tmeic.mespd.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MWorkCalendarBreakNames._MWorkCalendarBreakNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MWorkCalendar}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class MWorkCalendarNames {

    /**
     * createDateのプロパティ名を返します。
     * 
     * @return createDateのプロパティ名
     */
    public static PropertyName<Timestamp> createDate() {
        return new PropertyName<Timestamp>("createDate");
    }

    /**
     * updateDateのプロパティ名を返します。
     * 
     * @return updateDateのプロパティ名
     */
    public static PropertyName<Timestamp> updateDate() {
        return new PropertyName<Timestamp>("updateDate");
    }

    /**
     * workCalendarIdのプロパティ名を返します。
     * 
     * @return workCalendarIdのプロパティ名
     */
    public static PropertyName<String> workCalendarId() {
        return new PropertyName<String>("workCalendarId");
    }

    /**
     * workDayのプロパティ名を返します。
     * 
     * @return workDayのプロパティ名
     */
    public static PropertyName<Date> workDay() {
        return new PropertyName<Date>("workDay");
    }

    /**
     * holidayのプロパティ名を返します。
     * 
     * @return holidayのプロパティ名
     */
    public static PropertyName<Boolean> holiday() {
        return new PropertyName<Boolean>("holiday");
    }

    /**
     * workStartTimeのプロパティ名を返します。
     * 
     * @return workStartTimeのプロパティ名
     */
    public static PropertyName<Time> workStartTime() {
        return new PropertyName<Time>("workStartTime");
    }

    /**
     * workEndTimeのプロパティ名を返します。
     * 
     * @return workEndTimeのプロパティ名
     */
    public static PropertyName<Time> workEndTime() {
        return new PropertyName<Time>("workEndTime");
    }

    /**
     * MWorkCalendarBreakListのプロパティ名を返します。
     * 
     * @return MWorkCalendarBreakListのプロパティ名
     */
    public static _MWorkCalendarBreakNames MWorkCalendarBreakList() {
        return new _MWorkCalendarBreakNames("MWorkCalendarBreakList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MWorkCalendarNames extends PropertyName<MWorkCalendar> {

        /**
         * インスタンスを構築します。
         */
        public _MWorkCalendarNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MWorkCalendarNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _MWorkCalendarNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * createDateのプロパティ名を返します。
         *
         * @return createDateのプロパティ名
         */
        public PropertyName<Timestamp> createDate() {
            return new PropertyName<Timestamp>(this, "createDate");
        }

        /**
         * updateDateのプロパティ名を返します。
         *
         * @return updateDateのプロパティ名
         */
        public PropertyName<Timestamp> updateDate() {
            return new PropertyName<Timestamp>(this, "updateDate");
        }

        /**
         * workCalendarIdのプロパティ名を返します。
         *
         * @return workCalendarIdのプロパティ名
         */
        public PropertyName<String> workCalendarId() {
            return new PropertyName<String>(this, "workCalendarId");
        }

        /**
         * workDayのプロパティ名を返します。
         *
         * @return workDayのプロパティ名
         */
        public PropertyName<Date> workDay() {
            return new PropertyName<Date>(this, "workDay");
        }

        /**
         * holidayのプロパティ名を返します。
         *
         * @return holidayのプロパティ名
         */
        public PropertyName<Boolean> holiday() {
            return new PropertyName<Boolean>(this, "holiday");
        }

        /**
         * workStartTimeのプロパティ名を返します。
         *
         * @return workStartTimeのプロパティ名
         */
        public PropertyName<Time> workStartTime() {
            return new PropertyName<Time>(this, "workStartTime");
        }

        /**
         * workEndTimeのプロパティ名を返します。
         *
         * @return workEndTimeのプロパティ名
         */
        public PropertyName<Time> workEndTime() {
            return new PropertyName<Time>(this, "workEndTime");
        }

        /**
         * MWorkCalendarBreakListのプロパティ名を返します。
         * 
         * @return MWorkCalendarBreakListのプロパティ名
         */
        public _MWorkCalendarBreakNames MWorkCalendarBreakList() {
            return new _MWorkCalendarBreakNames(this, "MWorkCalendarBreakList");
        }
    }
}