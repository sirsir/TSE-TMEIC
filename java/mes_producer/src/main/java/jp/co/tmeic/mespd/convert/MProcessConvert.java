package jp.co.tmeic.mespd.convert;

import java.util.ArrayList;
import java.util.List;

import jp.co.tmeic.mespd.dto.jqgrid.JqgridDto;
import jp.co.tmeic.mespd.dto.jqgrid.master.MMaterialComponentDto;
import jp.co.tmeic.mespd.dto.jqgrid.master.MProcessComponentDto;
import jp.co.tmeic.mespd.dto.jqgrid.master.MSpecProcessComponentDto;
import jp.co.tmeic.mespd.dto.jqgrid.master.MSpecProductComponentDto;
import jp.co.tmeic.mespd.entity.MProcessComponent;
import jp.co.tmeic.mespd.utils.SystemParameterUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringConversionUtil;

public final class MProcessConvert {

	private MProcessConvert() {

	}

	/**
	 * @param mProcessComponents
	 * @return
	 */
	public static List<MProcessComponentDto> convertMProcessComponentDto(List<MProcessComponent> mProcessComponents) {

		int processMaxSize = SystemParameterUtil.processMaxSize();
		int productSpecMaxSize = SystemParameterUtil.productSpecMaxSize();
		int processSpecMaxSize = SystemParameterUtil.processSpecMaxSize();
		int materialMaxSize = SystemParameterUtil.materialMaxSize();

		List<MProcessComponentDto> mProcessComponentDtos = new ArrayList<>();

		for (int i = 0; i < processMaxSize; i++) {

			MProcessComponentDto mProcessComponentDto = new MProcessComponentDto();

			mProcessComponentDto.id = StringConversionUtil.toString(i);
			mProcessComponentDto.crud = "R";

			MProcessComponent mProcessComponent = new MProcessComponent();

			if (CollectionUtils.size(mProcessComponents) > i) {
				mProcessComponent = mProcessComponents.get(i);
			}

			Beans.copy(mProcessComponent, mProcessComponentDto).execute();

			mProcessComponentDto.specProducts.addAll(
					createComponent(MSpecProductComponentDto.class, productSpecMaxSize, mProcessComponent.MSpecProductComponentList));

			mProcessComponentDto.specProcesses.addAll(
					createComponent(MSpecProcessComponentDto.class, processSpecMaxSize, mProcessComponent.MSpecProcessComponentList));

			mProcessComponentDto.materials.addAll(
					createComponent(MMaterialComponentDto.class, materialMaxSize, mProcessComponent.MMaterialComponentList));

			mProcessComponentDtos.add(mProcessComponentDto);
		}

		return mProcessComponentDtos;
	}

	/**
	 * 構成データを作成する。
	 *
	 * @param destClass
	 * @param maxSize
	 * @param src
	 * @return
	 */
	private static <T extends JqgridDto> List<T> createComponent(Class<T> destClass, int maxSize, List<?> src) {

		List<T> destList = new ArrayList<>();

		for (int i = 0; i < maxSize; i++) {

			@SuppressWarnings("unchecked")
			T newDest = (T) ClassUtil.newInstance(destClass);

			newDest.id = StringConversionUtil.toString(i);
			newDest.crud = "R";

			if (CollectionUtils.size(src) > i) {
				Beans.copy(src.get(i), newDest).execute();
			}

			destList.add(newDest);
		}

		return destList;
	}
}
