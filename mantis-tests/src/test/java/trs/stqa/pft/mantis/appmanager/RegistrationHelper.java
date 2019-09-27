package trs.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import trs.stqa.pft.mantis.tests.DbHelperM;

public class RegistrationHelper extends HelperBase {
  DbHelperM db = new DbHelperM();

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {

    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public boolean startAsUser(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Login']"));

    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));

    return true;
  }

  public void toUsersPage() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void selectSomeUser() {
    db.DbHelperStart();
    String userName = db.usernames.get(3);
    click(By.linkText(userName));
  }

  public void resetSelectedUserPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finish(String confirmationLink, String password, String username) {
    wd.get(confirmationLink);
    type(By.name("realname"), username);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

}
