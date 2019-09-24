package trs.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.Contacts;
import trs.stqa.pft.addressbook.model.Groups;

public class ContactDeletionFromGroupTests extends TestBase {
  public WebDriver wd;
  @Test
  public void testContactDeletionFromGroup()  {

    app.contact().HomePage();

    clickToDropDownGroupMenu();
    selectGroupFromDropDownGroupMenu();
    removeSelectedContactFromGroup();
   // wd.findElement(By.linkText("home")).click();
    clickToDropDownGroupMenu();
    switchToAllGroupsDropDownMenu();
    // ********************************

    Contacts someContacts = app.db().contacts();
    Groups someGroups = app.db().groups();

    //if (someGroups.size() == 0) { addNewGroup("additionalGroup");  }
  }

  public void selectGroupFromDropDownGroupMenu(){
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("test 0");
    wd.findElement(By.xpath("//option[@value='93']")).click();
  }

  public void clickToDropDownGroupMenu(){
    wd.findElement(By.name("group")).click();
    ///driver.findElement(By.name("group")).click();
  }

  public void removeSelectedContactFromGroup(){
    wd.findElement(By.name("selected[]")).click();// выбрать контакт
    wd.findElement(By.name("remove")).click();
  }

  public void switchToAllGroupsDropDownMenu(){
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    wd.findElement(By.xpath("//form[@id='right']/select/option[2]")).click();
  }
}
