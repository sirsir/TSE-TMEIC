package jp.co.tmeic.mespd.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MRoleAuthority;

import static jp.co.tmeic.mespd.entity.MRoleAuthorityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MRoleAuthority}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class MRoleAuthorityService extends AbstractService<MRoleAuthority> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param roleId
     *            識別子
     * @param authorityId
     *            識別子
     * @return エンティティ
     */
    public MRoleAuthority findById(String roleId, String authorityId) {
        return select().id(roleId, authorityId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MRoleAuthority> findAllOrderById() {
        return select().orderBy(asc(roleId()), asc(authorityId())).getResultList();
    }
}