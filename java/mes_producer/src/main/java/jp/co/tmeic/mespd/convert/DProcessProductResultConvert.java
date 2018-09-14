package jp.co.tmeic.mespd.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.tmeic.mespd.dto.jqgrid.product.DProcessProductResultDto;
import jp.co.tmeic.mespd.entity.DProcessProductResult;
import jp.co.tmeic.mespd.entity.DProcessResult;
import jp.co.tmeic.mespd.utils.JSONUtil;
import net.arnx.jsonic.JSON;

import org.seasar.framework.beans.util.Beans;

public final class DProcessProductResultConvert {

	private DProcessProductResultConvert() {

	}

	/**
	 * JSON文字列から工程内製品単位実績Mapへ変換
	 *
	 * @param dProcessProductResultJson
	 * @return
	 */
	public static List<DProcessProductResultDto> toDProcessProductResultDto(String dProcessProductResultJson) {

		List<DProcessProductResultDto> dProcessProductResultDtos = new ArrayList<>();
		Map<Integer, Object> processProductResultsMap = JSONUtil.decodeToMapInt(dProcessProductResultJson);

		for (Integer index : processProductResultsMap.keySet()) {

			Map<String, Object> processProductResultMap = JSONUtil.decodeToMapString(JSON.encode(processProductResultsMap.get(index)));
			DProcessProductResultDto dProcessProductResultDto = copyProcessProductResultMapToDto(processProductResultMap);

			dProcessProductResultDtos.add(dProcessProductResultDto);
		}

		return dProcessProductResultDtos;
	}

	/**
	 * 工程内製品単位実績Mapから工程内製品単位実績Dtoへ変換
	 *
	 * @param processProductResultMap
	 * @return
	 */
	private static DProcessProductResultDto copyProcessProductResultMapToDto(Map<String, Object> processProductResultMap) {

		DProcessProductResultDto dProcessProductResultDto =
				Beans.createAndCopy(DProcessProductResultDto.class, processProductResultMap).execute();

		dProcessProductResultDto.dMaterialProductResults =
				DMaterialResultConvert.toDMaterialResultDto(processProductResultMap);

		dProcessProductResultDto.dSpecProductResults =
				DSpecResultConvert.toDSpecResultDto(processProductResultMap);

		return dProcessProductResultDto;
	}

	/**
	 * 工程内製品単位実績Dtoから工程内製品単位実績エンティティへ変換
	 *
	 * @param productPlanNo
	 * @param processComponentNo
	 * @param dProcessProductResultDto
	 * @return
	 */
	public static DProcessProductResult toDProcessProductResult(
			String productPlanNo,
			Integer processComponentNo,
			DProcessProductResultDto dProcessProductResultDto) {

		DProcessProductResult dProcessProductResult =
				Beans.createAndCopy(DProcessProductResult.class, dProcessProductResultDto).execute();

		dProcessProductResult.productPlanNo = productPlanNo;
		dProcessProductResult.processComponentNo = processComponentNo;

		return dProcessProductResult;
	}

	/**
	 * 工程内製品単位実績Dtoから工程内製品単位実績エンティティへ変換
	 *
	 * @param dProcessResult
	 * @param dProcessProductResultDto
	 * @return
	 */
	public static DProcessProductResult toDProcessProductResult(DProcessResult dProcessResult, DProcessProductResultDto dProcessProductResultDto) {
		return toDProcessProductResult(dProcessResult.productPlanNo, dProcessResult.processComponentNo, dProcessProductResultDto);
	}
}
