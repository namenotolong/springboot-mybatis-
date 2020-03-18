package com.huyong.exception;

/**
 * 描述: dao exception
 *
 * @author huyong
 * @date 2020-03-13 10:18 下午
 */
public class DAOException extends RuntimeException {

    public DAOException(String e){
        super(e);
    }

    public DAOException(Throwable e){
        super(e);
    }
}
