package jp.co.tmeic.mespd.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.tmeic.mespd.dto.jqgrid.product.DSpecPlanDto;
import jp.co.tmeic.mespd.dto.jqgrid.product.DSpecResultDto;
import jp.co.tmeic.mespd.entity.DProcessProductResult;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.entity.DSpecProcessResult;
import jp.co.tmeic.mespd.entity.DSpecProductResult;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;

import org.apache.commons.lang3.StringUtils;
import org.seasar.framework.beans.util.Beans;

public final class DSpecResultConvert {

	private DSpecResultConvert() {

	}

	/**
	 * 仕様実績Mapから仕様実績Dtoへ変換
	 *
	 * @param map
	 * @return
	 */
	public static List<DSpecResultDto> toDSpecResultDto(Map<String, Object> map) {

		List<DSpecResultDto> dSpecResultDtos = new ArrayList<>();

		for (int i = 1; i <= SystemParameterUtil.processSpecMaxSize(); i++) {

			if (!map.containsKey("specComponentNo" + i)) {
				continue;
			}

			if (map.get("specComponentNo" + i) == null) {
				continue;
			}

			String specComponentNo = String.valueOf(map.get("specComponentNo" + i));
			if (StringUtils.isBlank(specComponentNo)) {
				continue;
			}

			DSpecResultDto dSpecResultDto = new DSpecResultDto();
			dSpecResultDto.specComponentNo = specComponentNo;

			dSpecResultDto.dSpecPlan = new DSpecPlanDto();
			dSpecResultDto.dSpecPlan.specId = String.valueOf(map.get("specId" + i));
			dSpecResultDto.dSpecPlan.specName = String.valueOf(map.get("specName" + i));
			dSpecResultDto.dSpecPlan.specAttributeId = String.valueOf(map.get("specAttributeId" + i));

			String inputValue = "";
			if (map.get("inputValue" + i) != null) {
				inputValue = String.valueOf(map.get("inputValue" + i));
			}
			dSpecResultDto.inputValue = inputValue;

			dSpecResultDtos.add(dSpecResultDto);
		}

		return dSpecResultDtos;
	}

	/**
	 * 仕様実績Dtoから工程単位仕様実績エンティティへ変換
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 *            識別子
	 * @param dMaterialResultDto
	 *            Dto
	 * @return
	 */
	public static DSpecProcessResult toDSpecProcessResult(
			String productPlanNo,
			Integer processComponentNo,
			DSpecResultDto dSpecResultDto) {

		DSpecProcessResult dSpecProcessResult = Beans.createAndCopy(DSpecProcessResult.class, dSpecResultDto).execute();

		dSpecProcessResult.productPlanNo = productPlanNo;
		dSpecProcessResult.processComponentNo = processComponentNo;

		return dSpecProcessResult;
	}

	/**
	 * 仕様実績Dtoから工程単位仕様実績エンティティへ変換
	 *
	 * @param dProcessResult
	 * @param dSpecResultDto
	 * @return
	 */
	public static DSpecProcessResult toDSpecProcessResult(DProcessResult dProcessResult, DSpecResultDto dSpecResultDto) {

		return toDSpecProcessResult(
				dProcessResult.productPlanNo,
				dProcessResult.processComponentNo,
				dSpecResultDto);
	}

	/**
	 * 仕様実績Dtoから工程単位仕様実績エンティティへ変換
	 *
	 * @param dProcessResult
	 * @param dSpecResultDtos
	 * @return
	 */
	public static List<DSpecProcessResult> toDSpecProcessResult(DProcessResult dProcessResult, List<DSpecResultDto> dSpecResultDtos) {

		List<DSpecProcessResult> dSpecProcessResults = new ArrayList<>();

		for (DSpecResultDto dSpecResultDto : dSpecResultDtos) {

			DSpecProcessResult dSpecProcessResult = toDSpecProcessResult(dProcessResult, dSpecResultDto);
			dSpecProcessResults.add(dSpecProcessResult);
		}

		return dSpecProcessResults;
	}

	/**
	 * 仕様実績Dtoから製品単位仕様実績エンティティへ変換
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
	public static DSpecProductResult toDSpecProductResult(
			String productPlanNo,
			Integer processComponentNo,
			String serialNo,
			Integer revision,
			DSpecResultDto dSpecResultDto) {

		DSpecProductResult dSpecProductResult = Beans.createAndCopy(DSpecProductResult.class, dSpecResultDto).execute();

		dSpecProductResult.productPlanNo = productPlanNo;
		dSpecProductResult.processComponentNo = processComponentNo;
		dSpecProductResult.serialNo = serialNo;
		dSpecProductResult.revision = revision;

		return dSpecProductResult;
	}

	/**
	 * 仕様実績Dtoから製品単位仕様実績エンティティへ変換
	 *
	 * @param dProcessProductResult
	 * @param dSpecResultDto
	 *            Dto
	 * @return
	 */
	public static DSpecProductResult toDSpecProductResult(DProcessProductResult dProcessProductResult, DSpecResultDto dSpecResultDto) {

		return toDSpecProductResult(
				dProcessProductResult.productPlanNo,
				dProcessProductResult.processComponentNo,
				dProcessProductResult.serialNo,
				dProcessProductResult.revision,
				dSpecResultDto);
	}

	/**
	 * 仕様実績Dtoから製品単位仕様実績エンティティへ変換
	 *
	 * @param dProcessProductResult
	 * @param dSpecResultDtos
	 *            Dto
	 * @return
	 */
	public static List<DSpecProductResult> toDSpecProductResult(DProcessProductResult dProcessProductResult, List<DSpecResultDto> dSpecResultDtos) {

		List<DSpecProductResult> dSpecProductResults = new ArrayList<>();

		for (DSpecResultDto dSpecResultDto : dSpecResultDtos) {

			DSpecProductResult dSpecProductResult = toDSpecProductResult(dProcessProductResult, dSpecResultDto);
			dSpecProductResults.add(dSpecProductResult);
		}

		return dSpecProductResults;
	}
}
