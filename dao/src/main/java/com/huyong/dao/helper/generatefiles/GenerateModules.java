package com.huyong.dao.helper.generatefiles;

import com.huyong.dao.helper.Constant;

import java.io.*;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-03-18 2:45 下午
 */
public class GenerateModules extends AbstractGenerate {

    private static String root = null;
    static {
        try {
            root = new File(".").getCanonicalPath() + "/dao/src/main/java/";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GenerateModules(Generate generate) {
        super(generate);
    }
    @Override
    public void generate() {
        final File file = new File(Constant.DAO_ENTITY_PATH);
        final File order = new File(root + target.replace('.', '/'));
        final File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            try {
                for (File listFile : files) {
                    final String name = listFile.getName();
                    if (name.endsWith(".java")) {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(listFile));
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(order + "/" + listFile.getName().replace("DO", "BO")));
                        String len;
                        String newName = name.replace("DO", "BO").split("\\.")[0];
                        String introduction = null;
                        //是否经过了class
                        boolean flag = false;
                        while ((len = bufferedReader.readLine()) != null) {
                            if (!flag && len.contains("描述")) {
                                introduction = len.split(":")[1].substring(1);
                            }
                            if (len.contains("java.io.Serializable")) {
                                continue;
                            } else if (len.startsWith("public class")) {
                                len = "@ApiModel(\"" + introduction + "\")" + "\npublic class " + newName + " {";
                                flag = true;
                            } else if (len.startsWith("package ")) {
                                len = "package " + target + ";\n" + "import io.swagger.annotations.ApiModel;\n" +
                                        "import io.swagger.annotations.ApiModelProperty;";
                            } else if (flag && (len.contains("static") || len.contains("/**"))) {
                                continue;
                            } else if (flag && len.contains(" */")) {
                                len = len.replace(" */", "@ApiModelProperty(\"" + introduction + "\")");
                            } else if (flag && len.contains("*")) {
                                introduction = len.split("\\*")[1].substring(1);
                                continue;
                            }
                            bufferedWriter.write(len);
                            bufferedWriter.newLine();
                        }
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        bufferedReader.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //final GenerateModules generateModules = new GenerateModules("com.huyong.dao.module", "com.huyong.dao.entity");
        //generateModules.generate();
        System.out.println(System.getProperty("user.dir"));
    }
}