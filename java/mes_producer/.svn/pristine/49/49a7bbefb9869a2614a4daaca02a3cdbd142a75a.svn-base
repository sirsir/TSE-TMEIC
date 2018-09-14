package jp.co.tmeic.mespd.service;

import static jp.co.tmeic.mespd.entity.DSpecProcessPlanNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.entity.DSpecProcessPlan;

/**
 * {@link DSpecProcessPlan}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"})
public class DSpecProcessPlanService extends AbstractService<DSpecProcessPlan> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param productPlanNo
     *            識別子
     * @param processComponentNo
     *            識別子
     * @param specComponentNo
     *            識別子
     * @return エンティティ
     */
    public DSpecProcessPlan findById(String productPlanNo, Integer processComponentNo, Integer specComponentNo) {
        return select().id(productPlanNo, processComponentNo, specComponentNo).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<DSpecProcessPlan> findAllOrderById() {
        return select().orderBy(asc(productPlanNo()), asc(processComponentNo()), asc(specComponentNo())).getResultList();
    }
}