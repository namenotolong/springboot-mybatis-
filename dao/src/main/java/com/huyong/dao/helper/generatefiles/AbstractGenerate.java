package com.huyong.dao.helper.generatefiles;


/**
 * 描述: 抽象生成文件
 *
 * @author huyong
 * @date 2020-03-19 1:10 上午
 */
public abstract class AbstractGenerate implements Generate {

    protected String root = System.getProperty("user.dir");

    protected Generate generate;

    public AbstractGenerate(Generate generate) {
        this.generate = generate;
    }

    public String initialLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

}
