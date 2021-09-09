package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

    public class ChangePasswordTests extends TestBase {

        @BeforeMethod
        public void startMailServer() {
            app.mail().start();
        }

        @Test
        public void testChangePassword() throws IOException, MessagingException {
            String email = "user%s@localhost";
            String user = "user2";
            Integer userId = app.db().userId(user);
            String password = "root";
            String newPassword = "newPassword";
            String admin = "administrator";
            app.change().init(admin, password);
            app.newSession().login(password, admin);
            app.change().start(userId);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 60000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.change().finish(confirmationLink, newPassword);
            assertTrue(app.newSession().login(newPassword, user));
        }

        private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
            MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
            VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
            return regex.getText(mailMessage.text);
        }

        @AfterMethod(alwaysRun = true)
        public void stopMailServer() {
            app.mail().stop();
        }
    }

