package jp.co.tmeic.mespd.entity;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link DArrivalPlan}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DArrivalPlanNames {

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
     * arrivalNoのプロパティ名を返します。
     * 
     * @return arrivalNoのプロパティ名
     */
    public static PropertyName<String> arrivalNo() {
        return new PropertyName<String>("arrivalNo");
    }

    /**
     * arrivalDateのプロパティ名を返します。
     * 
     * @return arrivalDateのプロパティ名
     */
    public static PropertyName<Date> arrivalDate() {
        return new PropertyName<Date>("arrivalDate");
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
     * itemNoのプロパティ名を返します。
     * 
     * @return itemNoのプロパティ名
     */
    public static PropertyName<String> itemNo() {
        return new PropertyName<String>("itemNo");
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
     * resultQtyのプロパティ名を返します。
     * 
     * @return resultQtyのプロパティ名
     */
    public static PropertyName<Integer> resultQty() {
        return new PropertyName<Integer>("resultQty");
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
     * @author S2JDBC-Gen
     */
    public static class _DArrivalNames extends PropertyName<DArrivalPlan> {

        /**
         * インスタンスを構築します。
         */
        public _DArrivalNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DArrivalNames(final String name) {
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
        public _DArrivalNames(final PropertyName<?> parent, final String name) {
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
         * arrivalNoのプロパティ名を返します。
         *
         * @return arrivalNoのプロパティ名
         */
        public PropertyName<String> arrivalNo() {
            return new PropertyName<String>(this, "arrivalNo");
        }

        /**
         * arrivalDateのプロパティ名を返します。
         *
         * @return arrivalDateのプロパティ名
         */
        public PropertyName<Date> arrivalDate() {
            return new PropertyName<Date>(this, "arrivalDate");
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
         * planQtyのプロパティ名を返します。
         *
         * @return planQtyのプロパティ名
         */
        public PropertyName<Integer> planQty() {
            return new PropertyName<Integer>(this, "planQty");
        }
    }
}