package trs.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroupTests extends TestBase {
  public SessionFactory sessionFactory;

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

  @Test(enabled = false)
  public void testAddContactToTheGroupN() {

    ContactData contact = app.db().contacts().iterator().next();
    Groups someGroups2 = app.db().groups();

   // boolean bb = someGroups2.removeAll(app.db().contact.getGroups());       //   without(contact.getGroups()).stream().map((g)->g.)

    System.out.println("Contact.getGroups =" + contact.getGroups());
    System.out.println("Список групп из БД оригинальный = " + app.db().groups());
    System.out.println("Список групп без контактов из группы =" + someGroups2);









    app.contact().HomePage();



    Contacts someContacts = app.db().contacts();

    Groups someGroups = app.db().groups();
    Boolean contactAdded = false;

    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);

    search:
    for (GroupData selectedGroup : someGroups) {
      for (ContactData selectedContact : someContacts) {
        if (!app.contact().isContactInGroup(selectedContact, selectedGroup)) {
          Groups before = selectedContact.getGroups();
          app.contact().addToGroup(selectedContact.getId(), selectedGroup.getId(), selectedGroup.getName());

          Assert.assertTrue(!app.contact().isContactInGroup(selectedContact, selectedGroup));
          Groups after = selectedContact.getGroups();
         // Assert.assertTrue(before.size() == after.size() -1 );
          System.out.println("Contact added to the Group !!");
          contactAdded = true;
          break search;
        }
      }
    }

    if (!contactAdded){
      app.contact().HomePage();
      app.contact().create(new ContactData().withFirstname(user).withMiddlename("Ivanovich").withLastname("Petrov")
              .withAddress("21 E Mossovet str").withHomePhone("123456789")
              .withEmail("abc@job.com"), true);

      ContactData additionalContact = someContacts.iterator().next().withFirstname(user);
      Integer newId = additionalContact.getId();
      Groups before = additionalContact.getGroups();

      app.group().groupPage();
      app.contact().addToGroup(newId, someGroups.iterator().next().getId(), someGroups.iterator().next().getName());

      Groups after = app.db().contacts().iterator().next().withFirstname(user).getGroups();
      //Assert.assertEquals(before.size(),after.size()-1);
      System.out.println("Create new contact");
    }
  }


}
