package com.huyong.exception;

/**
 * @author huyong
 */
public class ContinueException extends RuntimeException{
    /**
     * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
     */
    private static final long serialVersionUID = 3455708526465670030L;

    public ContinueException(String msg){
        super(msg);
    }
}