package com.huyong.dao.helper;

import com.huyong.dao.helper.annotation.Table;
import com.huyong.dao.helper.dbbuilder.MysqlBuilder;
import com.huyong.dao.helper.sqlgenerate.*;
import com.huyong.dao.helper.utils.BuilderUtils;
import com.huyong.dao.helper.utils.TransformUtils;
import com.huyong.exception.DAOException;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.NestedIOException;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: base mapper
 *
 * @author huyong
 * @date 2020-03-13 10:16 下午
 */
public abstract class AbstractGenericMapper<E> extends SqlMapBaseDAO<E> implements InitializingBean, ApplicationContextAware, GenerateMapper<E> {
    private ApplicationContext applicationContext;

    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    protected final String getStatement() {
        return BuilderUtils.getStatement(getEntityClass());
    }

    /**
     * 子类可添加自定义generate eg: return new ExtendGenerate(gen)
     *
     * @param gen
     * @return
     */
    protected AbstractSqlGenerate addExtendGenerate(AbstractSqlGenerate gen) {
        return gen;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Map<String, SqlSessionTemplate> e = this.applicationContext.getBeansOfType(SqlSessionTemplate.class);
        Table table = this.getEntityClass().getAnnotation(Table.class);
        if(table != null){
            String dbRoute = table.sqlSession();
            if(!StringUtils.isEmpty(dbRoute)){
                sqlSession = e.get(dbRoute);
            }
        }
        if(sqlSession == null){
            sqlSession = e.values().iterator().next();
        }
        if (sqlSession != null) {
            try {
                //ibatis 后面可以考虑配置选择数据库类型和orm框架类型
                Builder ibatisBuilder = new MysqlBuilder(getEntityClass());
                //生产sqlMap
                AbstractSqlGenerate gen = new InsertGenerate(new SelectByPrimaryGenerate(ibatisBuilder));
                gen = new UpdateByPrimaryGenerate(new DeleteByPrimaryGenerate(gen));
                gen = new CountGenerate(new QueryByPageGenerate(gen));
                gen = new BatchInsertGenerate(new QueryByConditionGenerate(gen));

                gen = addExtendGenerate(gen);

                gen.buildHeader();
                gen.buildBody();
                gen.buildFooter();
                String sqlMap = gen.getBuilderResult().toString();

                InputStream sqlMapInputStream = new ByteArrayInputStream(sqlMap.getBytes());
                Configuration configuration = sqlSession.getConfiguration();
                //resource 不实际存在，为了跳过校验，这里设置了一个默认值
                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(sqlMapInputStream,
                        configuration, getEntityClass().getName(), configuration.getSqlFragments());
                xmlMapperBuilder.parse();
            } catch (Exception ex) {
                throw new NestedIOException(
                        "Failed to parse mapping resource: "
                                + getEntityClass().getName(), ex);
            }
        }else{
            throw new RuntimeException("cannot find sqlSession, please config SqlSessionTemplate");
        }
    }

    @Override
    public E getByPrimary(long primary) throws DAOException {
        return super.selectOne(getStatement() + "." + AbstractSqlGenerate.SELECT_BY_PRIMARY,
                primary);
    }

    @Override
    public int insert(E e) throws DAOException {
        return super.insert(getStatement() + "." + AbstractSqlGenerate.INSERT,
                e);
    }

    @Override
    public int batchInsert(List<E> e) throws DAOException {
        return super.insert(getStatement() + "." + AbstractSqlGenerate.BATCH_INSERT,
                e);
    }

    @Override
    public int updateByPrimary(E e) throws DAOException {
        return super.update(getStatement() + "." + AbstractSqlGenerate.UPDATE_BY_PRIMARY,
                e);
    }

    @Override
    public int deleteByPrimary(long primary) throws DAOException {
        return super.delete(getStatement() + "." + AbstractSqlGenerate.DELETE_BY_PRIMARY,
                primary);
    }

    @Override
    public List<E> queryByCondition(E condition)
            throws DAOException {
        return queryByCondition(condition, null);
    }

    @Override
    public List<E> queryByCondition(E condition, List<Sort> sort) throws DAOException {

        if (condition == null) {
            throw new RuntimeException("condition cannot be null!");
        }

        Map<String, Object> param = entityToMap(condition);

//        checkSortField(sort);

        if (sort != null && sort.isEmpty() == false) {
            for (Sort s : sort) {
                s.setSortField(TransformUtils.camelToUnderline(s.getSortField()));
            }
        }

        param.put(AbstractSqlGenerate.KEY_NAME_SORT, sort);

        return super.selectList(getStatement() + "." + AbstractSqlGenerate.QUERY_BY_CONDITION,
                param);
    }


    /**
     * 实体对象转map对象
     * @param condition
     * @return
     */
    private Map<String, Object> entityToMap(E condition) {
        Map<String, Object> param = new HashMap<String, Object>();

        Field[] fields = condition.getClass().getDeclaredFields();

        for (Field f : fields) {
            if (BuilderUtils.ignoreField(f)) {
                continue;
            }
            String name = f.getName();

            try {
                f.setAccessible(true);
                Object value = f.get(condition);
                if (value == null) {
                    continue;
                }
                param.put(name, f.get(condition));
            } catch (Exception e) {
                throw new RuntimeException("get condition field:" + name + " occurred error", e);
            }
        }

        return param;
    }

    /**
     * 校验排序字段，防止sql注入
     *
     * @param sort
     */
    private void checkSortField(List<Sort> sort) {
        if (sort != null) {
            for (Sort s : sort) {
                boolean hit = Boolean.FALSE;
                for (Field f : getEntityClass().getDeclaredFields()) {
                    if (f.getName().equals(TransformUtils.underLineToCamel(s.getSortField()))) {
                        hit = Boolean.TRUE;
                        break;
                    }
                }
                if (!hit) {
                    throw new RuntimeException("sort field:" + s.getSortField() + " is not a property belong to the entity class, please check it");
                }
            }
        }
    }

    @Override
    public List<E> queryByPage(E condition, int offset,
                               int size, List<Sort> sort) throws DAOException {
        if (offset < 0) {
            throw new IllegalArgumentException(
                    "Offset argument value could not be negative integer");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Size argument value must be positive integer");
        }

        Map<String, Object> param = entityToMap(condition);

//        checkSortField(sort);

        if (sort != null && !sort.isEmpty()) {
            for (Sort s : sort) {
                s.setSortField(TransformUtils.camelToUnderline(s.getSortField()));
            }
        }

        param.put(AbstractSqlGenerate.KEY_NAME_OFFSET, offset);
        param.put(AbstractSqlGenerate.KEY_NAME_SIZE, size);
        param.put(AbstractSqlGenerate.KEY_NAME_SORT, sort);

        return super.selectList(getStatement() + "." + AbstractSqlGenerate.QUERY_BY_PAGE,
                param);
    }

    @Override
    public int count(E condition) throws DAOException {

        if (condition == null) {
            throw new RuntimeException("condition cannot be null!");
        }

        Map<String, Object> param = entityToMap(condition);

        int result = 0;
        Object tempResult = super.selectOne(getStatement() + "." + AbstractSqlGenerate.COUNT,
                param);
        if (tempResult != null) {
            result = (Integer.parseInt(String.valueOf(tempResult)));
        }
        return result;
    }

    @Override
    public E getOne(E condition, List<Sort> sort)
            throws DAOException {
        List<E> list = queryByCondition(condition, sort);
        if (list != null && list.isEmpty() == false) {
            return list.iterator().next();
        }
        return null;
    }

    @Override
    public E getOne(E condition)
            throws DAOException {
        return getOne(condition, null);
    }
}
