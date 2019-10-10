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


  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      contactId = 0;
    } else {
      System.out.println("app.db().groups().size()++++++++++++++++++++++++++++++++++++++++ " + app.db().groups().size());
      if (app.db().groups().size() == 0) {
        groupId = 0;
      } else {
        app.contact().HomePage();
        Contacts allContacts = app.db().contacts();
        Groups allGroups = app.db().groups();

        stopCycle:
        for (ContactData selectedContact : allContacts) {

          Groups groupsFromContact = selectedContact.getGroups();

          System.out.println("ContactName = " + selectedContact.getFirstname() + "  groupsFromContact.size()------------" + groupsFromContact.size());

          if (groupsFromContact.size() > 0) {

            System.out.println("groupsFromContact.size() > 0 ++++++++++++++++++++");

            GroupData groupFromContact = groupsFromContact.iterator().next();

            contactId = selectedContact.getId();
            groupId = groupFromContact.getId();
            groupName = groupFromContact.getName();

            break stopCycle;
          }
        }
      }
    }
    System.out.println(contactId);
    System.out.println(groupId);
    System.out.println(groupName);
  }

  @Test(enabled = true)
  public void testContactDeletionFromGroup() {

    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String group = String.format("group%s", now);

    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();

    if (groupId == 0 && allGroups.size() == 0) {
      app.group().groupPage();
      app.group().createGroup(new GroupData().withName(group).withHeader("test2").withFooter("test1"));
      groupName = group;
      groupId = allGroups.iterator().next().withName(group).getId();

    } else {

      if (contactId == 0 && allContacts.size() == 0) {
        app.contact().HomePage();
        app.contact().create(new ContactData().withFirstname(user).withMiddlename("Ivanovich").withLastname("Petrov")
                .withAddress("21 E Mossovet str").withHomePhone("123456789")
                .withEmail("abc@job.com"), true);
        contactId = allContacts.iterator().next().withFirstname(user).getId();
      } else {

        ContactData selectNewContact = allContacts.iterator().next();
        GroupData selectedNewGroup = allGroups.iterator().next();
        contactId = selectNewContact.getId();
        groupId = selectedNewGroup.getId();
        groupName = selectedNewGroup.getName();

        app.contact().addToGroup(contactId, groupId, groupName);
      }

      Groups before = app.db().contacts().iterator().next().withFirstname(user).getGroups();
      System.out.println("Before = " + before);

      app.contact().removeFromGroup(contactId, groupId, groupName);
      System.out.println("Contact removed from group > " + groupName);

      Groups after = app.db().contacts().iterator().next().withFirstname(user).getGroups();
      System.out.println("AFTER = " + after);

      GroupData deletedContactGroups = allGroups.iterator().next().withId(groupId);
      assertThat(after, equalTo(before.without(deletedContactGroups)));
    }

  }

}
