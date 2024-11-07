package com.gousade.mybatisPlus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

//@SpringBootTest
public class MybatisPlusCodeGeneratorTests {
    /*@Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;*/

    @Test
    public void runFast() {
        FastAutoGenerator.create("jdbc:mysql://192.168.0.204:3306/subcenter_monitor_rc?useSSL=false",
                        "root",
                        "GGT@82339319")
                .globalConfig(builder -> {
                    builder.author("Administrator") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\IdeaProjects\\gousade\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("direction.device.inspection") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                    /*.pathInfo(Collections.singletonMap(OutputFile.other, "E://MybatisPlusCodeGenerator"))*/; // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> builder.addInclude("device_inspection_log") // 设置需要生成的表名
                        // 设置过滤表前缀
                        .addTablePrefix("t_", "c_")
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().enableRemoveIsPrefix().disableSerialVersionUID()
                        .idType(IdType.ASSIGN_ID)
                        .logicDeleteColumnName("is_deleted")
                        .addTableFills(new Column("create_time", FieldFill.INSERT))
                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                        .build())
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

}
