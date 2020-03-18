package com.huyong.dao.helper.generatefiles;

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
public class GenerateMapperXmls {
    private String mapperInterfacesPath;
    private String target;
    public GenerateMapperXmls(String mapperInterfacesPath, String target) {
        this.mapperInterfacesPath = mapperInterfacesPath;
        this.target = target;
    }
    public void generate(String root) {
        final File file = new File(root + "java/" + mapperInterfacesPath.replace('.', '/'));
        final File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            try {
                for (File listFile : files) {
                    final String name = listFile.getName();
                    if (name.endsWith(".java")) {
                        final String className = name.split("\\.")[0];
                        String fileName = className.substring(0, 1).toLowerCase() + className.substring(1);
                        String nameSpace = mapperInterfacesPath.replace("mapper", "entity") + "." + fileName.substring(0, fileName.length() - "mapper".length());
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(target +  fileName + ".xml"));
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

    public static void main(String[] args) throws IOException {
        String root = new File("").getCanonicalPath() + "/dao/src/main/";
        final GenerateMapperXmls generateMapperXmls = new GenerateMapperXmls("com.huyong.dao.mapper", root + "resources/mapper/");
        generateMapperXmls.generate(root);
    }
}
