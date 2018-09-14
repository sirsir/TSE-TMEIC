package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MProcessComponentNames._MProcessComponentNames;
import jp.co.tmeic.mespd.entity.MSpecNames._MSpecNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MSpecProductComponent}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class MSpecProductComponentNames {

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
     * specComponentNoのプロパティ名を返します。
     * 
     * @return specComponentNoのプロパティ名
     */
    public static PropertyName<Integer> specComponentNo() {
        return new PropertyName<Integer>("specComponentNo");
    }

    /**
     * specIdのプロパティ名を返します。
     * 
     * @return specIdのプロパティ名
     */
    public static PropertyName<String> specId() {
        return new PropertyName<String>("specId");
    }

    /**
     * displayOrderのプロパティ名を返します。
     * 
     * @return displayOrderのプロパティ名
     */
    public static PropertyName<Integer> displayOrder() {
        return new PropertyName<Integer>("displayOrder");
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
     * MSpecのプロパティ名を返します。
     * 
     * @return MSpecのプロパティ名
     */
    public static _MSpecNames MSpec() {
        return new _MSpecNames("MSpec");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MSpecProductComponentNames extends PropertyName<MSpecProductComponent> {

        /**
         * インスタンスを構築します。
         */
        public _MSpecProductComponentNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MSpecProductComponentNames(final String name) {
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
        public _MSpecProductComponentNames(final PropertyName<?> parent, final String name) {
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
         * specComponentNoのプロパティ名を返します。
         *
         * @return specComponentNoのプロパティ名
         */
        public PropertyName<Integer> specComponentNo() {
            return new PropertyName<Integer>(this, "specComponentNo");
        }

        /**
         * specIdのプロパティ名を返します。
         *
         * @return specIdのプロパティ名
         */
        public PropertyName<String> specId() {
            return new PropertyName<String>(this, "specId");
        }

        /**
         * displayOrderのプロパティ名を返します。
         *
         * @return displayOrderのプロパティ名
         */
        public PropertyName<Integer> displayOrder() {
            return new PropertyName<Integer>(this, "displayOrder");
        }

        /**
         * MProcessComponentのプロパティ名を返します。
         * 
         * @return MProcessComponentのプロパティ名
         */
        public _MProcessComponentNames MProcessComponent() {
            return new _MProcessComponentNames(this, "MProcessComponent");
        }

        /**
         * MSpecのプロパティ名を返します。
         * 
         * @return MSpecのプロパティ名
         */
        public _MSpecNames MSpec() {
            return new _MSpecNames(this, "MSpec");
        }
    }
}