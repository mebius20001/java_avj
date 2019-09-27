package trs.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import trs.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class changeUserPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test(enabled = false)
  public void testRegistration() throws IOException {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String user = String.format("user%s", now);
    String administrator = "administrator";
    String adminPassword = "root";
    String password = "password";
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password, user);
    assertTrue(app.newSession().login(user, password));
  }

  @Test(enabled = true)
  public void testRefreshUserPassword() throws IOException {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String user = String.format("user%s", now);
    String administrator = "administrator";
    String adminPassword = "root";
    String password = "password";

    app.registration().startAsUser(administrator, adminPassword);
    app.registration().toUsersPage();
    app.registration().selectSomeUser();
    app.registration().resetSelectedUserPassword();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password, user);

    assertTrue(app.registration().startAsUser(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {

    // MailMessage mailMessage = mailMessages.stream().filter((m)-> m.to.equals(email)).findFirst().get();

    MailMessage mailMessage = mailMessages.iterator().next();// new variant for 1 letter only

    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailserver() {
    app.mail().stop();
  }

}
