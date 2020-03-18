package com.huyong.dao.helper.generatefiles;

/**
 * 描述: 修饰者
 *
 * @author huyong
 * @date 2020-03-19 1:35 上午
 */
public class GenerateModifier implements Generate {
    @Override
    public void generate() {

    }

    public static void main(String[] args) {
        Generate gen = new GenerateModifier();
        //包装模式
        gen = new GenerateController(new GenerateService(new GenerateMapperXmls(new GenerateMappers(new GenerateModules(gen)))));
        gen.generate();
    }
}
