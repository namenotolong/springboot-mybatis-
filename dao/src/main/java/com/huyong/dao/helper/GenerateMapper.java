package com.huyong.dao.helper;

import com.huyong.dao.helper.annotation.Table;
import com.huyong.dao.helper.dbbuilder.MysqlBuilder;
import com.huyong.dao.helper.sqlgenerate.*;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述: generateMapper
 *
 * @author huyong
 * @date 2020-03-16 3:05 下午
 */
@Component
public class GenerateMapper implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private SqlSessionTemplate sqlSession;

    public void generate(Class<?> target, Class<?> mapper) {
        Map<String, SqlSessionTemplate> e = this.applicationContext.getBeansOfType(SqlSessionTemplate.class);
        Table table = target.getAnnotation(Table.class);
        if(table != null){
            String sqlSessionTemp = table.sqlSession();
            if (!"".equals(sqlSessionTemp.trim())) {
                sqlSession = e.get(sqlSessionTemp);
                if(sqlSession == null){
                    throw new RuntimeException(
                            "find multi datasource, but cannot find sqlSession bean named:" + sqlSessionTemp);
                }
            }
        }
        if(sqlSession == null){
            sqlSession = e.values().iterator().next();
        }
        Builder builder = new MysqlBuilder(target, mapper);
        //生产sqlMap
        AbstractSqlGenerate gen = new InsertGenerate(new SelectByPrimaryGenerate(builder));
        gen = new UpdateByPrimaryGenerate(new DeleteByPrimaryGenerate(gen));
        gen = new CountGenerate(new QueryByPageGenerate(gen));
        gen = new BatchInsertGenerate(new QueryByConditionGenerate(gen));
        gen = new QueryByConditionOrderBySortGenerate(new QueryByConditionOrderBySortsGenerate(gen));
        gen = new QueryByPageWithSortGenerate(new QueryByPageWithSortsGenerate(gen));
        gen = new UpdateByConditionGenerate(new DeleteByConditionGenerate(gen));
        gen.buildHeader();
        gen.buildBody();
        gen.buildFooter();
        String sqlMap = gen.getBuilderResult().toString();
        InputStream sqlMapInputStream = new ByteArrayInputStream(sqlMap.getBytes());
        final Configuration configuration = sqlSession.getConfiguration();
        //resource 不实际存在，为了跳过校验，这里设置了一个默认值
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(sqlMapInputStream,
                configuration, target.getName(), configuration.getSqlFragments());
        xmlMapperBuilder.parse();
    }


    @Override
    public void afterPropertiesSet() {
        final Map<String, Configuration> configurations = this.applicationContext.getBeansOfType(Configuration.class);
        if (configurations.isEmpty()) {
            return;
        }
        List<Class<?>> mappers = new ArrayList<>();
        configurations.forEach((k, v) -> mappers.addAll(v.getMapperRegistry().getMappers()));
        if (!mappers.isEmpty()) {
            for (Class<?> mapper : mappers) {
                final Type[] genericInterfaces = mapper.getGenericInterfaces();
                if (genericInterfaces != null && genericInterfaces.length > 0) {
                    for (Type genericInterface : genericInterfaces) {
                        final Type[] actualTypeArguments = ((ParameterizedType) genericInterface).getActualTypeArguments();
                        if (null != actualTypeArguments && actualTypeArguments.length > 0) {
                            for (Type actualTypeArgument : actualTypeArguments) {
                                generate((Class<?>) actualTypeArgument, mapper);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
