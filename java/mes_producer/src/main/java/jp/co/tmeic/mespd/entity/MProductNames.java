package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MProcessComponentNames._MProcessComponentNames;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MProduct}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class MProductNames {

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
     * MProcessComponentListのプロパティ名を返します。
     * 
     * @return MProcessComponentListのプロパティ名
     */
    public static _MProcessComponentNames MProcessComponentList() {
        return new _MProcessComponentNames("MProcessComponentList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MProductNames extends PropertyName<MProduct> {

        /**
         * インスタンスを構築します。
         */
        public _MProductNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MProductNames(final String name) {
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
        public _MProductNames(final PropertyName<?> parent, final String name) {
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
         * partNameのプロパティ名を返します。
         *
         * @return partNameのプロパティ名
         */
        public PropertyName<String> partName() {
            return new PropertyName<String>(this, "partName");
        }
        
        /**
         * partNoのプロパティ名を返します。
         *
         * @return partNoのプロパティ名
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
         * MProcessComponentListのプロパティ名を返します。
         * 
         * @return MProcessComponentListのプロパティ名
         */
        public _MProcessComponentNames MProcessComponentList() {
            return new _MProcessComponentNames(this, "MProcessComponentList");
        }
    }
}