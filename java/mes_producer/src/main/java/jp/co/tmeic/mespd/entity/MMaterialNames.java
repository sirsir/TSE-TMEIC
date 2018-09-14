package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MMaterialComponentNames._MMaterialComponentNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MMaterial}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class MMaterialNames {

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
     * materialIdのプロパティ名を返します。
     * 
     * @return materialIdのプロパティ名
     */
    public static PropertyName<String> materialId() {
        return new PropertyName<String>("materialId");
    }

    /**
     * materialNameのプロパティ名を返します。
     * 
     * @return materialNameのプロパティ名
     */
    public static PropertyName<String> materialName() {
        return new PropertyName<String>("materialName");
    }

    /**
     * MMaterialComponentListのプロパティ名を返します。
     * 
     * @return MMaterialComponentListのプロパティ名
     */
    public static _MMaterialComponentNames MMaterialComponentList() {
        return new _MMaterialComponentNames("MMaterialComponentList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MMaterialNames extends PropertyName<MMaterial> {

        /**
         * インスタンスを構築します。
         */
        public _MMaterialNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MMaterialNames(final String name) {
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
        public _MMaterialNames(final PropertyName<?> parent, final String name) {
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
         * materialIdのプロパティ名を返します。
         *
         * @return materialIdのプロパティ名
         */
        public PropertyName<String> materialId() {
            return new PropertyName<String>(this, "materialId");
        }

        /**
         * materialNameのプロパティ名を返します。
         *
         * @return materialNameのプロパティ名
         */
        public PropertyName<String> materialName() {
            return new PropertyName<String>(this, "materialName");
        }

        /**
         * MMaterialComponentListのプロパティ名を返します。
         * 
         * @return MMaterialComponentListのプロパティ名
         */
        public _MMaterialComponentNames MMaterialComponentList() {
            return new _MMaterialComponentNames(this, "MMaterialComponentList");
        }
    }
}