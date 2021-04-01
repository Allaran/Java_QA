package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.apache.tools.mail.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s@localhost.localdomain", now);
        String email = String.format("user%s", now);
        String name = String.format("user%name", now);
        String pwd = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitMail(1, 10000);
        String registration = app.registration().findConfirmationLink(mailMessages, email);
        app.registration().finish().login(user, pwd);
        assertTrue(app.newSession().login(user, pwd));
    }

    @AfterMethod
    public void stopMailServer() throws MessagingException {
        app.mail().stop();
    }
}
