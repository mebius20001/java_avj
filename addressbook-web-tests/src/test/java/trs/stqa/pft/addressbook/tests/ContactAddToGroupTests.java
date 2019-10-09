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

public class ContactAddToGroupTests extends TestBase {
  public SessionFactory sessionFactory;
  public Integer contactId = null;
  public Integer groupId = null;
  public String groupName = null;


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

  /*
    @BeforeMethod
    public void ensurePreconditions() {

      if (app.db().contacts().size() == 0) {
        app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Petrov")
                .withAddress("21 E Mossovet str").withHomePhone("123456789").withEmail("abc@job.com"), true);
      }

      if (app.db().groups().size() == 0) {
        app.group().groupPage();
        app.group().createGroup(new GroupData().withName("test_group").withHeader("test2").withFooter("test1"));
      }
      app.group().groupPage();
    }

   */
  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      contactId = 0;
    } else {
      if (app.db().groups().size() == 0) {
        groupId = 0;
      } else {
        app.contact().HomePage();

        Contacts allContacts = app.db().contacts();
        Groups allGroups = app.db().groups();

        stopCycle:
        for (ContactData selectedContact : allContacts) {
          Groups withoutGroups = allGroups;

          boolean wGroup = withoutGroups.removeAll(selectedContact.getGroups());

          System.out.println("withoutGroups.size()= " + withoutGroups.size() );
          if (withoutGroups.size() != 0) {
            GroupData nextGroup = withoutGroups.iterator().next();
            contactId = selectedContact.getId();
            groupId = nextGroup.getId();
            groupName = nextGroup.getName();

            break stopCycle;
          }
        }
      }
    }

  }

  //***************************************************************************************************

  @Test(enabled = true)
  public void testAddContactToTheGroupN() {

    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String group = String.format("group%s", now);

    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();

    if (groupId == 0) {
      app.group().groupPage();
      app.group().createGroup(new GroupData().withName(group).withHeader("test2").withFooter("test1"));
      groupName = group;
      groupId = allGroups.iterator().next().withName(group).getId();

    } else {
      if (contactId == 0) {

        app.contact().HomePage();
        app.contact().create(new ContactData().withFirstname(user).withMiddlename("Ivanovich").withLastname("Petrov")
                .withAddress("21 E Mossovet str").withHomePhone("123456789")
                .withEmail("abc@job.com"), true);
        contactId = allContacts.iterator().next().withFirstname(user).getId();

      } else {
        app.contact().addToGroup(contactId, groupId, groupName);
        // добавить контакт в группу с известными данными
      }
    }
  }


}
