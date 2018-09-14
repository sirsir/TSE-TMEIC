package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.DSpecPlanNames.*;

/**
 * {@link DSpecPlan}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class DSpecPlanTest extends S2TestCase {

    private JdbcManager jdbcManager;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("s2jdbc.dicon");
    }

    /**
     * 識別子による取得をテストします。
     * 
     * @throws Exception
     */
    public void testFindById() throws Exception {
        jdbcManager.from(DSpecPlan.class).id("aaa", "aaa").getSingleResult();
    }

    /**
     * DSpecAttributePlanとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DSpecAttributePlan() throws Exception {
        jdbcManager.from(DSpecPlan.class).leftOuterJoin(DSpecAttributePlan()).id("aaa", "aaa").getSingleResult();
    }

    /**
     * DSpecProcessPlanListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DSpecProcessPlanList() throws Exception {
        jdbcManager.from(DSpecPlan.class).leftOuterJoin(DSpecProcessPlanList()).id("aaa", "aaa").getSingleResult();
    }

    /**
     * DSpecProductPlanListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_DSpecProductPlanList() throws Exception {
        jdbcManager.from(DSpecPlan.class).leftOuterJoin(DSpecProductPlanList()).id("aaa", "aaa").getSingleResult();
    }
}