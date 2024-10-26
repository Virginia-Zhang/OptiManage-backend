package com.virginia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CrmBackApplicationTests {
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
}
