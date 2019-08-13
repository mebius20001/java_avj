package contacts.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {

  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }
  //public FirefoxDriver wd;

  public void goToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }
}
