package com.PPU.DB.MailService;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 17.05.14
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public class MailSender {

    public static void sendMessage(String mailAdress, String subject, String text)
    {
        try
        {
            Properties mailProps=new Properties();
            //mailProps.put("mail.smtp.host","smtp.yandex.ru");
            //mailProps.put("mail.smtp.auth", "true");

            Session mailSession=Session.getInstance(mailProps, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("borrodach3@gmail.com", "rijndaelruls");
                }
            });

            MimeMessage message=new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("borrodach3@gmail.com"));
            String[] emails={mailAdress};
            InternetAddress dests[]=new InternetAddress[emails.length];

            for(int i=0; i<emails.length; i++){
                dests[i]=new InternetAddress(emails[i].trim().toLowerCase());
            }
            message.setRecipients(Message.RecipientType.TO, dests);
            message.setSubject(subject);
            Multipart mp=new MimeMultipart();
            MimeBodyPart mbp1=new MimeBodyPart();
            mbp1.setText(text);
            mp.addBodyPart(mbp1);
            message.setContent(mp);
            message.setSentDate(new java.util.Date());
            Transport.send(message);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
