package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.MSpecNames.*;

/**
 * {@link MSpec}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class MSpecTest extends S2TestCase {

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
        jdbcManager.from(MSpec.class).id("aaa").getSingleResult();
    }

    /**
     * MSpecAttributeとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MSpecAttribute() throws Exception {
        jdbcManager.from(MSpec.class).leftOuterJoin(MSpecAttribute()).id("aaa").getSingleResult();
    }

    /**
     * MSpecProcessComponentListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MSpecProcessComponentList() throws Exception {
        jdbcManager.from(MSpec.class).leftOuterJoin(MSpecProcessComponentList()).id("aaa").getSingleResult();
    }

    /**
     * MSpecProductComponentListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MSpecProductComponentList() throws Exception {
        jdbcManager.from(MSpec.class).leftOuterJoin(MSpecProductComponentList()).id("aaa").getSingleResult();
    }
}