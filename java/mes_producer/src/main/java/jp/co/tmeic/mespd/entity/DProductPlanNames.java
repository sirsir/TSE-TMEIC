package jp.co.tmeic.mespd.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DProcessPlanNames._DProcessPlanNames;
import jp.co.tmeic.mespd.entity.DProductResultNames._DProductResultNames;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link DProductPlan}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DProductPlanNames {

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
     * manufactureDateのプロパティ名を返します。
     * 
     * @return manufactureDateのプロパティ名
     */
    public static PropertyName<Date> manufactureDate() {
        return new PropertyName<Date>("manufactureDate");
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
     * partNameのプロパティ名を返します。
     * 
     * @return partNameのプロパティ名
     */
    public static PropertyName<String> partName() {
        return new PropertyName<String>("partName");
    }

    /**
     * customerNameのプロパティ名を返します。
     * 
     * @return customerNameのプロパティ名
     */
    public static PropertyName<String> customerName() {
        return new PropertyName<String>("customerName");
    }
    
    /**
     * modelのプロパティ名を返します。
     * 
     * @return modelのプロパティ名
     */
    public static PropertyName<String> model() {
        return new PropertyName<String>("model");
    }
    
    /**
     * modelのプロパティ名を返します。
     * 
     * @return modelのプロパティ名
     */
    public static PropertyName<String> itemNo() {
        return new PropertyName<String>("itemNo");
    }
    /**
     * platingMachineのプロパティ名を返します。
     * 
     * @return platingMachineのプロパティ名
     */
    public static PropertyName<Integer> platingMachine() {
        return new PropertyName<Integer>("platingMachine");
    }

    /**
     * productKindのプロパティ名を返します。
     * 
     * @return productKindのプロパティ名
     */
    public static PropertyName<Integer> productKind() {
        return new PropertyName<Integer>("productKind");
    }

    /**
     * planQtyのプロパティ名を返します。
     * 
     * @return planQtyのプロパティ名
     */
    public static PropertyName<Integer> planQty() {
        return new PropertyName<Integer>("planQty");
    }

    /**
     * planStartDateのプロパティ名を返します。
     * 
     * @return planStartDateのプロパティ名
     */
    public static PropertyName<Timestamp> planStartDate() {
        return new PropertyName<Timestamp>("planStartDate");
    }

    /**
     * planEndDateのプロパティ名を返します。
     * 
     * @return planEndDateのプロパティ名
     */
    public static PropertyName<Timestamp> planEndDate() {
        return new PropertyName<Timestamp>("planEndDate");
    }

    /**
     * DProcessPlanListのプロパティ名を返します。
     * 
     * @return DProcessPlanListのプロパティ名
     */
    public static _DProcessPlanNames DProcessPlanList() {
        return new _DProcessPlanNames("DProcessPlanList");
    }

    /**
     * DProductResultのプロパティ名を返します。
     * 
     * @return DProductResultのプロパティ名
     */
    public static _DProductResultNames DProductResult() {
        return new _DProductResultNames("DProductResult");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _DProductPlanNames extends PropertyName<DProductPlan> {

        /**
         * インスタンスを構築します。
         */
        public _DProductPlanNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DProductPlanNames(final String name) {
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
        public _DProductPlanNames(final PropertyName<?> parent, final String name) {
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
         * manufactureDateのプロパティ名を返します。
         *
         * @return manufactureDateのプロパティ名
         */
        public PropertyName<Date> manufactureDate() {
            return new PropertyName<Date>(this, "manufactureDate");
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
         * partNameのプロパティ名を返します。
         *
         * @return partNameのプロパティ名
         */
        public PropertyName<String> partName() {
            return new PropertyName<String>(this, "partName");
        }

        /**
         * customerNameのプロパティ名を返します。
         *
         * @return customerNameのプロパティ名
         */
        public PropertyName<String> customerName() {
            return new PropertyName<String>(this, "customerName");
        }

        /**
         * modelのプロパティ名を返します。
         *
         * @return modelのプロパティ名
         */
        public PropertyName<String> model() {
            return new PropertyName<String>(this, "model");
        }

        /**
         * itemNoのプロパティ名を返します。
         *
         * @return itemNoのプロパティ名
         */
        public PropertyName<String> itemNo() {
            return new PropertyName<String>(this, "itemNo");
        }
        
        /**
         * platingMachineのプロパティ名を返します。
         *
         * @return platingMachineのプロパティ名
         */
        public PropertyName<Integer> platingMachine() {
            return new PropertyName<Integer>(this, "platingMachine");
        }
        
        /**
         * productKindのプロパティ名を返します。
         *
         * @return productKindのプロパティ名
         */
        public PropertyName<Integer> productKind() {
            return new PropertyName<Integer>(this, "productKind");
        }

        /**
         * planQtyのプロパティ名を返します。
         *
         * @return planQtyのプロパティ名
         */
        public PropertyName<Integer> planQty() {
            return new PropertyName<Integer>(this, "planQty");
        }

        /**
         * planStartDateのプロパティ名を返します。
         *
         * @return planStartDateのプロパティ名
         */
        public PropertyName<Timestamp> planStartDate() {
            return new PropertyName<Timestamp>(this, "planStartDate");
        }

        /**
         * planEndDateのプロパティ名を返します。
         *
         * @return planEndDateのプロパティ名
         */
        public PropertyName<Timestamp> planEndDate() {
            return new PropertyName<Timestamp>(this, "planEndDate");
        }

        /**
         * DProcessPlanListのプロパティ名を返します。
         * 
         * @return DProcessPlanListのプロパティ名
         */
        public _DProcessPlanNames DProcessPlanList() {
            return new _DProcessPlanNames(this, "DProcessPlanList");
        }

        /**
         * DProductResultのプロパティ名を返します。
         * 
         * @return DProductResultのプロパティ名
         */
        public _DProductResultNames DProductResult() {
            return new _DProductResultNames(this, "DProductResult");
        }
    }
}