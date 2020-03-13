package com.huyong.dao.helper;

import com.huyong.exception.DAOException;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 * 描述: sql mapper base
 *
 * @author huyong
 * @date 2020-03-13 10:17 下午
 */
public class SqlMapBaseDAO<T> {
    protected SqlSessionTemplate sqlSession;

    public T selectOne(String statementName, Object parameterObject){
        checkParam(statementName);
        T reuslt = null;
        try{
            reuslt = sqlSession.selectOne(statementName, parameterObject);
        }catch(Exception e){
            throw new DAOException(e.getCause());
        }
        return reuslt;
    }

    public int insert(String statementName, Object parameterObject){
        checkParam(statementName);
        int result = 0;
        try {
            result = sqlSession.insert(statementName, parameterObject);
        } catch (Exception e) {
            throw new DAOException(e.getCause());
        }
        return result;
    }


    public int update(String statementName, Object parameterObject){
        checkParam(statementName);
        int result = 0;
        try {
            result = sqlSession.update(statementName, parameterObject);
        } catch (Exception e) {
            throw new DAOException(e.getCause());
        }
        return result;
    }

    public int count(String statementName, Object parameterObject){
        checkParam(statementName);
        int result = 0;
        try {
            result = sqlSession.selectOne(statementName, parameterObject);
        } catch (Exception e) {
            throw new DAOException(e.getCause());
        }
        return result;
    }

    public int delete(String statementName, Object parameterObject){
        checkParam(statementName);
        int result = 0;
        try {
            result = sqlSession.delete(statementName, parameterObject);
        } catch (Exception e) {
            throw new DAOException(e.getCause());
        }
        return result;
    }

    public List<T> selectList(String statementName, Object parameterObject){
        checkParam(statementName);
        List<T> result = null;
        try {
            result = sqlSession.selectList(statementName, parameterObject);
        } catch (Exception e) {
            throw new DAOException(e.getCause());
        }
        return result;
    }

    private void checkParam(String statementName){
        if(statementName == null || "".equals(statementName.trim())){
            throw new DAOException("statement is empty!");
        }
    }

    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
}