package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
  }

  public void modify(ContactData contact) {
    initContactModification( contact.getId() );
    fillContactForm(contact ,true);
    submitContactModification();
    HomePage();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }

  public void initContactModification(int index) {
    click(By.xpath("//a[@href='edit.php?id=" + index + "']"));
  }

  public void initContactModificationById(int id) {

    //click(By.xpath("//a[@href='edit.php?id=" + index + "']"));
    wd.findElement(By.cssSelector(String.format("a[@href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    closeAlertWindow();
    HomePage();
  }

  public void HomePage() {
    if (isElementPresent(By.id("maintable")) ) {
      return;
    }
    click(By.linkText("home"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());

    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

   if (!creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("test_group")));
    }
  }

  public void initContactCreation() {
      click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }


  public boolean isThereAContact() {

    return isElementPresent(By.name("selected[]"));
  }

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }
//**********************************************

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.cssSelector("td"));

      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();

     // String AllEmails = cells.get(4).getText();

      String[] phones = cells.get(5).getText().split("\n");
      System.out.println(phones.length);


      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname( firstname).withLastname(lastname).withAddress(address)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));

    }
    return contacts;
  }


  public ContactData infoFromEditform(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return (ContactData) new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);

  }


}
