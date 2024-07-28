package com.book.demo.Service.ServiceIMP;

import com.book.demo.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceIMP implements EmailService {

    @Autowired
    private JavaMailSender mailSender; // 自动注入JavaMailSender对象

    @Value("${spring.mail.username}")
    private String fromEmail; // 通过配置文件注入发送者地址

    public boolean sendEmail(String toEmail, String text, String title) {
        System.out.println(fromEmail + " sendEmail to " + toEmail + " with text " + text + " and title is" + title);
        try {
            // 创建一个SimpleMailMessage对象
            SimpleMailMessage message = new SimpleMailMessage();

            // 设置发送者地址
            message.setFrom(fromEmail);

            // 设置接收者地址
            message.setTo(toEmail);

            // 设置邮件主题
            message.setSubject(title);

            // 设置邮件正文
            message.setText(text);

            // 发送邮件
            mailSender.send(message);

            // 返回成功状态
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // 如果出现异常，返回失败状态
            return false;
        }
    }


    // 生成6位随机验证码
   public   String generateVerificationCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
