package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.MProcessComponentNames.*;

/**
 * {@link MProcessComponent}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class MProcessComponentTest extends S2TestCase {

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
        jdbcManager.from(MProcessComponent.class).id("aaa", 1).getSingleResult();
    }

    /**
     * MBeforeProcessListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MBeforeProcessList() throws Exception {
        jdbcManager.from(MProcessComponent.class).leftOuterJoin(MBeforeProcessList()).id("aaa", 1).getSingleResult();
    }

    /**
     * MMaterialComponentListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MMaterialComponentList() throws Exception {
        jdbcManager.from(MProcessComponent.class).leftOuterJoin(MMaterialComponentList()).id("aaa", 1).getSingleResult();
    }

    /**
     * MProductとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MProduct() throws Exception {
        jdbcManager.from(MProcessComponent.class).leftOuterJoin(MProduct()).id("aaa", 1).getSingleResult();
    }

    /**
     * MSpecProcessComponentListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MSpecProcessComponentList() throws Exception {
        jdbcManager.from(MProcessComponent.class).leftOuterJoin(MSpecProcessComponentList()).id("aaa", 1).getSingleResult();
    }

    /**
     * MSpecProductComponentListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MSpecProductComponentList() throws Exception {
        jdbcManager.from(MProcessComponent.class).leftOuterJoin(MSpecProductComponentList()).id("aaa", 1).getSingleResult();
    }
}