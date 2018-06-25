package com.codesignet.consalto.contactus.data_access_layer;

import android.util.Log;

import com.codesignet.consalto.contactus.pojo.ContactUs;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Aya on 02/06/2018.
 */

public class MailService implements Serializable {

    public void sendmail(ContactUs contactUs) {
        Log.i("obj", contactUs.getEmail());
        final String to = "medicalinsurance.mobile@gmail.com";
        final String from = "ayahilal16@gmail.com";
        //  final String from = contactUs.getEmail().toString();
        // final String password ="medical123456";
        final String password = "aya1621995hilal";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        //get Session
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(from,password);
//                    }
//                });
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                from, password);// Specify the Username and the PassWord
                    }
                });
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, password);
//            }
//        });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Question");
            message.setText("Hello," + contactUs.getMsg() + "my name is" + contactUs.getFirstName() + contactUs.getLastName() + "\n" + contactUs.getPhone());
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}