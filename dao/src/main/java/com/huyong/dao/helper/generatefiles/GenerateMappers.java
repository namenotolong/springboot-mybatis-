package com.huyong.dao.helper.generatefiles;

import com.huyong.dao.helper.BaseMapper;
import com.huyong.dao.helper.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述: 生成Mapper接口
 *
 * @author huyong
 * @date 2020-03-18 9:14 下午
 */
public class GenerateMappers extends AbstractGenerate {

    public GenerateMappers(Generate generate) {
        super(generate);
    }

    @Override
    public void generate() {
        generate.generate();
        final File file = new File(root + Constant.DAO_ENTITY_PATH);
        final File order = new File(root + Constant.DAO_MAPPER_PATH);
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
                                .append(Constant.MAPPER_PACKAGE)
                                .append(";\n")
                                .append("\nimport ")
                                .append(Constant.ENTITY_PACKAGE)
                                .append(".")
                                .append(className)
                                .append(";\n" +
                                        "import ")
                                .append(BaseMapper.class.getName())
                                .append(";\n" +
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
}
