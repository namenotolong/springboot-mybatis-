package com.huyong.dao.helper.generatefiles;


/**
 * 描述: 抽象生成文件
 *
 * @author huyong
 * @date 2020-03-19 1:10 上午
 */
public abstract class AbstractGenerate implements Generate {
    /**
     * 模板文件的目录
     */
    protected String source;
    /**
     * 生成文件的目录
     */
    protected String target;

    protected String root = System.getProperty("user.dir");

    protected Generate generate;

    public AbstractGenerate(Generate generate) {
        this.generate = generate;
    }

}
