package jp.co.tmeic.mespd.service;

import java.util.List;

import javax.annotation.Generated;

import jp.co.tmeic.mespd.dto.jqgrid.master.MUsersRoleDto;

/**
 * {@link MUsersService}のテストクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceTestModelFactoryImpl" })
public class MUsersServiceTest extends AbstructServiceTest {

	private MUsersService mUsersService;

	/**
	 * 事前処理をします。
	 *
	 * @throws Exception
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("app.dicon");
	}

	/**
	 * {@link #mUsersService}が利用可能であることをテストします。
	 *
	 * @throws Exception
	 */
	public void testAvailable() throws Exception {
		assertNotNull(mUsersService);
	}

	public void testFindVisibleOrderByIdTx() {

		this.allDelete();

		// DBにテストデータを追加
		//readXlsAllReplaceDb("MUsersServiceTest_testFindVisibleOrderByIdTx.xls");

		List<MUsersRoleDto> mUsersRoleDtos = mUsersService.findVisibleOrderById();

		assertEquals(1, mUsersRoleDtos.size());
		assertEquals("0001", mUsersRoleDtos.get(0).userId);
	}

}