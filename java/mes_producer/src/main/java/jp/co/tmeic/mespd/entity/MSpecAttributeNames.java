package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MSpecNames._MSpecNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MSpecAttribute}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class MSpecAttributeNames {

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
     * specAttributeIdのプロパティ名を返します。
     * 
     * @return specAttributeIdのプロパティ名
     */
    public static PropertyName<Integer> specAttributeId() {
        return new PropertyName<Integer>("specAttributeId");
    }

    /**
     * specAttributeNameのプロパティ名を返します。
     * 
     * @return specAttributeNameのプロパティ名
     */
    public static PropertyName<String> specAttributeName() {
        return new PropertyName<String>("specAttributeName");
    }

    /**
     * MSpecListのプロパティ名を返します。
     * 
     * @return MSpecListのプロパティ名
     */
    public static _MSpecNames MSpecList() {
        return new _MSpecNames("MSpecList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MSpecAttributeNames extends PropertyName<MSpecAttribute> {

        /**
         * インスタンスを構築します。
         */
        public _MSpecAttributeNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MSpecAttributeNames(final String name) {
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
        public _MSpecAttributeNames(final PropertyName<?> parent, final String name) {
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
         * specAttributeIdのプロパティ名を返します。
         *
         * @return specAttributeIdのプロパティ名
         */
        public PropertyName<Integer> specAttributeId() {
            return new PropertyName<Integer>(this, "specAttributeId");
        }

        /**
         * specAttributeNameのプロパティ名を返します。
         *
         * @return specAttributeNameのプロパティ名
         */
        public PropertyName<String> specAttributeName() {
            return new PropertyName<String>(this, "specAttributeName");
        }

        /**
         * MSpecListのプロパティ名を返します。
         * 
         * @return MSpecListのプロパティ名
         */
        public _MSpecNames MSpecList() {
            return new _MSpecNames(this, "MSpecList");
        }
    }
}