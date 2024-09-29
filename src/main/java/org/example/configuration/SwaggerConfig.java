package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        // 指定扫描包路径
        return new Docket(DocumentationType.SWAGGER_2) // 指定生成的文档的类型是Swagger2
                .apiInfo(apiInfo())
                //.pathMapping("/swagger")
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）,配置文档页面的基本信息内容
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("org.example.controller"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                //.securitySchemes(Collections.singletonList(securitySchemes()))
                //.securityContexts(Collections.singletonList(securityContexts()))
                ;
    }

    /**
     * 认证方式使用密码模式
     */
    private SecurityScheme securitySchemes() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("/auth/oauth/token");

        return new OAuthBuilder()
                .name("OAuth2")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * 设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("OAuth2", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        AuthorizationScope authorizationScope = new AuthorizationScope("all", "接口测试");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return authorizationScopes;
    }
    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("描述：Spring Boot中使用Swagger2构建RESTful APIs")
                // 描述
                .description("swagger 测试demo")
                //作者信息、联系方式：Contact(String name, String url, String email)
                .contact(new Contact("oyc","https://blog.csdn.net/u014553029","1456682842@qq.com"))
                // 版本
                .version("版本号: 1.0.1")
                .build();
    }
}
