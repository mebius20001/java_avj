package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase  {

  public GroupHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"),groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup() {
    click(By.name("selected[]"));
  }

  //Contacts section *********************

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

}
