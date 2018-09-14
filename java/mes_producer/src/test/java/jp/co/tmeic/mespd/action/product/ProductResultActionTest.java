package jp.co.tmeic.mespd.action.product;

import java.lang.reflect.Method;

import jp.co.tmeic.mespd.action.product.ProductResultAction.ProductStatusInput;
import jp.co.tmeic.mespd.constant.ProductStatus;

import org.junit.Test;
import org.seasar.framework.util.tiger.ReflectionUtil;
import org.seasar.struts.unit.S2ActionTestCase;

public class ProductResultActionTest extends S2ActionTestCase {

	private ProductResultAction productResultAction;

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
	 * 製造状態遷移テスト
	 *
	 */
	@Test
	public void testIsChangeableStatusTx() {

		productResultAction = super.initAction(ProductResultAction.class);

		assertFalse("1-1", isChangeableStatus(ProductStatus.NONE, ProductStatusInput.NONE));
		assertTrue("1-2", isChangeableStatus(ProductStatus.NONE, ProductStatusInput.IN_PRODUCTION));
		assertFalse("1-3", isChangeableStatus(ProductStatus.NONE, ProductStatusInput.COMPLETE));
		assertFalse("1-4", isChangeableStatus(ProductStatus.NONE, ProductStatusInput.SUSPEND));
		assertFalse("1-5", isChangeableStatus(ProductStatus.NONE, ProductStatusInput.RESTART));
		assertFalse("1-6", isChangeableStatus(ProductStatus.NONE, ProductStatusInput.STOP));

		assertFalse("2-1", isChangeableStatus(ProductStatus.IN_PRODUCTION, ProductStatusInput.NONE));
		assertFalse("2-2", isChangeableStatus(ProductStatus.IN_PRODUCTION, ProductStatusInput.IN_PRODUCTION));
		assertTrue("2-3", isChangeableStatus(ProductStatus.IN_PRODUCTION, ProductStatusInput.COMPLETE));
		assertTrue("2-4", isChangeableStatus(ProductStatus.IN_PRODUCTION, ProductStatusInput.SUSPEND));
		assertFalse("2-5", isChangeableStatus(ProductStatus.IN_PRODUCTION, ProductStatusInput.RESTART));
		assertTrue("2-6", isChangeableStatus(ProductStatus.IN_PRODUCTION, ProductStatusInput.STOP));

		assertFalse("3-1", isChangeableStatus(ProductStatus.COMPLETE, ProductStatusInput.NONE));
		assertFalse("3-2", isChangeableStatus(ProductStatus.COMPLETE, ProductStatusInput.IN_PRODUCTION));
		assertFalse("3-3", isChangeableStatus(ProductStatus.COMPLETE, ProductStatusInput.COMPLETE));
		assertFalse("3-4", isChangeableStatus(ProductStatus.COMPLETE, ProductStatusInput.SUSPEND));
		assertFalse("3-5", isChangeableStatus(ProductStatus.COMPLETE, ProductStatusInput.RESTART));
		assertFalse("3-6", isChangeableStatus(ProductStatus.COMPLETE, ProductStatusInput.STOP));

		assertFalse("4-1", isChangeableStatus(ProductStatus.SUSPEND, ProductStatusInput.NONE));
		assertFalse("4-2", isChangeableStatus(ProductStatus.SUSPEND, ProductStatusInput.IN_PRODUCTION));
		assertTrue("4-3", isChangeableStatus(ProductStatus.SUSPEND, ProductStatusInput.COMPLETE));
		assertFalse("4-4", isChangeableStatus(ProductStatus.SUSPEND, ProductStatusInput.SUSPEND));
		assertTrue("4-5", isChangeableStatus(ProductStatus.SUSPEND, ProductStatusInput.RESTART));
		assertTrue("4-6", isChangeableStatus(ProductStatus.SUSPEND, ProductStatusInput.STOP));

		assertFalse("5-1", isChangeableStatus(ProductStatus.STOP, ProductStatusInput.NONE));
		assertFalse("5-2", isChangeableStatus(ProductStatus.STOP, ProductStatusInput.IN_PRODUCTION));
		assertFalse("5-3", isChangeableStatus(ProductStatus.STOP, ProductStatusInput.COMPLETE));
		assertFalse("5-4", isChangeableStatus(ProductStatus.STOP, ProductStatusInput.SUSPEND));
		assertFalse("5-5", isChangeableStatus(ProductStatus.STOP, ProductStatusInput.RESTART));
		assertFalse("5-6", isChangeableStatus(ProductStatus.STOP, ProductStatusInput.STOP));
	}

	private boolean isChangeableStatus(int currentStatus, ProductStatusInput updateStatus) {

		Method method =
				ReflectionUtil.getDeclaredMethod(ProductResultAction.class, "isChangeableStatus", int.class, ProductStatusInput.class);

		method.setAccessible(true);

		return (boolean) ReflectionUtil.invoke(method, productResultAction, currentStatus, updateStatus);
	}

}
