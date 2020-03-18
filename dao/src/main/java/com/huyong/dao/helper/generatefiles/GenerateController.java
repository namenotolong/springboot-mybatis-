package com.huyong.dao.helper.generatefiles;

import com.huyong.dao.helper.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述: 生成Controller接口
 *
 * @author huyong
 * @date 2020-03-18 9:14 下午
 */
public class GenerateController extends AbstractGenerate {

    public GenerateController(Generate generate) {
        super(generate);
    }

    @Override
    public void generate() {
        generate.generate();
        final File file = new File(root + Constant.SERVICE__PATH);
        final File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            try {
                for (File listFile : files) {
                    final String name = listFile.getName();
                    if (name.endsWith(".java")) {
                        String serviceName = name.split("\\.")[0];
                        String className = serviceName.replace("Service", "Controller");
                        String fileName = className + ".java";
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(root + Constant.CONTROLLER__PATH + fileName));
                        StringBuilder sb = new StringBuilder();
                        sb.append("package ")
                                .append(Constant.CONTROLLER_PACKAGE)
                                .append(";\n")
                                .append("\nimport ")
                                .append(Constant.SERVICE_PACKAGE)
                                .append(".")
                                .append(serviceName).append(";\n" +
                                        "import org.springframework.stereotype.Controller;\n" +
                                        "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                                "import javax.annotation.Resource;")
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
                                .append("@Controller\n" +
                                        "@RequestMapping(\"/")
                                .append(className)
                                .append("\")")
                                .append("\npublic class ")
                                .append(className)
                                .append(" {\n")
                                .append("\n    @Resource\n" +
                                        "    private ")
                                .append(serviceName)
                                .append(" ")
                                .append(initialLower(serviceName))
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
