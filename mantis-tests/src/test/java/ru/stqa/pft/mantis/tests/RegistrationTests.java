package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationTests extends TestBase {

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
        app.registration().finish().login(user, pwd);
        assertTrue(app.newSession().login(user, pwd));
    }

    @AfterMethod
    public void stopMailServer() {
        app.mail().stop();
    }
}
