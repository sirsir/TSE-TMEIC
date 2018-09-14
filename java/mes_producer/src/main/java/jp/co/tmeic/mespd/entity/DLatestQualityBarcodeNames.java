package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.MRoleNames._MRoleNames;

import org.seasar.extension.jdbc.name.PropertyName;

@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class DLatestQualityBarcodeNames {
	
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

    public static class _DBarcodeLabelManagementNames extends PropertyName<DBarcodeLabelManagement> {

        /**
         * インスタンスを構築します。
         */
        public _DBarcodeLabelManagementNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _DBarcodeLabelManagementNames(final String name) {
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
        public _DBarcodeLabelManagementNames(final PropertyName<?> parent, final String name) {
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
        
    }
}
