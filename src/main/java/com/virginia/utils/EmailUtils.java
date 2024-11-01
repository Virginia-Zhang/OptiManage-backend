package com.virginia.utils;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

// 使用QQ邮箱发送邮件
@Component
public class EmailUtils {
    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private MessageSource messageSource;

    /**
     * 根据用户的语言偏好发送本地化HTML邮件，使用Thymeleaf模板
     *
     * @param to                收件人邮箱
     * @param loginAct          用户账号
     * @param password          用户密码
     * @param preferredLanguage 用户的语言偏好（"en", "zh", "ja"）
     */
    @Async
    public void sendLocalizedTemplateEmail(String to, String loginAct, String password, String preferredLanguage) {
        try {
            // 根据语言偏好选择Locale
            Locale locale;
            switch (preferredLanguage.toLowerCase()) {
                case "zh":
                    locale = Locale.SIMPLIFIED_CHINESE;
                    break;
                case "ja":
                    locale = Locale.JAPANESE;
                    break;
                case "en":
                default:
                    locale = Locale.ENGLISH;
                    break;
            }

            // 创建Thymeleaf上下文
            Context context = new Context(locale);
            context.setVariable("loginAct", loginAct);
            context.setVariable("password", password);

            // 处理模板
            String htmlContent = templateEngine.process("welcome_email", context);

            // 创建MimeMessage
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            // 设置发件人邮箱
            helper.setFrom("optimanage@foxmail.com");
            // 设置收件人邮箱
            helper.setTo(to);
            // 设置邮件主题，本地化
            helper.setSubject(messageSource.getMessage("email.subject", null, locale));
            // 设置邮件正文，第二个参数设为true表示邮件内容包含HTML
            helper.setText(htmlContent, true);

            // 发送邮件
            mailSender.send(mimeMessage);
            System.out.println("本地化模板邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("本地化模板邮件发送失败");
        }
    }
}
