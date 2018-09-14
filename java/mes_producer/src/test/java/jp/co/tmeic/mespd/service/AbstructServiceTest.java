package jp.co.tmeic.mespd.service;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

public abstract class AbstructServiceTest extends S2TestCase {

	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * データベースの全データをDELETEします。
	 *
	 */
	public void allDelete() {

		allMasterDelete();
		allDataDelete();

	}

	private void allMasterDelete() {

		jdbcManager.updateBySql("DELETE FROM m_before_process").execute();
		jdbcManager.updateBySql("DELETE FROM m_material_component").execute();
		jdbcManager.updateBySql("DELETE FROM m_spec_product_component").execute();
		jdbcManager.updateBySql("DELETE FROM m_spec_process_component").execute();
		jdbcManager.updateBySql("DELETE FROM m_spec").execute();
		jdbcManager.updateBySql("DELETE FROM m_material").execute();
		jdbcManager.updateBySql("DELETE FROM m_spec_attribute").execute();
		jdbcManager.updateBySql("DELETE FROM m_process_component").execute();
		jdbcManager.updateBySql("DELETE FROM m_product").execute();
		jdbcManager.updateBySql("DELETE FROM m_work_calendar_break").execute();
		jdbcManager.updateBySql("DELETE FROM m_work_calendar").execute();
		jdbcManager.updateBySql("DELETE FROM m_users_role").execute();
		jdbcManager.updateBySql("DELETE FROM m_role_authority").execute();
		jdbcManager.updateBySql("DELETE FROM m_authority").execute();
		jdbcManager.updateBySql("DELETE FROM m_role").execute();
		jdbcManager.updateBySql("DELETE FROM m_users").execute();

	}

	private void allDataDelete() {

		jdbcManager.updateBySql("DELETE FROM d_material_process_result").execute();
		jdbcManager.updateBySql("DELETE FROM d_material_product_result").execute();
		jdbcManager.updateBySql("DELETE FROM d_spec_product_result").execute();
		jdbcManager.updateBySql("DELETE FROM d_process_product_result").execute();
		jdbcManager.updateBySql("DELETE FROM d_spec_process_result").execute();
		jdbcManager.updateBySql("DELETE FROM d_process_result").execute();
		jdbcManager.updateBySql("DELETE FROM d_product_result").execute();
		jdbcManager.updateBySql("DELETE FROM d_spec_process_plan").execute();
		jdbcManager.updateBySql("DELETE FROM d_spec_product_plan").execute();
		jdbcManager.updateBySql("DELETE FROM d_material_plan").execute();
		jdbcManager.updateBySql("DELETE FROM d_spec_plan").execute();
		jdbcManager.updateBySql("DELETE FROM d_spec_attribute_plan").execute();
		jdbcManager.updateBySql("DELETE FROM d_serial_no_count").execute();
		jdbcManager.updateBySql("DELETE FROM d_serial_no").execute();
		jdbcManager.updateBySql("DELETE FROM d_process_plan").execute();
		jdbcManager.updateBySql("DELETE FROM d_product_plan").execute();

	}

}
