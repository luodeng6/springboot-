package com.book.demo.Service;

public interface EmailService {
     boolean sendEmail(String toEmail, String text, String title) ;
      String generateVerificationCode() ;
}
