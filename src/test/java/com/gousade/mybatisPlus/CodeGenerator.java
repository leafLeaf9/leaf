package com.gousade.mybatisPlus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author
 * @since 2018/12/13
 */
@SpringBootTest
public class CodeGenerator {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Test
    public void run() {

        // 1、创建代码生成器
       /* AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");

        gc.setAuthor("woxigousade <woxigsd@gmail.com>");
        gc.setOpen(false); // 生成后是否打开资源管理器
        gc.setFileOverride(false); // 重新生成时文件是否覆盖

        // UserServie
        gc.setServiceName("%sService"); // 去掉Service接口的首字母I

        gc.setIdType(IdType.ASSIGN_UUID); // 主键策略
        gc.setDateType(DateType.ONLY_DATE);// 定义生成的实体类中日期类型
        gc.setSwagger2(true);// 开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword("password");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(""); // 模块名
        // 包 com.atguigu.eduservice
        pc.setParent("com.gousade");
        // 包 com.atguigu.eduservice.controller
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude("easy_excel_data");

        strategy.setNaming(NamingStrategy.underline_to_camel);// 数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); // 生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// 数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); // restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); // url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();*/
    }
}
