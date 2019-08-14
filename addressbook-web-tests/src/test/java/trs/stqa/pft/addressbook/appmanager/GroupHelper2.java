package trs.stqa.pft.addressbook.appmanager;

import trs.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupHelper2 extends HelperBase2 {

  public GroupHelper2(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData groupData) {
    type(By.name("firstname"), groupData.getFirstname());
    type(By.name("middlename"), groupData.getMiddlename());
    type(By.name("lastname"), groupData.getLastname());
    type(By.name("address"), groupData.getAddress());
    type(By.name("home"), groupData.getHomePhone());
    click(By.name("email"));
    type(By.name("email"), groupData.getEmail());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }
}
