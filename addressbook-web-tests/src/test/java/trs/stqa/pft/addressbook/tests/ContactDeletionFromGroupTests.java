package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTests extends TestBase {

  public Integer contactId = 0;
  public Integer groupId = 0;
  public String groupName = "";
  public Boolean noContacts = true;
  public boolean noGroups = true;


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      noGroups = true;
    } else {
      noGroups = false;

      if (app.db().contacts().size() == 0) {
        noContacts = true;
      } else {
        noContacts = false;
      }
    }

    app.contact().HomePage();

    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();

    stopCycle:
    for (ContactData selectedContact : allContacts) {

      Groups groupsFromContact = selectedContact.getGroups();

      System.out.println("ContactName = " + selectedContact.getFirstname() + "  groupsFromContact.size()------------" + groupsFromContact.size());

      if (groupsFromContact.size() > 0) {

        System.out.println("groupsFromContact.size() > 0 ");

        GroupData groupFromContact = groupsFromContact.iterator().next();

        contactId = selectedContact.getId();

        groupId = groupFromContact.getId();
        groupName = groupFromContact.getName();
        noContacts = false;
        noGroups = false;

        break stopCycle;
      }
    }
  }


  @Test(enabled = true)
  public void testContactDeletionFromGroup() {

    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String group = String.format("group%s", now);

    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();

    if (noGroups) {
      app.group().groupPage();
      app.group().createGroup(new GroupData().withName(group).withHeader("test2").withFooter("test1"));
      System.out.println("new group created");
    }
    if (noContacts) {

      app.contact().HomePage();
      app.contact().create(new ContactData().withFirstname(user).withMiddlename("Ivanovich").withLastname("Petrov")
              .withAddress("21 E Mossovet str").withHomePhone("123456789")
              .withEmail("abc@job.com"), true);

      System.out.println("new contact created");
    }

    app.contact().HomePage();

    if (contactId == 0 && groupId == 0) {

      ContactData someContact = app.db().contacts().iterator().next();
      contactId = someContact.getId();
      GroupData someGroup = app.db().groups().iterator().next();
      groupId = someGroup.getId();
      groupName = someGroup.getName();

      Groups before = someContact.getGroups();

      app.contact().addToGroup(contactId, groupId, groupName); //добавляем контакт в группу

      Groups after = someContact.getGroups();

      assertThat(before, equalTo(after.without(someGroup)));

    }

    ContactData contactToDelete = app.contact().findWithId(contactId, app.db().contacts());
    GroupData groupToDelete = app.group().findWithId(groupId, app.db().groups());

    Groups before = contactToDelete.getGroups();

    app.contact().removeFromGroup(contactId, groupId, groupName);
    System.out.println("remove from group -------------------------------------------------");

    Groups after = contactToDelete.getGroups();

    assertThat(before, equalTo(after.withAdded(groupToDelete)));
  }
}
