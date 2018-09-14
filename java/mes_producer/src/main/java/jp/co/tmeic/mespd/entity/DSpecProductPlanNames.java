package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.DProcessPlanNames._DProcessPlanNames;
import jp.co.tmeic.mespd.entity.DSpecPlanNames._DSpecPlanNames;
import jp.co.tmeic.mespd.entity.DSpecProductResultNames._DSpecProductResultNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link DSpecProductPlan}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DSpecProductPlanNames {

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
     * DProcessPlanのプロパティ名を返します。
     * 
     * @return DProcessPlanのプロパティ名
     */
    public static _DProcessPlanNames DProcessPlan() {
        return new _DProcessPlanNames("DProcessPlan");
    }

    /**
     * DSpecPlanのプロパティ名を返します。
     * 
     * @return DSpecPlanのプロパティ名
     */
    public static _DSpecPlanNames DSpecPlan() {
        return new _DSpecPlanNames("DSpecPlan");
    }

    /**
     * DSpecProductResultListのプロパティ名を返します。
     * 
     * @return DSpecProductResultListのプロパティ名
     */
    public static _DSpecProductResultNames DSpecProductResultList() {
        return new _DSpecProductResultNames("DSpecProductResultList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _DSpecProductPlanNames extends PropertyName<DSpecProductPlan> {

        /**
         * インスタンスを構築します。
         */
        public _DSpecProductPlanNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DSpecProductPlanNames(final String name) {
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
        public _DSpecProductPlanNames(final PropertyName<?> parent, final String name) {
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
         * DProcessPlanのプロパティ名を返します。
         * 
         * @return DProcessPlanのプロパティ名
         */
        public _DProcessPlanNames DProcessPlan() {
            return new _DProcessPlanNames(this, "DProcessPlan");
        }

        /**
         * DSpecPlanのプロパティ名を返します。
         * 
         * @return DSpecPlanのプロパティ名
         */
        public _DSpecPlanNames DSpecPlan() {
            return new _DSpecPlanNames(this, "DSpecPlan");
        }

        /**
         * DSpecProductResultListのプロパティ名を返します。
         * 
         * @return DSpecProductResultListのプロパティ名
         */
        public _DSpecProductResultNames DSpecProductResultList() {
            return new _DSpecProductResultNames(this, "DSpecProductResultList");
        }
    }
}