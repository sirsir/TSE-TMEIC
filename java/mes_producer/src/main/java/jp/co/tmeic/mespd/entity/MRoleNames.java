package jp.co.tmeic.mespd.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MRoleAuthorityNames._MRoleAuthorityNames;
import jp.co.tmeic.mespd.entity.MUsersRoleNames._MUsersRoleNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MRole}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"})
public class MRoleNames {

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
     * roleIdのプロパティ名を返します。
     * 
     * @return roleIdのプロパティ名
     */
    public static PropertyName<String> roleId() {
        return new PropertyName<String>("roleId");
    }

    /**
     * roleNameのプロパティ名を返します。
     * 
     * @return roleNameのプロパティ名
     */
    public static PropertyName<String> roleName() {
        return new PropertyName<String>("roleName");
    }

    /**
     * MRoleAuthorityListのプロパティ名を返します。
     * 
     * @return MRoleAuthorityListのプロパティ名
     */
    public static _MRoleAuthorityNames MRoleAuthorityList() {
        return new _MRoleAuthorityNames("MRoleAuthorityList");
    }

    /**
     * MUsersRoleListのプロパティ名を返します。
     * 
     * @return MUsersRoleListのプロパティ名
     */
    public static _MUsersRoleNames MUsersRoleList() {
        return new _MUsersRoleNames("MUsersRoleList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MRoleNames extends PropertyName<MRole> {

        /**
         * インスタンスを構築します。
         */
        public _MRoleNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MRoleNames(final String name) {
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
        public _MRoleNames(final PropertyName<?> parent, final String name) {
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
         * roleIdのプロパティ名を返します。
         *
         * @return roleIdのプロパティ名
         */
        public PropertyName<String> roleId() {
            return new PropertyName<String>(this, "roleId");
        }

        /**
         * roleNameのプロパティ名を返します。
         *
         * @return roleNameのプロパティ名
         */
        public PropertyName<String> roleName() {
            return new PropertyName<String>(this, "roleName");
        }

        /**
         * MRoleAuthorityListのプロパティ名を返します。
         * 
         * @return MRoleAuthorityListのプロパティ名
         */
        public _MRoleAuthorityNames MRoleAuthorityList() {
            return new _MRoleAuthorityNames(this, "MRoleAuthorityList");
        }

        /**
         * MUsersRoleListのプロパティ名を返します。
         * 
         * @return MUsersRoleListのプロパティ名
         */
        public _MUsersRoleNames MUsersRoleList() {
            return new _MUsersRoleNames(this, "MUsersRoleList");
        }
    }
}