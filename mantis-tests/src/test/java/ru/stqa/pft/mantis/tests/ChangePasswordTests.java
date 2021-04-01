package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.apache.tools.mail.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests extends TestBase {

    private Optional<Users> user;

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        HttpSession session = app.newSession();
        app.admin().login("administrator", "root");
        app.admin().manageUsers();
        Users users = app.db().users();
        UserData user = users.iterator().next();
        app.admin().reset(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String conformationLink = app.mail().findConformationLink(mailMessages, user.getEmail());
        String password = "newPassword";
        app.admin().finish(conformationLink, user, password);

        assertTrue(session.login(user.getUsername(), password));
        assertTrue(session.isLoggedInAs(user.getUsername()));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
