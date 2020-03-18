package com.huyong.test;

import com.huyong.dao.entity.UserDO;
import com.huyong.enums.UserEnums;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.Iterator;

/**
 * author: haiyangp
 * date:  2017/8/19
 * desc: Main
 */
public class MybatisMain {
    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/boke";
    private String username = "root";
    private String password = "123456";


    public static void main(String[] args) {
        MybatisMain mybatis =new MybatisMain();
        SqlSessionFactory sqlSessionFactory = mybatis.initMybatis();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        java.util.List<UserDO> userList = userMapper.getAllUser();
        for (UserDO userItem : userList) {
            System.out.println(userItem);
        }
        final Collection<MappedStatement> mappedStatements = sqlSessionFactory.getConfiguration().getMappedStatements();

    }

    /**
     * 初始化mybatis,并返回sqlSessionFactory
     */
    private SqlSessionFactory initMybatis() {
        DataSource dataSource = initDataSourceWithReturn();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("justForTest", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        return new DefaultSqlSessionFactory(configuration);
    }


    /**
     * 初始化并获取dataSource
     *
     * @return DataSource
     */
    private DataSource initDataSourceWithReturn() {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setUrl(url);
        dataSource.setDriver(driverName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
