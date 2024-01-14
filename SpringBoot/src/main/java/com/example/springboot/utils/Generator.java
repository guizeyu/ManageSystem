package com.example.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.apache.commons.lang.ObjectUtils;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        generator();
    }

    private static void generator() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/gzy?serverTimezone=GMT%2b8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("gzy") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\ins\\Desktop\\VS\\SpringBoot\\src\\main\\java"); // 指定输出目录
                })
//                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
//                    if (typeCode == Types.SMALLINT) {
//                        // 自定义类型转换
//                        return DbColumnType.INTEGER;
//                    }
//                    return typeRegistry.getColumnType(metaInfo);
//
//                }))
                .packageConfig(builder -> {
                    builder.parent("com.example.springboot") // 设置父包名
                            .moduleName(null) // 设置父包模块名,不设置为null会有//user的问题
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                    "C:\\Users\\ins\\Desktop\\VS\\SpringBoot\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀,这里sys就忽略了sys
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
