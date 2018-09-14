package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MProcessComponentNames._MProcessComponentNames;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MProcess}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class MProcessNames {

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
     * processIdのプロパティ名を返します。
     * 
     * @return processIdのプロパティ名
     */
    public static PropertyName<String> processId() {
        return new PropertyName<String>("processId");
    }

    /**
     * processNameのプロパティ名を返します。
     * 
     * @return processNameのプロパティ名
     */
    public static PropertyName<String> processName() {
        return new PropertyName<String>("processName");
    }

    /**
     * MProcessComponentListのプロパティ名を返します。
     * 
     * @return MProcessComponentListのプロパティ名
     */
    public static _MProcessComponentNames MProcessComponentList() {
        return new _MProcessComponentNames("MProcessComponentList");
    }
    
    /**
     * handyManagedのプロパティ名を返します。
     * 
     * @return handyManagedのプロパティ名
     */
    public static PropertyName<Boolean> handyManaged() {
        return new PropertyName<Boolean>("handyManaged");
    }
    /**
     * @author S2JDBC-Gen
     */
    public static class _MProcessNames extends PropertyName<MProcess> {

        /**
         * インスタンスを構築します。
         */
        public _MProcessNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MProcessNames(final String name) {
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
        public _MProcessNames(final PropertyName<?> parent, final String name) {
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
         * processIdのプロパティ名を返します。
         *
         * @return processIdのプロパティ名
         */
        public PropertyName<String> processId() {
            return new PropertyName<String>(this, "processId");
        }

        /**
         * processNameのプロパティ名を返します。
         *
         * @return processNameのプロパティ名
         */
        public PropertyName<String> processName() {
            return new PropertyName<String>(this, "processName");
        }

        /**
         * MProcessComponentListのプロパティ名を返します。
         * 
         * @return MProcessComponentListのプロパティ名
         */
        public _MProcessComponentNames MProcessComponentList() {
            return new _MProcessComponentNames(this, "MProcessComponentList");
        }
        /**
         * handyManagedのプロパティ名を返します。
         *
         * @return handyManagedのプロパティ名
         */
        public PropertyName<Boolean> handyManaged() {
            return new PropertyName<Boolean>(this, "handyManaged");
        }
    }
}