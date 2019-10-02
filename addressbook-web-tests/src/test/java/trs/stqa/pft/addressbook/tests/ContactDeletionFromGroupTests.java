package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionFromGroupTests extends TestBase {

  @Test
  public void testContactDeletionFromGroup()  {

    app.contact().HomePage();

    ContactData selectedContact =  app.db().contacts().iterator().next();
    GroupData selectedGroup = app.db().groups().iterator().next();

    selectedContact.getGroups();
    selectedGroup.getContacts();

    System.out.println("groupName= " + selectedGroup.getName());
    System.out.println("SelectedContact ID = " + selectedContact.getId());


   app.contact().selectGroupFromDropDownMenu(selectedGroup.getName());

   app.contact().selectContactById(selectedContact.getId());

   app.contact().removeSelectedContactFromGroup();

    app.contact().switchToAllGroupsDropDownMenu();
    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");

  //  if (someGroups.size() == 0) {
   //   addNewGroup("additionalGroup");  }
  }

  public void selectGroupFromDropDownGroupMenu(GroupData groupData){
   // new Select(wd.findElement(By.name("group"))).selectByVisibleText("test 0");
   // wd.findElement(By.xpath("//option[@value='93']")).click();
    //new Select(wd.findElement(By.name("group"))).selectByVisibleText(String.format("%s", groupData.getName()));
    //wd.findElement(By.cssSelector(String.format("select[name=\"group\"] > option[value=\"%s\"]", groupData.getId()))).click();
  }

  public void clickToDropDownGroupMenu(){
   // wd.findElement(By.cssSelector(String.format("select[name=\"group\"] > option[value=\"%s\"]", group.getId()))).click();
    //option[value='93']
    ///driver.findElement(By.name("group")).click();
  }




}
