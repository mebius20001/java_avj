package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper2 extends HelperBase2 {

    public NavigationHelper2(FirefoxDriver wd) {
    super(wd);
  }

  public void goToHomePage() {
   click(By.linkText("home"));
  }
}
