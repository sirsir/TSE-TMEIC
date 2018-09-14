package jp.co.tmeic.mespd.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.tmeic.mespd.dto.jqgrid.product.DMaterialResultDto;
import jp.co.tmeic.mespd.entity.DMaterialProcessResult;
import jp.co.tmeic.mespd.entity.DMaterialProductResult;
import jp.co.tmeic.mespd.entity.DProcessProductResult;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;

import org.apache.commons.lang3.StringUtils;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.StringUtil;

public final class DMaterialResultConvert {

	private DMaterialResultConvert() {

	}

	/**
	 * 部材実績Mapから部材実績Dtoへ変換
	 *
	 * @param map
	 * @return
	 */
	public static List<DMaterialResultDto> toDMaterialResultDto(Map<String, Object> map) {

		List<DMaterialResultDto> materialResults = new ArrayList<>();

		for (int i = 1; i <= SystemParameterUtil.materialMaxSize(); i++) {

			if (!map.containsKey("materialComponentNo" + i)) {
				continue;
			}

			if (map.get("materialComponentNo" + i) == null) {
				continue;
			}

			String materialComponentNo = String.valueOf(map.get("materialComponentNo" + i));

			if (StringUtils.isBlank(materialComponentNo)) {
				continue;
			}

			DMaterialResultDto dMaterialResultDto = new DMaterialResultDto();
			dMaterialResultDto.materialComponentNo = materialComponentNo;

			String materialQty = "0";
			if ((map.get("materialQty" + i) != null) && (StringUtil.isNotBlank(String.valueOf(map.get("materialQty" + i))))) {
				materialQty = String.valueOf(map.get("materialQty" + i));
			}

			dMaterialResultDto.materialQty = materialQty;

			materialResults.add(dMaterialResultDto);
		}

		return materialResults;
	}

	/**
	 * 部材実績Dtoから工程単位部材実績エンティティへ変換
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 *            識別子
	 * @param materialResult
	 *            Dto
	 * @return
	 */
	public static DMaterialProcessResult toDMaterialProcessResult(
			String productPlanNo,
			Integer processComponentNo,
			DMaterialResultDto materialResult) {

		DMaterialProcessResult dMaterialProcessResult = Beans.createAndCopy(DMaterialProcessResult.class, materialResult).execute();

		dMaterialProcessResult.productPlanNo = productPlanNo;
		dMaterialProcessResult.processComponentNo = processComponentNo;

		return dMaterialProcessResult;
	}

	/**
	 * 部材実績Dtoから工程単位部材実績エンティティへ変換
	 *
	 * @param dProcessResult
	 * @param materialResult
	 * @return
	 */
	public static DMaterialProcessResult toDMaterialProcessResult(DProcessResult dProcessResult, DMaterialResultDto materialResult) {

		return toDMaterialProcessResult(
				dProcessResult.productPlanNo,
				dProcessResult.processComponentNo,
				materialResult);
	}

	/**
	 * 部材実績Dtoから工程単位部材実績エンティティへ変換
	 *
	 * @param dProcessResult
	 * @param dMaterialResultDtos
	 * @return
	 */
	public static List<DMaterialProcessResult> toDMaterialProcessResult(DProcessResult dProcessResult, List<DMaterialResultDto> dMaterialResultDtos) {

		List<DMaterialProcessResult> dMaterialProcessResults = new ArrayList<>();

		for (DMaterialResultDto dMaterialResultDto : dMaterialResultDtos) {

			DMaterialProcessResult dMaterialProcessResult = toDMaterialProcessResult(dProcessResult, dMaterialResultDto);
			dMaterialProcessResults.add(dMaterialProcessResult);
		}

		return dMaterialProcessResults;
	}

	/**
	 * 部材実績Dtoから製品単位部材実績エンティティへ変換
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @param serialNo
	 * @param revision
	 *            識別子
	 * @param dMaterialResultDto
	 *            Dto
	 * @return
	 */
	public static DMaterialProductResult toDMaterialProductResult(
			String productPlanNo,
			Integer processComponentNo,
			String serialNo,
			Integer revision,
			DMaterialResultDto dMaterialResultDto) {

		DMaterialProductResult dMaterialProductResult = Beans.createAndCopy(DMaterialProductResult.class, dMaterialResultDto).execute();

		dMaterialProductResult.productPlanNo = productPlanNo;
		dMaterialProductResult.processComponentNo = processComponentNo;
		dMaterialProductResult.serialNo = serialNo;
		dMaterialProductResult.revision = revision;

		return dMaterialProductResult;
	}

	/**
	 * 部材実績Dtoから製品単位部材実績エンティティへ変換
	 *
	 * @param dProcessProductResult
	 * @param dMaterialResultDto
	 * @return
	 */
	public static DMaterialProductResult toDMaterialProductResult(DProcessProductResult dProcessProductResult, DMaterialResultDto dMaterialResultDto) {

		return toDMaterialProductResult(
				dProcessProductResult.productPlanNo,
				dProcessProductResult.processComponentNo,
				dProcessProductResult.serialNo,
				dProcessProductResult.revision,
				dMaterialResultDto);
	}

	/**
	 * 部材実績Dtoから製品単位部材実績エンティティへ変換
	 *
	 * @param dProcessProductResult
	 * @param dMaterialResultDtos
	 * @return
	 */
	public static List<DMaterialProductResult> toDMaterialProductResult(DProcessProductResult dProcessProductResult, List<DMaterialResultDto> dMaterialResultDtos) {

		List<DMaterialProductResult> dMaterialProductResults = new ArrayList<>();

		for (DMaterialResultDto dMaterialResultDto : dMaterialResultDtos) {

			DMaterialProductResult dMaterialProductResult = toDMaterialProductResult(dProcessProductResult, dMaterialResultDto);
			dMaterialProductResults.add(dMaterialProductResult);
		}

		return dMaterialProductResults;
	}
}
