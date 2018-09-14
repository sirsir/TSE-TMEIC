package jp.co.tmeic.mespd.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.tmeic.mespd.entity.DSpecPlan;

import static jp.co.tmeic.mespd.entity.DSpecPlanNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link DSpecPlan}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class DSpecPlanService extends AbstractService<DSpecPlan> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param productPlanNo
     *            識別子
     * @param specId
     *            識別子
     * @return エンティティ
     */
    public DSpecPlan findById(String productPlanNo, String specId) {
        return select().id(productPlanNo, specId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<DSpecPlan> findAllOrderById() {
        return select().orderBy(asc(productPlanNo()), asc(specId())).getResultList();
    }
}