package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.DMaterialPlanNames._DMaterialPlanNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link DMaterialProcessResult}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DMaterialProcessResultNames {

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
     * productPlanNoのプロパティ名を返します。
     * 
     * @return productPlanNoのプロパティ名
     */
    public static PropertyName<String> productPlanNo() {
        return new PropertyName<String>("productPlanNo");
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
     * materialComponentNoのプロパティ名を返します。
     * 
     * @return materialComponentNoのプロパティ名
     */
    public static PropertyName<Integer> materialComponentNo() {
        return new PropertyName<Integer>("materialComponentNo");
    }

    /**
     * materialQtyのプロパティ名を返します。
     * 
     * @return materialQtyのプロパティ名
     */
    public static PropertyName<Integer> materialQty() {
        return new PropertyName<Integer>("materialQty");
    }

    /**
     * DMaterialPlanのプロパティ名を返します。
     * 
     * @return DMaterialPlanのプロパティ名
     */
    public static _DMaterialPlanNames DMaterialPlan() {
        return new _DMaterialPlanNames("DMaterialPlan");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _DMaterialProcessResultNames extends PropertyName<DMaterialProcessResult> {

        /**
         * インスタンスを構築します。
         */
        public _DMaterialProcessResultNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DMaterialProcessResultNames(final String name) {
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
        public _DMaterialProcessResultNames(final PropertyName<?> parent, final String name) {
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
         * productPlanNoのプロパティ名を返します。
         *
         * @return productPlanNoのプロパティ名
         */
        public PropertyName<String> productPlanNo() {
            return new PropertyName<String>(this, "productPlanNo");
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
         * materialComponentNoのプロパティ名を返します。
         *
         * @return materialComponentNoのプロパティ名
         */
        public PropertyName<Integer> materialComponentNo() {
            return new PropertyName<Integer>(this, "materialComponentNo");
        }

        /**
         * materialQtyのプロパティ名を返します。
         *
         * @return materialQtyのプロパティ名
         */
        public PropertyName<Integer> materialQty() {
            return new PropertyName<Integer>(this, "materialQty");
        }

        /**
         * DMaterialPlanのプロパティ名を返します。
         * 
         * @return DMaterialPlanのプロパティ名
         */
        public _DMaterialPlanNames DMaterialPlan() {
            return new _DMaterialPlanNames(this, "DMaterialPlan");
        }
    }
}