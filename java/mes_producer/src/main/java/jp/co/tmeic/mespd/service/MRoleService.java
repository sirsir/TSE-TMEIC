package jp.co.tmeic.mespd.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MRole;

import static jp.co.tmeic.mespd.entity.MRoleNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MRole}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class MRoleService extends AbstractService<MRole> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param roleId
     *            識別子
     * @return エンティティ
     */
    public MRole findById(String roleId) {
        return select().id(roleId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MRole> findAllOrderById() {
        return select().orderBy(asc(roleId())).getResultList();
    }
}