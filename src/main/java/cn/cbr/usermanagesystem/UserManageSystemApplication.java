package cn.cbr.usermanagesystem;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.cbr.usermanagesystem.mapper")
public class UserManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManageSystemApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }

}
