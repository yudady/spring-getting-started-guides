package io08;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootTest
@EnableConfigurationProperties({MailProperties.class})
public class SpringEmailTest {

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    public void test01() throws MessagingException {
        System.out.println("mailSender = " + javaMailSender);
    }
    // @BeforeAll
    // public static void setup() {
    //     mailSender = new JavaMailSenderImpl();
    //     mailSender.setHost("127.0.0.1");
    //     mailSender.setPort(2525);
    //     mailSender.setUsername("your_email");
    //     mailSender.setPassword("your_password");
    //
    //     Properties properties = new Properties();
    //     properties.setProperty("mail.smtp.auth", "false");
    //     properties.setProperty("mail.smtp.starttls.enable", "false");
    //
    //     mailSender.setJavaMailProperties(properties);
    // }

    @Test
    public void testSendSimpleEmail() throws Exception {
        String from = "codejava.net@gmail.com";
        String to = "hainatu@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("My 2nd email from Spring Boot");
        message.setText("Hello guys, this is my 2nd email sent from Spring Boot.");

        javaMailSender.send(message);
    }

    @Test
    public void testSendHTMLEmail() throws MessagingException {
        String from = "codejava.net@gmail.com";
        String to = "hainatu@gmail.com";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("This is an HTML email");
        helper.setFrom(from);
        helper.setTo(to);

        helper.setText("<b>Hey guys</b>,<br><i>Welcome to my home</i>", true);

        javaMailSender.send(message);
    }

    @Test
    public void testSendEmailWithAttachment() throws MessagingException, URISyntaxException {
        String from = "codejava.net@gmail.com";
        String to = "hainatu@gmail.com";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Here's your e-book");
        helper.setFrom(from);
        helper.setTo(to);

        helper.setText("<b>Dear master</b>,<br><i>Please find the book attached.</i>", true);

        FileSystemResource file = new FileSystemResource(Paths.get(this.getClass().getClassLoader().getResource("defer.txt").toURI()).toFile());
        helper.addAttachment("FreelanceSuccess.pdf", file);

        javaMailSender.send(message);
    }

    @Test
    public void testSendEmailWithInlineImage() throws Exception {
        String from = "codejava.net@gmail.com";
        String to = "hainatu@gmail.com";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Here's your pic");
        helper.setFrom(from);
        helper.setTo(to);

        helper.setText("<b>Dear guru</b>,<br><i>Please look at this nice picture:.</i><br><img src='cid:image001'/><br><b>Best Regards</b>", true);

        FileSystemResource resource = new FileSystemResource(Paths.get(this.getClass().getClassLoader().getResource("defer.txt").toURI()).toFile());
        helper.addInline("image001", resource);

        javaMailSender.send(message);
    }
}
