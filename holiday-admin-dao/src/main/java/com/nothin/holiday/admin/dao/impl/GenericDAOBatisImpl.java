/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: GenericDAOBatisImpl.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.nothin.holiday.admin.dao.api.IGenericDAO;
import com.nothin.holiday.admin.dao.domain.BaseDomain;
import com.nothin.holiday.admin.dao.utils.GrapeDAOUtils;

/**
 * 接口 GenericDAO batis 抽象实现基类
 * 
 * @param E
 *            实体类类型
 * @param T
 *            GenericDAOBatisImpl的实现子类型
 * 
 * @author 李牧牧
 * @version 1.0
 */
public abstract class GenericDAOBatisImpl<E extends BaseDomain, T extends GenericDAOBatisImpl<E, T>>
		extends SqlSessionDaoSupport implements IGenericDAO<E> {

	private static final Logger logger = LoggerFactory
			.getLogger(GenericDAOBatisImpl.class);

	protected static final String TABLE_NAME_PREFIX = "t_";
	private static final String NAME_SPACE_SUFFIX = "Mapper";

	protected static final String VAR_TABLE_NAME = "table_name";

	protected static final int SUCCESS = 1;
	protected static final int FAIL = 0;

	private static final int PAGESIZE_MAX = 500;

	private static final Pattern P_DAOIMPL_NAME = Pattern
			.compile("^(.+)DAOImpl$");

	@SuppressWarnings("rawtypes")
	private static final Map<Class, String> NAME_SPACE_MAP = new ConcurrentHashMap<>();

	@SuppressWarnings("rawtypes")
	private static final Map<Class, String> TABLE_NAME_MAP = new ConcurrentHashMap<>();

	protected Class<E> entityClass;
	protected Class<T> daoImplClass;

	private final ObjectFactory objectFactory = new DefaultObjectFactory();

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@SuppressWarnings("unchecked")
	public GenericDAOBatisImpl() {
		Type[] types = ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments();
		this.entityClass = (Class<E>) types[0];
		this.daoImplClass = (Class<T>) types[1];
	}

	@PostConstruct
	public void init() {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public final List<E> selectAll() {
		return selectList("selectAll");
	}

	@Override
	public final E findById(Long id) {
		Map<String, Object> params = new HashMap<>();
		params.put(VAR_TABLE_NAME, getTableName());
		params.put("id", id);
		return genericSelectOne("findById", params);
	}

	@Override
	public final E findByCode(String code) {
		if (code == null || "".equals(code)) {
			throw new IllegalArgumentException(
					"findByCode: argument code is null or empty!!");
		}
		Map<String, Object> params = new HashMap<>();
		params.put(VAR_TABLE_NAME, getTableName());
		params.put("code", code);
		return genericSelectOne("findByCode", params);
	}

	@Override
	public final int insert(E entity) {
		if (entity == null) {
			return FAIL;
		}

		try {
			List<E> entities = new ArrayList<>();
			entities.add(entity);

			insert(entities);

			return SUCCESS;

		} catch (Exception ex) {
			logger.error("insert failed with entity: "
					+ entity.getClass().getName(), ex);
		}

		return FAIL;
	}

	@Override
	public void insert(List<E> entities) {
		genericInsert(GenericDAOBatisImpl.class, entities);
	}

	protected void genericInsert(List<? extends BaseDomain> entities) {
		genericInsert(GenericDAOBatisImpl.class, entities);
	}

	@SuppressWarnings("rawtypes")
	protected void genericInsert(
			Class<? extends GenericDAOBatisImpl> daoImplclass,
			List<? extends BaseDomain> entities) {

		List<List<?>> valuesList = new ArrayList<>();

		Map<String, Object> params = new HashMap<>();
		params.put(VAR_TABLE_NAME, getTableName());

		try {

			for (int i = 0; i < entities.size(); i++) {
				Map<String, List<?>> mapFieldsAndValues = GrapeDAOUtils
						.describe(entities.get(i));
				if (i == 0) {
					params.put("fields", mapFieldsAndValues.get("fields"));
				}

				valuesList.add((List<?>) mapFieldsAndValues.get("values"));
			}

			params.put("values", valuesList);

			getSqlSession().insert(getNameSpace(daoImplclass, "batchInsert"),
					params);

		} catch (Exception ex) {
			logger.error("insert entities failed with exception: "
					+ entities.get(0).getClass().getName(), ex);

			throw new RuntimeException(ex);
		}
	}

	protected int insert(String operate, E e) {
		return getSqlSession().insert(getNameSpace(operate), e);
	}

	protected int insert(String operate, Object param) {
		return getSqlSession().insert(getNameSpace(operate), param);
	}

	@Override
	public int update(E entity) {
		return getSqlSession().update(getNameSpace("update"), entity);
	}

	protected int update(String operate, E entity) {
		return getSqlSession().update(getNameSpace(operate), entity);
	}

	@Override
	public void update(List<E> entities) {
		if (entities != null && entities.size() > 0) {
			getSqlSession().update(getNameSpace("batchUpdate"), entities);
		}
	}

	protected void update(String operate, List<E> entities) {
		if (entities != null && entities.size() > 0) {
			getSqlSession().update(getNameSpace(operate), entities);
		}
	}

	protected void update(Map<String, Object> map, String whereCondition) {
		if (map != null) {
			List<String> fields = new ArrayList<String>();
			List<Object> values = new ArrayList<Object>();
			for (String key : map.keySet()) {
				fields.add(key);
				values.add(map.get(key));
			}
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put(VAR_TABLE_NAME, getTableName());
			parameterMap.put("fields", fields);
			parameterMap.put("values", values);
			parameterMap.put("conditions", whereCondition);
			getSqlSession().update(
					getNameSpace(GenericDAOBatisImpl.class, "updateWithMap"),
					parameterMap);
		}
	}

	protected void updateByCode(Map<String, Object> map, String code) {
		if (map != null) {
			List<String> fields = new ArrayList<String>();
			List<Object> values = new ArrayList<Object>();
			for (String key : map.keySet()) {
				fields.add(key);
				values.add(map.get(key));
			}
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put(VAR_TABLE_NAME, getTableName());
			parameterMap.put("fields", fields);
			parameterMap.put("values", values);
			parameterMap.put("code", code);
			getSqlSession().update(
					getNameSpace(GenericDAOBatisImpl.class, "updateByCode"),
					parameterMap);
		}
	}

	@Override
	public final int deleteById(Long id) {
		Map<String, Object> params = new HashMap<>();
		params.put(VAR_TABLE_NAME, getTableName());
		params.put("id", id);
		return getSqlSession().update(
				getNameSpace(GenericDAOBatisImpl.class, "deleteById"), params);
	}

	@Override
	public final int deleteByCode(String code) {
		if (code == null || "".equals(code)) {
			throw new IllegalArgumentException(
					"deleteByCode: argument code is null or empty!!");
		}

		Map<String, Object> params = new HashMap<>();
		params.put(VAR_TABLE_NAME, getTableName());
		params.put("code", code);

		return getSqlSession()
				.update(getNameSpace(GenericDAOBatisImpl.class, "deleteByCode"),
						params);
	}

	@Override
	public final int delete(E entity) {
		return deleteById(entity.getId());
	}

	@Override
	public final void delete(List<E> entities) {
		List<Long> ids = new ArrayList<>();
		if (entities != null && entities.size() > 0) {
			for (E e : entities) {
				ids.add(e.getId());
			}
		}
		if (ids != null && ids.size() > 0) {
			Map<String, Object> params = new HashMap<>();
			params.put(VAR_TABLE_NAME, getTableName());
			params.put("ids", ids);
			getSqlSession()
					.update(getNameSpace(GenericDAOBatisImpl.class,
							"batchDeleteByIds"), params);
		}
	}

	public String getNameSpace(String operate) {
		return getNameSpace(daoImplClass, operate);
	}

	@SuppressWarnings("rawtypes")
	public static String getNameSpace(Class daoImplClass, String operate) {
		String nameSpace = NAME_SPACE_MAP.get(daoImplClass);
		if (nameSpace == null || "".equals(nameSpace)) {
			nameSpace = daoImplClass.getName() + NAME_SPACE_SUFFIX;
			NAME_SPACE_MAP.put(daoImplClass, nameSpace);
		}
		return nameSpace + "." + operate;
	}

	public String getTableName() {
		return getTableName(this.daoImplClass);
	}

	@SuppressWarnings("rawtypes")
	public static String getTableName(Class daoImplClass) {

		String tableName = TABLE_NAME_MAP.get(daoImplClass);

		if (tableName != null && !"".equals(tableName)) {
			return tableName;
		}

		StringBuilder sbd = new StringBuilder();

		Matcher m = P_DAOIMPL_NAME.matcher(daoImplClass.getSimpleName());

		if (!m.matches()) {
			throw new IllegalArgumentException(daoImplClass.getSimpleName()
					+ " is illegal DAOImpl class!!");
		}

		for (char c : m.group(1).toCharArray()) {
			if (c >= 'A' && c <= 'Z') {
				if (sbd.length() > 0) {
					sbd.append('_');
				}
				sbd.append(String.valueOf(c).toLowerCase());

			} else {
				sbd.append(c);
			}
		}

		tableName = TABLE_NAME_PREFIX + sbd.toString();

		TABLE_NAME_MAP.put(daoImplClass, tableName);

		return tableName;
	}

	protected List<E> selectList(String operate) {
		return selectList(operate, null);
	}

	protected List<E> selectList(String operate, Object param) {
		return selectList(this.daoImplClass, operate, param);
	}

	protected List<E> selectList(Class<?> clzss, String operate, Object param) {

		return getSqlSession().selectList(getNameSpace(clzss, operate), param);
	}

	protected List<E> selectList(String operate, int pageIndex, int pageSize) {

		return selectList(operate, null, pageIndex, pageSize);
	}

	protected List<E> selectList(String operate, Object param, int pageIndex,
			int pageSize) {

		return selectList(this.daoImplClass, operate, param, pageIndex,
				pageSize);
	}

	protected List<E> selectList(Class<?> clzss, String operate, Object param,
			int pageIndex, int pageSize) {

		return getSqlSession().selectList(getNameSpace(clzss, operate), param,
				makeRowBounds(pageIndex, pageSize));
	}

	protected E selectOne(String operate) {
		return selectOne(operate, null);
	}

	@SuppressWarnings("unchecked")
	protected E selectOne(String operate, Object param) {
		return (E) getSqlSession().selectOne(getNameSpace(operate), param);
	}

	// 查询记录总条数
	protected Long selectCount(String operate, Object param) {
		return (Long) getSqlSession().selectOne(getNameSpace(operate), param);
	}

	protected E genericSelectOne(String operate, Object param) {

		GenericResultHandler resultHandler = new GenericResultHandler();

		getSqlSession().select(
				getNameSpace(GenericDAOBatisImpl.class, operate), param,
				resultHandler);

		return resultHandler.getResult();
	}

	protected RowBounds makeRowBounds(int pageIndex, int pageSize) {

		pageIndex = pageIndex < 1 ? 1 : pageIndex;
		pageSize = (pageSize <= 0 || pageSize > PAGESIZE_MAX) ? PAGESIZE_MAX
				: pageSize;

		return new RowBounds((pageIndex - 1) * pageSize, pageSize);
	}

	/*
	 * internal ResultHandler class
	 */
	protected class GenericResultHandler implements ResultHandler {

		private E entity;

		@Override
		public void handleResult(ResultContext context) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) context
					.getResultObject();
			if (map != null) {
				entity = objectFactory
						.create(GenericDAOBatisImpl.this.entityClass);

				try {
					GrapeDAOUtils.populate(entity, map);
				} catch (Exception ex) {
					logger.error("GenericResultHandler.handleResult exception",
							ex);
				}
			}
		}

		public E getResult() {
			return entity;
		}

	}

}
