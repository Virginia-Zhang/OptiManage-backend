package com.virginia.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

// 创建一个配置类来配置MessageSource Bean
@Configuration
public class InternationalizationConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // 设置消息资源文件的基础名称
        messageSource.setBasename("classpath:messages");
        // 设置默认编码
        messageSource.setDefaultEncoding("UTF-8");
        // 设置缓存持续时间（秒）
        messageSource.setCacheSeconds(3600);
        // 禁用回退到系统语言
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }
}
