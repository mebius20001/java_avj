package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import trs.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void submitContactCreation() {

    click(By.xpath("(//input[@name='submit'])[2]"));

  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
   // type(By.name("group"), contactData.getGroup());

   if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("test_group")));
    }

  }

  public void initContactCreation() {
      click(By.linkText("add new"));

  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
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

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
//**********************************************
  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData("Ivan",  "Ivanovich", "Petrov", "21 E Mossovet str", "123456789", "abc@job.com", "test_group");
      contacts.add(contact);
    }
    return contacts;
  }
}
