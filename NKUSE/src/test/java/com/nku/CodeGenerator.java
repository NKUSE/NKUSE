package com.nku;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nkuse-cet6?useUnicode=true&characterEncoding=UTF-8&&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "root";
        String author = "lyh";
        String outputDir = "D:\\desktop\\se\\sourcecodes\\NKUSE\\src\\main\\java";
        String basePackage = "com.nku";
        String moduleName = "cet";
        String mapperLocation = "D:\\desktop\\se\\sourcecodes\\NKUSE\\src\\main\\resources\\mapper\\" + moduleName;
        String tableName = "cet6_answersheet,cet6_enum_examstate,cet6_enum_usertype,cet6_examinfo,cet6_obj_ques,cet6_paperinfo,cet6_registinfo,cet6_roominfo,cet6_userinfo";
        String tablePrefix = "cet6_";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
