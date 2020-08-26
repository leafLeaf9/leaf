package com.gousade.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
* @author woxigsd@gmail.com
* @date 2020-8-25 16:54:43
* Description: Swagger3配置类
* http://springfox.github.io/springfox/docs/current/#configuration-explained
*/
@Configuration
public class Swagger3Config {
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.OAS_30)
        		.groupName("gousade")
                .apiInfo(apiInfo())
                .select()
                /**
                 * apis() allows selection of RequestHandler's using a predicate. 
                 * The example here uses an any predicate (default). 
                 * Out of the box predicates provided are any, none, withClassAnnotation, withMethodAnnotation and basePackage.
                 */
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//只显示被此注解标注的方法
                .apis(RequestHandlerSelectors.basePackage("com.gousade.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3-GisardAdminLTE 3.0.5接口API文档")
                .description("文档描述")
                .contact(new Contact("java", "https://github.com/woxigousade/gousade", "woxigsd@gmail.com"))
                .version("1.1.0")
                .license("MIT License")
                .licenseUrl("https://github.com/woxigousade")
                .build();
    }
}