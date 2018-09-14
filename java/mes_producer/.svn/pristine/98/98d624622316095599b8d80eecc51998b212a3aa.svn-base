package jp.co.tmeic.mespd.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static jp.co.tmeic.mespd.entity.MUsersRoleNames.*;

/**
 * {@link MUsersRole}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"})
public class MUsersRoleTest extends S2TestCase {

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
        jdbcManager.from(MUsersRole.class).id("aaa", "aaa").getSingleResult();
    }

    /**
     * MRoleとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MRole() throws Exception {
        jdbcManager.from(MUsersRole.class).leftOuterJoin(MRole()).id("aaa", "aaa").getSingleResult();
    }

    /**
     * MUsersとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_MUsers() throws Exception {
        jdbcManager.from(MUsersRole.class).leftOuterJoin(MUsers()).id("aaa", "aaa").getSingleResult();
    }
}