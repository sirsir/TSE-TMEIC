package jp.co.tmeic.mespd.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.MAuthority;

import static jp.co.tmeic.mespd.entity.MAuthorityNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MAuthority}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class MAuthorityService extends AbstractService<MAuthority> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param authorityId
     *            識別子
     * @return エンティティ
     */
    public MAuthority findById(String authorityId) {
        return select().id(authorityId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MAuthority> findAllOrderById() {
        return select().orderBy(asc(authorityId())).getResultList();
    }
}