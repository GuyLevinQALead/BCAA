package Utilities;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class SendReport extends Operations {

    DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public void SendEmailReport() {
        // Recipient's email ID needs to be mentioned.
        String to[] = {"ron.asor@asperii.com", "guy.levin@asperii.com"};

        // Sender's email ID needs to be mentioned
        String from = "ron.asor@asperii.com";

        // Assuming you are sending email from through gmail smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        //it is required
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(readData.GetData("EmailReportUser"), readData.GetData("EmailReportPassword"));
            }

        });
        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            for (String email : to){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }
            // Set Subject: header field
            message.setSubject("Automation Report - " + dateTime.format(now));
            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {
                File f =new File("ExtentReports/extentReport" + dtf.format(localDate) +"Admin.html");
                System.out.println(f);
                attachmentPart.attachFile(f);
                textPart.setText("This is the latest automation Report generated after execution");
                System.out.println("file sent");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {
                e.printStackTrace();
            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
