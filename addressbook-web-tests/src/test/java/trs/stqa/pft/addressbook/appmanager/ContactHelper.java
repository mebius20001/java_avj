package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import trs.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
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

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.cssSelector("img[alt=\"Edit\"]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}
