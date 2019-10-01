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

  public Integer selectSomeUserId() {
    db.DbHelperStart();

    Integer userListSize = db.usernames.size();
    Integer rnd = (int)(Math.random() * (userListSize  + 2)) + 1; //Max= size , Min = 1
    //(int)(Math.random() * ((max - min) + 1)) + min
    System.out.println("RANDOM= " + rnd + " SIZE= " + userListSize);

    String userName = selectUserName(rnd);

    click(By.linkText(userName));
    return rnd;
  }

  public String selectUserName(Integer id){
    return db.usernames.get(id);
  }

  public String selectUserPassword(Integer id){
    return db.passwords.get(id);
  }

  public String selectUserEmail(Integer id){
    return db.emails.get(id);
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
