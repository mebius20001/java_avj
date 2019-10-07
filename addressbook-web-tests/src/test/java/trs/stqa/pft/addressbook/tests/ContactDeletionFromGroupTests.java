package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

public class ContactDeletionFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){

    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Petrov")
              .withAddress("21 E Mossovet str").withHomePhone("123456789").withEmail("abc@job.com"), true);
    }

    if (app.db().groups().size() == 0){
      app.group().groupPage();
      app.group().createGroup(new GroupData().withName("test_group").withHeader("test2").withFooter("test1"));
    }
    app.group().groupPage();
  }


  @Test(enabled = true)
  public void testContactDeletionFromGroupN() {

    app.contact().HomePage();

    Contacts someContacts = app.db().contacts();
    Groups someGroups = app.db().groups();
    Boolean contactRemoved = false;

    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String groupName = String.format("groupName%s", now);

    search:
    for (GroupData selectedGroup : someGroups) {
      for (ContactData selectedContact : someContacts) {
        if (app.contact().isContactInGroup(selectedContact, selectedGroup)) {
          Groups before = selectedContact.getGroups();
          app.contact().removeFromGroup(selectedContact.getId(),selectedGroup.getId(),selectedGroup.getName());
          Groups after = selectedContact.getGroups();
          // Assert.assertTrue(before.size() == after.size() + 1 );
          System.out.println("Contact removed from the Group !!");
          contactRemoved = true;
          break search;
        }
      }
    }

    if (!contactRemoved){
      app.contact().HomePage();
      app.contact().create(new ContactData().withFirstname(user).withMiddlename("Ivanovich").withLastname("Petrov")
              .withAddress("21 E Mossovet str").withHomePhone("123456789")
              .withEmail("abc@job.com"), true);

      Integer newId = someContacts.iterator().next().withFirstname(user).getId();
      String newGroupName = someGroups.iterator().next().getName();
      Integer newGroupId = someGroups.iterator().next().getId();

      Groups before = app.db().contacts().iterator().next().withFirstname(user).getGroups();
      app.contact().addToGroup(newId, newGroupId, newGroupName);
      app.contact().removeFromGroup(newId, newGroupId, newGroupName);
      Groups after = app.db().contacts().iterator().next().withFirstname(user).getGroups();
      //Assert.assertEquals(before.size(),after.size()+1);
    }
  }

}
