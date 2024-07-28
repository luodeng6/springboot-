package com.book.demo.Service.ServiceIMP;

import com.book.demo.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceIMPTest {
    @Autowired
    EmailService emailService;

    @Test
    void sendEmailTest() {
        System.out.println(emailService.sendEmail("3407708637@qq.com", "这是一封测试邮件", "测试内容"));
    }
}