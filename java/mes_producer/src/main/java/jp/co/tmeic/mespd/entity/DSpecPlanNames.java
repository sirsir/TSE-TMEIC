package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.DSpecAttributePlanNames._DSpecAttributePlanNames;
import jp.co.tmeic.mespd.entity.DSpecProcessPlanNames._DSpecProcessPlanNames;
import jp.co.tmeic.mespd.entity.DSpecProductPlanNames._DSpecProductPlanNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link DSpecPlan}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DSpecPlanNames {

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
     * specIdのプロパティ名を返します。
     * 
     * @return specIdのプロパティ名
     */
    public static PropertyName<String> specId() {
        return new PropertyName<String>("specId");
    }

    /**
     * specNameのプロパティ名を返します。
     * 
     * @return specNameのプロパティ名
     */
    public static PropertyName<String> specName() {
        return new PropertyName<String>("specName");
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
     * specParts01のプロパティ名を返します。
     * 
     * @return specParts01のプロパティ名
     */
    public static PropertyName<String> specParts01() {
        return new PropertyName<String>("specParts01");
    }

    /**
     * specParts02のプロパティ名を返します。
     * 
     * @return specParts02のプロパティ名
     */
    public static PropertyName<String> specParts02() {
        return new PropertyName<String>("specParts02");
    }

    /**
     * specParts03のプロパティ名を返します。
     * 
     * @return specParts03のプロパティ名
     */
    public static PropertyName<String> specParts03() {
        return new PropertyName<String>("specParts03");
    }

    /**
     * specParts04のプロパティ名を返します。
     * 
     * @return specParts04のプロパティ名
     */
    public static PropertyName<String> specParts04() {
        return new PropertyName<String>("specParts04");
    }

    /**
     * specParts05のプロパティ名を返します。
     * 
     * @return specParts05のプロパティ名
     */
    public static PropertyName<String> specParts05() {
        return new PropertyName<String>("specParts05");
    }

    /**
     * specParts06のプロパティ名を返します。
     * 
     * @return specParts06のプロパティ名
     */
    public static PropertyName<String> specParts06() {
        return new PropertyName<String>("specParts06");
    }

    /**
     * specParts07のプロパティ名を返します。
     * 
     * @return specParts07のプロパティ名
     */
    public static PropertyName<String> specParts07() {
        return new PropertyName<String>("specParts07");
    }

    /**
     * specParts08のプロパティ名を返します。
     * 
     * @return specParts08のプロパティ名
     */
    public static PropertyName<String> specParts08() {
        return new PropertyName<String>("specParts08");
    }

    /**
     * specParts09のプロパティ名を返します。
     * 
     * @return specParts09のプロパティ名
     */
    public static PropertyName<String> specParts09() {
        return new PropertyName<String>("specParts09");
    }

    /**
     * specParts10のプロパティ名を返します。
     * 
     * @return specParts10のプロパティ名
     */
    public static PropertyName<String> specParts10() {
        return new PropertyName<String>("specParts10");
    }

    /**
     * DSpecAttributePlanのプロパティ名を返します。
     * 
     * @return DSpecAttributePlanのプロパティ名
     */
    public static _DSpecAttributePlanNames DSpecAttributePlan() {
        return new _DSpecAttributePlanNames("DSpecAttributePlan");
    }

    /**
     * DSpecProcessPlanListのプロパティ名を返します。
     * 
     * @return DSpecProcessPlanListのプロパティ名
     */
    public static _DSpecProcessPlanNames DSpecProcessPlanList() {
        return new _DSpecProcessPlanNames("DSpecProcessPlanList");
    }

    /**
     * DSpecProductPlanListのプロパティ名を返します。
     * 
     * @return DSpecProductPlanListのプロパティ名
     */
    public static _DSpecProductPlanNames DSpecProductPlanList() {
        return new _DSpecProductPlanNames("DSpecProductPlanList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _DSpecPlanNames extends PropertyName<DSpecPlan> {

        /**
         * インスタンスを構築します。
         */
        public _DSpecPlanNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DSpecPlanNames(final String name) {
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
        public _DSpecPlanNames(final PropertyName<?> parent, final String name) {
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
         * specIdのプロパティ名を返します。
         *
         * @return specIdのプロパティ名
         */
        public PropertyName<String> specId() {
            return new PropertyName<String>(this, "specId");
        }

        /**
         * specNameのプロパティ名を返します。
         *
         * @return specNameのプロパティ名
         */
        public PropertyName<String> specName() {
            return new PropertyName<String>(this, "specName");
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
         * specParts01のプロパティ名を返します。
         *
         * @return specParts01のプロパティ名
         */
        public PropertyName<String> specParts01() {
            return new PropertyName<String>(this, "specParts01");
        }

        /**
         * specParts02のプロパティ名を返します。
         *
         * @return specParts02のプロパティ名
         */
        public PropertyName<String> specParts02() {
            return new PropertyName<String>(this, "specParts02");
        }

        /**
         * specParts03のプロパティ名を返します。
         *
         * @return specParts03のプロパティ名
         */
        public PropertyName<String> specParts03() {
            return new PropertyName<String>(this, "specParts03");
        }

        /**
         * specParts04のプロパティ名を返します。
         *
         * @return specParts04のプロパティ名
         */
        public PropertyName<String> specParts04() {
            return new PropertyName<String>(this, "specParts04");
        }

        /**
         * specParts05のプロパティ名を返します。
         *
         * @return specParts05のプロパティ名
         */
        public PropertyName<String> specParts05() {
            return new PropertyName<String>(this, "specParts05");
        }

        /**
         * specParts06のプロパティ名を返します。
         *
         * @return specParts06のプロパティ名
         */
        public PropertyName<String> specParts06() {
            return new PropertyName<String>(this, "specParts06");
        }

        /**
         * specParts07のプロパティ名を返します。
         *
         * @return specParts07のプロパティ名
         */
        public PropertyName<String> specParts07() {
            return new PropertyName<String>(this, "specParts07");
        }

        /**
         * specParts08のプロパティ名を返します。
         *
         * @return specParts08のプロパティ名
         */
        public PropertyName<String> specParts08() {
            return new PropertyName<String>(this, "specParts08");
        }

        /**
         * specParts09のプロパティ名を返します。
         *
         * @return specParts09のプロパティ名
         */
        public PropertyName<String> specParts09() {
            return new PropertyName<String>(this, "specParts09");
        }

        /**
         * specParts10のプロパティ名を返します。
         *
         * @return specParts10のプロパティ名
         */
        public PropertyName<String> specParts10() {
            return new PropertyName<String>(this, "specParts10");
        }

        /**
         * DSpecAttributePlanのプロパティ名を返します。
         * 
         * @return DSpecAttributePlanのプロパティ名
         */
        public _DSpecAttributePlanNames DSpecAttributePlan() {
            return new _DSpecAttributePlanNames(this, "DSpecAttributePlan");
        }

        /**
         * DSpecProcessPlanListのプロパティ名を返します。
         * 
         * @return DSpecProcessPlanListのプロパティ名
         */
        public _DSpecProcessPlanNames DSpecProcessPlanList() {
            return new _DSpecProcessPlanNames(this, "DSpecProcessPlanList");
        }

        /**
         * DSpecProductPlanListのプロパティ名を返します。
         * 
         * @return DSpecProductPlanListのプロパティ名
         */
        public _DSpecProductPlanNames DSpecProductPlanList() {
            return new _DSpecProductPlanNames(this, "DSpecProductPlanList");
        }
    }
}