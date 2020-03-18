package com.huyong.dao.helper.generatefiles;

import com.huyong.dao.helper.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述: 生成Service接口
 *
 * @author huyong
 * @date 2020-03-18 9:14 下午
 */
public class GenerateService extends AbstractGenerate {

    public GenerateService(Generate generate) {
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
                        String mapperName = name.split("\\.")[0];
                        String className = mapperName.replace("Mapper", "Service");
                        String fileName = className + ".java";
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(root + Constant.SERVICE__PATH + fileName));
                        StringBuilder sb = new StringBuilder();
                        sb.append("package ")
                                .append(Constant.SERVICE_PACKAGE)
                                .append(";\n")
                                .append("\nimport org.springframework.stereotype.Service;\n" +
                                        "import javax.annotation.Resource;\n")
                                .append("import ")
                                .append(Constant.MAPPER_PACKAGE)
                                .append(".").append(mapperName).append(";")
                                .append("\n\n/**\n" +
                                        " * 描述: ")
                                .append(className)
                                .append("\n" +
                                        " *\n" +
                                        " * @author huyong\n" +
                                        " * @date ")
                                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss")))
                                .append("\n" +
                                        " */\n")
                                .append("@Service\n")
                                .append("public class ")
                                .append(className)
                                .append(" {\n")
                                .append("    @Resource\n" +
                                        "    private ")
                                .append(mapperName)
                                .append(" ")
                                .append(initialLower(mapperName))
                                .append(";")
                                .append("\n}");
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
