package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.DMaterialPlanNames._DMaterialPlanNames;
import jp.co.tmeic.mespd.entity.DProcessProductResultNames._DProcessProductResultNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link DMaterialProductResult}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DMaterialProductResultNames {

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
     * serialNoのプロパティ名を返します。
     * 
     * @return serialNoのプロパティ名
     */
    public static PropertyName<String> serialNo() {
        return new PropertyName<String>("serialNo");
    }

    /**
     * revisionのプロパティ名を返します。
     * 
     * @return revisionのプロパティ名
     */
    public static PropertyName<Integer> revision() {
        return new PropertyName<Integer>("revision");
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
     * DProcessProductResultのプロパティ名を返します。
     * 
     * @return DProcessProductResultのプロパティ名
     */
    public static _DProcessProductResultNames DProcessProductResult() {
        return new _DProcessProductResultNames("DProcessProductResult");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _DMaterialProductResultNames extends PropertyName<DMaterialProductResult> {

        /**
         * インスタンスを構築します。
         */
        public _DMaterialProductResultNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DMaterialProductResultNames(final String name) {
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
        public _DMaterialProductResultNames(final PropertyName<?> parent, final String name) {
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
         * serialNoのプロパティ名を返します。
         *
         * @return serialNoのプロパティ名
         */
        public PropertyName<String> serialNo() {
            return new PropertyName<String>(this, "serialNo");
        }

        /**
         * revisionのプロパティ名を返します。
         *
         * @return revisionのプロパティ名
         */
        public PropertyName<Integer> revision() {
            return new PropertyName<Integer>(this, "revision");
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

        /**
         * DProcessProductResultのプロパティ名を返します。
         * 
         * @return DProcessProductResultのプロパティ名
         */
        public _DProcessProductResultNames DProcessProductResult() {
            return new _DProcessProductResultNames(this, "DProcessProductResult");
        }
    }
}