package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager2 {

  public FirefoxDriver wd;


  private SessionHelper2 sessionHelper;
  private NavigationHelper2 navigationHelper;
  private GroupHelper2 groupHelper;

  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper2(wd);
    navigationHelper = new NavigationHelper2(wd);
    sessionHelper = new SessionHelper2(wd);
    sessionHelper.login("admin", "secret");
  }

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void stop() {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }

  public GroupHelper2 getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper2 getNavigationHelper() {
    return navigationHelper;
  }

}
