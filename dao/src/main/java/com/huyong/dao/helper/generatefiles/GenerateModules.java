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

    public GenerateModules(Generate generate) {
        super(generate);
    }
    @Override
    public void generate() {
        generate.generate();
        final File file = new File(root + Constant.DAO_ENTITY_PATH);
        final File order = new File(root + Constant.DAO_MODULE_PATH);
        if (!order.exists()) {
            order.mkdirs();
        }
        final File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            try {
                for (File listFile : files) {
                    final String name = listFile.getName();
                    if (name.endsWith(".java")) {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(listFile));
                        final String s = order + File.separator + listFile.getName().replace("DO", "BO");
                        final File file1 = new File(s);
                        if (!file1.exists()) {
                            final boolean newFile = file1.createNewFile();
                            if (!newFile) {
                                throw new RuntimeException("创建文件失败！");
                            }
                        }
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1));
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
                                len = "package " + Constant.MODULE_PACKAGE + ";\n" + "\nimport io.swagger.annotations.ApiModel;\n" +
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
}
