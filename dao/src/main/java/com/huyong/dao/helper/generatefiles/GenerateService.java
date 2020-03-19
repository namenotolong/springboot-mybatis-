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

    public static void main(String[] args) {
        final GenerateModifier generateModifier = new GenerateModifier();
        new GenerateService(generateModifier).generate();
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
                        String entityName = mapperName.replace("Mapper", "DO");
                        String moduleName = mapperName.replace("Mapper", "BO");
                        String parameterName = initialLower(entityName.substring(0, entityName.length() - 2));
                        String parametersName = parameterName + "s";
                        String fileName = className + ".java";
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(root + Constant.SERVICE__PATH + fileName));
                        StringBuilder sb = new StringBuilder();
                        sb.append("package ")
                                .append(Constant.SERVICE_PACKAGE)
                                .append(";\n")
                                .append("\nimport ").append(Constant.ENTITY_PACKAGE).append(".").append(entityName).append(";\n")
                                .append("import ").append(Constant.MODULE_PACKAGE).append(".").append(moduleName).append(";\n")
                                .append("import org.springframework.beans.BeanUtils;")
                                .append("\nimport org.springframework.stereotype.Service;\n" +
                                        "import javax.annotation.Resource;\n")
                                .append("import ")
                                .append(Constant.MAPPER_PACKAGE)
                                .append(".").append(mapperName).append(";")
                                .append("\n" +
                                        "\r\nimport java.util.ArrayList;\n" +
                                        "import java.util.List;\n" +
                                        "import java.util.stream.Collectors;\n\n/**\n" +
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
                                .append("\n" +
                                        "\n    public ")
                                .append(entityName)
                                .append(" convertBo2Do(")
                                .append(moduleName).append(" ").append(parameterName).append(") {\n" +
                                        "        ").append(entityName).append(" ")
                                .append(initialLower(entityName))
                                .append(" = new ").append(entityName).append("();\n" +
                                        "        BeanUtils.copyProperties(").append(parameterName)
                                .append(", ").append(initialLower(entityName)).append(");\n" +
                                        "        return ")
                                .append(initialLower(entityName)).append(";\n" +
                                        "    }\n" +
                                        "    public List<").append(entityName)
                                .append("> convertBos2Dos(List<")
                                .append(moduleName).append("> ")
                                .append(parametersName).append(") {\n" +
                                        "        if (null == ").append(parametersName)
                                .append(" || ").append(parametersName)
                                .append(".isEmpty()) {\n" +
                                        "            return new ArrayList<>();\n" +
                                        "        }\n" +
                                        "        return ").append(parametersName).append(".stream().map(this::convertBo2Do).collect(Collectors.toList());\n" +
                                        "    }\n" +
                                        "\n" +
                                        "    public ").append(moduleName).append(" convertDo2Bo(")
                                .append(entityName).append(" ").append(parameterName).append(") {\n" +
                                        "        ").append(moduleName).append(" ").append(initialLower(moduleName))
                                .append(" = new ").append(moduleName).append("();\n" +
                                        "        BeanUtils.copyProperties(").append(parameterName)
                                .append(", ").append(initialLower(moduleName)).append(");\n" +
                                        "        return ").append(initialLower(moduleName)).append(";\n" +
                                        "    }\n" +
                                        "    public List<").append(moduleName).append("> convertDos2Bos(List<")
                                .append(entityName).append("> ").append(parametersName).append(") {\n" +
                                        "        if (null == ").append(parametersName)
                                .append(" || ").append(parametersName).append(".isEmpty()) {\n" +
                                        "            return new ArrayList<>();\n" +
                                        "        }\n" +
                                        "        return ").append(parametersName).append(".stream().map(this::convertDo2Bo).collect(Collectors.toList());\n" +
                                        "    }")
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
