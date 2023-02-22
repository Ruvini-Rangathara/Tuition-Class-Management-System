package lk.vidathya.tcms.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    public static void sendMail(String to, String subject, String text) throws MessagingException {

        //String to = "ruvinisubhasinghe200009@gmail.com";
        //String  from = "vidathyainstitute@gmail.com";
        String from = InstituteData.getEmail();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port",587);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("vidathyainstitute@gmail.com","zgmcqnorvbbqnolx");  // have to change some settings in SMTP
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(text);

        Transport.send(mimeMessage);

        System.out.println("Done...");
    }

}
