package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MRoleNames._MRoleNames;

import org.seasar.extension.jdbc.name.PropertyName;

@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DQualityLabelPrintQueueNames {
	
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
     * barcodeNoのプロパティ名を返します。
     * 
     * @return barcodeNoのプロパティ名
     */
    public static PropertyName<String> barcodeNo() {
        return new PropertyName<String>("barcodeNo");
    }

    /**
     * printerIdのプロパティ名を返します。
     * 
     * @return printerIdのプロパティ名
     */
    public static PropertyName<String> printerId() {
        return new PropertyName<String>("printerId");
    }

    /**
     * printTypeのプロパティ名を返します。
     * 
     * @return printTypeのプロパティ名
     */
    public static PropertyName<String>  printType() {
        return new PropertyName<String>("printType");
    }
    /**
     * qcBarcodeNoのプロパティ名を返します。
     * 
     * @return qcBarcodeNoのプロパティ名
     */
    public static PropertyName<String> qcBarcodeNo() {
        return new PropertyName<String>("qcBarcodeNo");
    }

    /**
     * sequenceNoのプロパティ名を返します。
     * 
     * @return sequenceNoのプロパティ名
     */
    public static PropertyName<Long>  sequenceNo() {
        return new PropertyName<Long>("sequenceNo");
    }
    public static class _DQualityLabelPrintQueueNames extends PropertyName<DQualityLabelPrintQueue> {

        /**
         * インスタンスを構築します。
         */
        public _DQualityLabelPrintQueueNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DQualityLabelPrintQueueNames(final String name) {
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
        public _DQualityLabelPrintQueueNames(final PropertyName<?> parent, final String name) {
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
         * barcodeNoのプロパティ名を返します。
         *
         * @return barcodeNoのプロパティ名
         */
        public PropertyName<String> barcodeNo() {
            return new PropertyName<String>(this, "barcodeNo");
        }
        /**
         * printerIdのプロパティ名を返します。
         *
         * @return printerIdのプロパティ名
         */
        public PropertyName<String> printerId() {
            return new PropertyName<String>(this, "printerId");
        }
        /**
         * printTypeのプロパティ名を返します。
         *
         * @return printTypeのプロパティ名
         */
        public PropertyName<String> serialNo() {
            return new PropertyName<String>(this, "printType");
        }
        /**
         * qcBarcodeNoのプロパティ名を返します。
         *
         * @return qcBarcodeNoのプロパティ名
         */
        public PropertyName<String> qcBarcodeNo() {
            return new PropertyName<String>(this, "qcBarcodeNo");
        }
        /**
         * sequenceNoのプロパティ名を返します。
         *
         * @return sequenceNoのプロパティ名
         */
        public PropertyName<Long> sequenceNo() {
            return new PropertyName<Long>(this, "sequenceNo");
        }
    }
}
