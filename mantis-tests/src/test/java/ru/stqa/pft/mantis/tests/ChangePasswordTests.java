package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
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
            UserData userData = app.db().users().iterator().next();
            String email = userData.getEmail();
            String user = userData.getUsername();
            String password = "root";
            String newPassword = "newPassword";
            String admin = "administrator";
            Integer userId = userData.getId();
            app.change().init(admin, password);
            app.newSession().login(password, admin);
            app.change().resetPass(userId);
            List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.change().changePass(confirmationLink, newPassword);
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

