package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionFromGroupTests extends TestBase {

  @Test(enabled = true)
  public void testContactDeletionFromGroup2() {

    app.contact().HomePage();

    if (app.db().groups().size() == 0) {
      app.group().createGroup(new GroupData().withName("test_group").withHeader("test2").withFooter("test1"));
    }
    GroupData selectedGroup = app.db().groups().iterator().next();
    ContactData selectedContact = app.db().contacts().iterator().next();

    if (selectedGroup.getContacts().size() == 0) {
      app.contact().addToGroup(selectedContact.getId(), selectedGroup.getId(), selectedGroup.getName());
    }
    app.contact().removeFromGroup(selectedGroup.getName(), selectedContact.getId());
  }




  @Test(enabled = false)
  public void testContactDeletionFromGroup() {

    app.contact().HomePage();

    ContactData selectedContact = app.db().contacts().iterator().next();
    GroupData selectedGroup = app.db().groups().iterator().next();

    selectedContact.getGroups();
    selectedGroup.getContacts();

    // System.out.println("groupName= " + selectedGroup.getName());
    // System.out.println("SelectedContact ID = " + selectedContact.getId());


    app.contact().selectGroupFromDropDownMenu(selectedGroup.getName());

    app.contact().selectContactById(selectedContact.getId()); ///нет контакта в группе

    app.contact().removeSelectedContactFromGroup();

    app.contact().switchToAllGroupsDropDownMenu();
    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");

    //  if (someGroups.size() == 0) {
    //   addNewGroup("additionalGroup");  }
  }


}
