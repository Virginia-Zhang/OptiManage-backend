package com.virginia.utils;

import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// 使用QQ邮箱发送邮件
@Component
public class EmailUtils {
    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送简单文本邮件
     *
     * @param to      收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    // 异步发送邮件
    @Async
    public void sendSimpleEmail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("zhangsakurayi@qq.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
            System.out.println("邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("邮件发送失败");
        }
    }
}
