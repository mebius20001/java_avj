package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }




  public void goToHomePage() {
    if (isElementPresent(By.id("maintable")) ) {
      return;
    }

    click(By.linkText("home"));
  }
}
