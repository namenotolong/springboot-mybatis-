package com.huyong.dao.helper.generatefiles;

import com.huyong.dao.helper.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 描述: generateMapperXmls
 *
 * @author huyong
 * @date 2020-03-19 12:26 上午
 */
public class GenerateMapperXmls extends AbstractGenerate {

    public GenerateMapperXmls(Generate generate) {
        super(generate);
    }

    @Override
    public void generate() {
        generate.generate();
        final File file = new File(root + Constant.DAO_MAPPER_PATH);
        final File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            try {
                for (File listFile : files) {
                    final String name = listFile.getName();
                    if (name.endsWith(".java")) {
                        final String className = name.split("\\.")[0];
                        String fileName = className.substring(0, 1).toLowerCase() + className.substring(1);
                        String nameSpace = Constant.MAPPER_PACKAGE + "." + className;
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(root + Constant.DAO_MAPPER_XML_PATH +  fileName + ".xml"));
                        StringBuilder sb = new StringBuilder();
                        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                                "<mapper namespace=\"")
                                .append(nameSpace)
                                .append("\">\n" +
                                        "\n" +
                                        "</mapper>\n");
                        bufferedWriter.write(sb.toString());
                        bufferedWriter.flush();
                        bufferedWriter.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
