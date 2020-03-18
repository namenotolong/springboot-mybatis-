package com.huyong.dao.helper.generatefiles;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述: 生成Mapper接口
 *
 * @author huyong
 * @date 2020-03-18 9:14 下午
 */
public class GenerateMappers {
    private String resource;
    private String target;
    public GenerateMappers(String resource, String target) {
        this.resource = resource;
        this.target = target;
    }
    public void generate(String root) {
        final File file = new File(root + resource.replace('.', '/'));
        final File order = new File(root + target.replace('.', '/'));
        final File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            try {
                for (File listFile : files) {
                    final String name = listFile.getName();
                    if (name.endsWith(".java")) {
                        final String className = name.split("\\.")[0];
                        String mapperName = className.substring(0, className.length() - 2) + "Mapper";
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(order + "/" + mapperName + ".java"));
                        StringBuilder sb = new StringBuilder();
                        sb.append("package ")
                                .append(target)
                                .append(";\r\n")
                                .append("import com.huyong.dao.entity.")
                                .append(className)
                                .append(";\n" +
                                        "import com.huyong.dao.helper.BaseMapper;\n" +
                                        "import org.springframework.stereotype.Repository;\n")
                                .append("/**\n" +
                                        " * 描述: ")
                                .append(className)
                                .append("对应的Mapper\n" +
                                        " *\n" +
                                        " * @author huyong\n" +
                                        " * @date ")
                                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss")))
                                .append("\n" +
                                        " */\n")
                                .append("@Repository\n")
                                .append("public interface ")
                                .append(mapperName)
                                .append(" extends BaseMapper<")
                                .append(className)
                                .append("> {\n" +
                                        "}");
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
        String root = new File("").getCanonicalPath() + "/dao/src/main/java/";
        final GenerateMappers generateMappers = new GenerateMappers("com.huyong.dao.entity", "com.huyong.dao.mapper");
        generateMappers.generate(root);
    }
}
