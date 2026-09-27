package cn.cbr.usermanagesystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        // 文档标题
                        .title("用户管理系统接口文档")
                        // 版本号
                        .version("1.0.0")
                        // 文档描述
                        .description("基于 SpringBoot + MyBatis-Plus 的用户管理后端接口")
                        // 联系人信息（可选）
                        .contact(new Contact()
                                .name("后端开发")
                                .email("dev@example.com"))
                );
    }
}
