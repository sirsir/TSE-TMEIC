package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.DProcessProductResultNames._DProcessProductResultNames;
import jp.co.tmeic.mespd.entity.DSpecProductPlanNames._DSpecProductPlanNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link DSpecProductResult}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DSpecProductResultNames {

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
     * specComponentNoのプロパティ名を返します。
     * 
     * @return specComponentNoのプロパティ名
     */
    public static PropertyName<Integer> specComponentNo() {
        return new PropertyName<Integer>("specComponentNo");
    }

    /**
     * inputValueのプロパティ名を返します。
     * 
     * @return inputValueのプロパティ名
     */
    public static PropertyName<String> inputValue() {
        return new PropertyName<String>("inputValue");
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
     * DSpecProductPlanのプロパティ名を返します。
     * 
     * @return DSpecProductPlanのプロパティ名
     */
    public static _DSpecProductPlanNames DSpecProductPlan() {
        return new _DSpecProductPlanNames("DSpecProductPlan");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _DSpecProductResultNames extends PropertyName<DSpecProductResult> {

        /**
         * インスタンスを構築します。
         */
        public _DSpecProductResultNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DSpecProductResultNames(final String name) {
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
        public _DSpecProductResultNames(final PropertyName<?> parent, final String name) {
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
         * specComponentNoのプロパティ名を返します。
         *
         * @return specComponentNoのプロパティ名
         */
        public PropertyName<Integer> specComponentNo() {
            return new PropertyName<Integer>(this, "specComponentNo");
        }

        /**
         * inputValueのプロパティ名を返します。
         *
         * @return inputValueのプロパティ名
         */
        public PropertyName<String> inputValue() {
            return new PropertyName<String>(this, "inputValue");
        }

        /**
         * DProcessProductResultのプロパティ名を返します。
         * 
         * @return DProcessProductResultのプロパティ名
         */
        public _DProcessProductResultNames DProcessProductResult() {
            return new _DProcessProductResultNames(this, "DProcessProductResult");
        }

        /**
         * DSpecProductPlanのプロパティ名を返します。
         * 
         * @return DSpecProductPlanのプロパティ名
         */
        public _DSpecProductPlanNames DSpecProductPlan() {
            return new _DSpecProductPlanNames(this, "DSpecProductPlan");
        }
    }
}