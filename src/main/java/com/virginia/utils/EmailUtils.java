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

// Send emails using qq mailbox

@Component
public class EmailUtils {
    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private MessageSource messageSource;

    /**
     *Send localized HTML emails based on the user's language preference, using Thymeleaf templates
     *
     * @param to recipient email
     * @param loginAct user account
     * @param password user password
     * @param preferredLanguage The user's language preference ("en", "zh", "ja")
     */
    @Async
    public void sendLocalizedTemplateEmail(String to, String loginAct, String password, String preferredLanguage) {
        try {
            // Select locale based on language preference
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

            // Create thymeleaf context
            Context context = new Context(locale);
            context.setVariable("loginAct", loginAct);
            context.setVariable("password", password);

            // Processing templates
            String htmlContent = templateEngine.process("welcome_email", context);

            // Create mime message
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            // Set sender email
            helper.setFrom("optimanage@foxmail.com");
            // Set recipient email
            helper.setTo(to);
            // Set email subject, localize
            helper.setSubject(messageSource.getMessage("email.subject", null, locale));
            // Set the email body. The second parameter is set to true to indicate that the email content contains html.
            helper.setText(htmlContent, true);

            // Send email
            mailSender.send(mimeMessage);
            System.out.println("Localized template email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send localized template email");
        }
    }
}
