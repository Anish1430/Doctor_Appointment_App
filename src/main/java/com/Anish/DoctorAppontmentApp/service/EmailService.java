package com.Anish.DoctorAppontmentApp.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    public static final String EMAIL_USERNAME = "anish.kumar20071998@gmail.com";
    public static final String EMAIL_PASSWORD = "vpxw tzhn dpkg uwtl";

    public static boolean sendMail(String toEmail, String subject, String body){
        Properties sysproperties = new Properties();

        sysproperties.put("mail.smtp.host", "smtp.gmail.com");  // SMTP SERVER
        sysproperties.put("mail.smtp.port", "465");  // SERVER PORT
        sysproperties.put("mail.smtp.ssl.enable", "true");  // SSL - Server Socket Layer - true or false (predicate value)
        sysproperties.put("mail.smtp.auth", "true");  // Authentication - auth

        Session session = Session.getInstance(sysproperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });
        try {
            MimeMessage mailMessage = new MimeMessage(session);
            mailMessage.setFrom(new InternetAddress(EMAIL_USERNAME));
            mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            mailMessage.setSubject(subject);
            mailMessage.setText(body);
            Transport.send(mailMessage);
            System.out.println("OTP sent Successfully to " + toEmail);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
