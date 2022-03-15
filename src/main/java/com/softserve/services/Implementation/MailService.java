package com.softserve.services.Implementation;

import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;

public class MailService {
    public void sendEmail(String[] recipients,String subject , String msg) throws MessagingException {
        Session newSession = null;
        MimeMessage mimeMessage = null;
        // тута пошту
        String fromUser = "vovkunm@gmail.com";
        //тута пароль
        String fromUserPassword = "";
        String emailHost = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
        mimeMessage = new MimeMessage(newSession);
        for (int i = 0; i < recipients.length; i++) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients[i]));
        }
        mimeMessage.setSubject(subject);
        mimeMessage.setText(msg);
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }
}
