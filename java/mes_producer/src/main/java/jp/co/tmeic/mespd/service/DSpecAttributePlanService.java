package jp.co.tmeic.mespd.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.DSpecAttributePlan;

import static jp.co.tmeic.mespd.entity.DSpecAttributePlanNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link DSpecAttributePlan}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class DSpecAttributePlanService extends AbstractService<DSpecAttributePlan> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param productPlanNo
     *            識別子
     * @param specAttributeId
     *            識別子
     * @return エンティティ
     */
    public DSpecAttributePlan findById(String productPlanNo, Integer specAttributeId) {
        return select().id(productPlanNo, specAttributeId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<DSpecAttributePlan> findAllOrderById() {
        return select().orderBy(asc(productPlanNo()), asc(specAttributeId())).getResultList();
    }
}