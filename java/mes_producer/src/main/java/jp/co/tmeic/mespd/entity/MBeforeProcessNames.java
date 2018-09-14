package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MProcessComponentNames._MProcessComponentNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MBeforeProcess}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class MBeforeProcessNames {

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
     * partNoのプロパティ名を返します。
     * 
     * @return partNoのプロパティ名
     */
    public static PropertyName<String> partNo() {
        return new PropertyName<String>("partNo");
    }

    /**
     * processComponentNoのプロパティ名を返します。
     * 
     * @return processComponentNoのプロパティ名
     */
    public static PropertyName<Integer> processComponentNo() {
        return new PropertyName<Integer>("processComponentNo");
    }

    /**
     * beforeProcessComponentNoのプロパティ名を返します。
     * 
     * @return beforeProcessComponentNoのプロパティ名
     */
    public static PropertyName<Integer> beforeProcessComponentNo() {
        return new PropertyName<Integer>("beforeProcessComponentNo");
    }

    /**
     * MProcessComponentのプロパティ名を返します。
     * 
     * @return MProcessComponentのプロパティ名
     */
    public static _MProcessComponentNames MProcessComponent() {
        return new _MProcessComponentNames("MProcessComponent");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MBeforeProcessNames extends PropertyName<MBeforeProcess> {

        /**
         * インスタンスを構築します。
         */
        public _MBeforeProcessNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MBeforeProcessNames(final String name) {
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
        public _MBeforeProcessNames(final PropertyName<?> parent, final String name) {
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
         * partNoのプロパティ名を返します。
         *
         * @return partNoのプロパティ名
         */
        public PropertyName<String> partNo() {
            return new PropertyName<String>(this, "partNo");
        }

        /**
         * processComponentNoのプロパティ名を返します。
         *
         * @return processComponentNoのプロパティ名
         */
        public PropertyName<Integer> processComponentNo() {
            return new PropertyName<Integer>(this, "processComponentNo");
        }

        /**
         * beforeProcessComponentNoのプロパティ名を返します。
         *
         * @return beforeProcessComponentNoのプロパティ名
         */
        public PropertyName<Integer> beforeProcessComponentNo() {
            return new PropertyName<Integer>(this, "beforeProcessComponentNo");
        }

        /**
         * MProcessComponentのプロパティ名を返します。
         * 
         * @return MProcessComponentのプロパティ名
         */
        public _MProcessComponentNames MProcessComponent() {
            return new _MProcessComponentNames(this, "MProcessComponent");
        }
    }
}