package jp.co.tmeic.mespd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.dto.handy.MaterialDto;

import org.seasar.extension.jdbc.JdbcManager;

/**
 * MaterialPlanのサービスクラスです。
 *
 */
public class MaterialPlanService {

	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * @param productPlanNo
	 * @param processComponentNo
	 * @param serialNo
	 * @param revision
	 * @return
	 */
	public List<MaterialDto> findBySerialNo(
			String productPlanNo, Integer processComponentNo, String serialNo, Integer revision) {

		Map<String, Object> param = new HashMap<>();
		param.put("productPlanNo", productPlanNo);
		param.put("processComponentNo", processComponentNo);
		param.put("serialNo", serialNo);
		param.put("revision", revision);

		return jdbcManager.selectBySqlFile(
				MaterialDto.class,
				"jp/co/tmeic/mespd/service/MaterialPlanService_findBySerialNo.sql",
				param)
				.getResultList();
	}
}
