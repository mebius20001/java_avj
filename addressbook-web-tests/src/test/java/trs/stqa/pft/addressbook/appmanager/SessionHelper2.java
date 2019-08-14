package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper2 extends HelperBase2 {


  public SessionHelper2(FirefoxDriver wd) {

    super(wd);
  }

  public void login(String username, String password) {

    type(By.name("user"),username);
    type(By.name("pass"), password);
    click(By.xpath("//input[@value='Login']"));

  }


}
