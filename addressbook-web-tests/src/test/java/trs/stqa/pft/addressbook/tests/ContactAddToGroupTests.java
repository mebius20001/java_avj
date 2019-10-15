package trs.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {
  public SessionFactory sessionFactory;
  public Integer contactId = 0;
  public Integer groupId = 0;
  public String groupName = "";
  public Boolean noContacts = true;
  public boolean noGroups = true;


  @BeforeClass
  protected void setUp2() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();//!!!
    } catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      noGroups = true;
    } else {
      noGroups = false;
      if (app.db().contacts().size() == 0) {
        noContacts = true;
      }
    }

    app.contact().HomePage();

    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();

    stopCycle:
    for (ContactData selectedContact : allContacts) {
      Groups withoutGroups = new Groups(allGroups);
      boolean wGroup = withoutGroups.removeAll(selectedContact.getGroups());

      if (withoutGroups.size() != 0) {
        GroupData nextGroup = withoutGroups.iterator().next();
        contactId = selectedContact.getId();
        groupId = nextGroup.getId();
        groupName = nextGroup.getName();
        noContacts = false;
        noGroups = false;

        break stopCycle;
      }
    }


  }

  @Test(enabled = true)
  public void testContactAdditionToGroup() {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String group = String.format("group%s", now);

    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();

    if (noGroups) {
      app.group().groupPage();
      app.group().createGroup(new GroupData().withName(group).withHeader("test2").withFooter("test1"));
      System.out.println("-------------------------------------------------------------------------------------------");
      System.out.println("new group created");
      groupId = app.group().findWithName(group, app.db().groups()).getId();
      groupName = group; //group
    }
    if (contactId == 0 || noContacts) {

      app.contact().HomePage();
      app.contact().create(new ContactData().withFirstname(user).withMiddlename("Ivanovich").withLastname("Petrov")
              .withAddress("21 E Mossovet str").withHomePhone("123456789")
              .withEmail("abc@job.com"), true);
      contactId = app.contact().findWithName(user, app.db().contacts()).getId();

      GroupData somegroup = app.db().groups().iterator().next();
      groupName = somegroup.getName();
      groupId = somegroup.getId();

      System.out.println("-------------------------------------------------------------------------------------------");
      System.out.println("new contact created");
    }

    app.contact().HomePage();

    GroupData additionalGroup = app.group().findWithId(groupId, app.db().groups());
    ContactData additionalContact = app.contact().findWithId(contactId, app.db().contacts());
    System.out.println(additionalContact);

    Groups before = additionalContact.getGroups();

    app.contact().addToGroup(contactId, groupId, groupName); //добавляем контакт в группу

    Groups after = additionalContact.getGroups();

    assertThat(before, equalTo(after.without(additionalGroup)));
  }

}
