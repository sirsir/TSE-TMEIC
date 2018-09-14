package jp.co.tmeic.mespd.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.framework.util.tiger.ReflectionUtil;

/**
 * サービスの抽象クラスです。
 *
 * @param <ENTITY>
 *            エンティティの型
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.AbstServiceModelFactoryImpl" })
public abstract class AbstractService<ENTITY> extends S2AbstractService<ENTITY> {

	public int insert(ENTITY entity) {

		Field createDateField = ReflectionUtil.getField(entity.getClass(), "createDate");
		ReflectionUtil.setValue(createDateField, entity, new Timestamp(System.currentTimeMillis()));

		Field updateDateField = ReflectionUtil.getField(entity.getClass(), "updateDate");
		ReflectionUtil.setValue(updateDateField, entity, new Timestamp(System.currentTimeMillis()));

		return jdbcManager.insert(entity).excludesNull().execute();
	}

	public int update(ENTITY entity) {

		Field updateDateField = ReflectionUtil.getField(entity.getClass(), "updateDate");
		ReflectionUtil.setValue(updateDateField, entity, new Timestamp(System.currentTimeMillis()));

		return jdbcManager.update(entity).excludesNull().execute();
	}

	public boolean isExist(Object... paramVarArgs) {
		return (select().id(paramVarArgs).getSingleResult() != null);
	}

	public int max(String sql, Object... params) {

		Integer max = jdbcManager.selectBySql(Integer.class, sql, params).getSingleResult();

		if (max == null) {
			return 0;
		}

		return max;
	}
}