package com.virginia;

import com.virginia.utils.EmailUtils;
import com.virginia.utils.PasswordUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CrmBackApplicationTests {
    @Resource
    private EmailUtils emailUtils;

    @Resource
    private PasswordUtils passwordUtils;

    @Test
    // 使用Bcrypt对密码加密
    void bcryptTest(){
        // 创建 BCryptPasswordEncoder 实例
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 原始密码
        String rawPassword = "yuyan123";

        // 加密密码
        String encodedPassword = encoder.encode(rawPassword);
//
//        // 输出加密后的密码
        System.out.println("Encoded Password: " + encodedPassword);

        // 验证密码
        boolean isMatch = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Password Match: " + isMatch);
    }

    @Test
    void getJWTSecret() {
        // 从系统环境变量中拿到JWT_SECRET
        String jwtSecret = System.getenv("JWT_SECRET");
        System.out.println(jwtSecret);
    }

    @Test
    void getMySQLPwd() {
        System.out.println(System.getenv("SPRING_DATASOURCE_PASSWORD"));
    }

    @Test
    void sendEmail() {
        // 使用emailUtils发送邮件测试
        emailUtils.sendLocalizedTemplateEmail("469868264@qq.com", "yuyan", "yuyan123", "ja");
    }

    @Test
    void generateRandomPassword() {
        String password = PasswordUtils.generateRandomPassword(6, 16);
        System.out.println(password);
    }
}
