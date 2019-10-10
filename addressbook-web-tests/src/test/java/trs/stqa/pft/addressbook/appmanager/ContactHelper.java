package trs.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

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

  public void createInGroup(String groupName, ContactData contact, boolean creation) {
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

  public void select(ContactData contact) {
    selectContactById(contact.getId());
    System.out.println(contact.getId());
   // deleteSelectedContacts();
   // closeAlertWindow();
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
    //attach(By.name("photo"), contactData.getPhoto()); //!!! It was a Problem !!!

   if (!creation){
     Assert.assertTrue(contactData.getGroups().size() == 1);
     new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
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

      String allEmails = cells.get(4).getText();
      String allphones = cells.get(5).getText();

     // String[] phones = cells.get(5).getText().split("\n");

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname( firstname).withLastname(lastname).withAddress(address)
              .withAllPhones(allphones).withAllEmails(allEmails));

    }
    return contacts;
  }


  public ContactData infoFromEditform(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");

    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return (ContactData) new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);

  }

//***********************
  public void findContactByID(Integer id) {
  //  wd.findElement(By.id(String.valueOf(id))).click();
    String ids = String.valueOf(id);
    //System.out.println(ids);

    wd.findElement(By.id( ids )).click();
  }

  public void initAddToGroup() {
    wd.findElement(By.name("to_group")).click();
  }

  public void selectGroupToAdd(String groupName, Integer GroupID) {

    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
    wd.findElement(By.xpath("(//option[@value=" + GroupID + " ])[2]")).click();
  }

//**********************************
  public void selectGroupToRemove(String groupName, Integer GroupID) {
  HomePage();
  new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
  wd.findElement(By.xpath("(//option[@value=" + GroupID + " ])[2]")).click();
    //closeAlertWindow();
}


  public void selectGroupFromDropDownMenu(String groupName){
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
  }

  public void removeFromButton(){
    wd.findElement(By.name("remove")).click();
  }

  public void switchToAllGroupsDropDownMenu(){
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    wd.findElement(By.xpath("//form[@id='right']/select/option[2]")).click();
  }


  public void addToGroup(Integer contactId, Integer groupId, String groupName){
   //  HomePage(); //????
    findContactByID(contactId);
    initAddToGroup();
    selectGroupToAdd(groupName, groupId);
    addToGroup_Button();
  }

  public void removeFromGroup(Integer contactId, Integer groupId, String groupName){
    selectGroupToRemove(groupName, groupId);
    findContactByID(contactId);
    removeFromButton();
  }


  public void removeFromGroup(String groupName, Integer contactId){
    selectGroupFromDropDownMenu(groupName);
    selectContactById(contactId);
    removeFromButton();
   // switchToAllGroupsDropDownMenu();
  }

  //********************************************************
  public void addToGroup_Button() {
    wd.findElement(By.name("add")).click();
  }

  public void goToGroupPageWithName(String groupName) {
    wd.findElement(By.linkText("group page \"" + groupName + "\"")).click();
    //wd.findElement(By.linkText("group page \"test 0\"")).click();
  }


  public Boolean isContactInGroup(ContactData selectedContact, GroupData selectedGroup) {

    Groups contact2 = selectedContact.getGroups();
    for (GroupData groupFromContact : contact2){
      if (groupFromContact.getId() == selectedGroup.getId()) {

        System.out.println("contact ALREADY in Group!!!!!");
        System.out.println(groupFromContact.getId() + " = " + selectedGroup.getId());
        System.out.println(groupFromContact.getName() + " = " + selectedGroup.getName());
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }



}
