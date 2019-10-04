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
  public void ensurePreconditions(){

    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Petrov")
              .withAddress("21 E Mossovet str").withHomePhone("123456789").withEmail("abc@job.com"), true);
    }

    if (app.db().groups().size() == 0){
      app.group().groupPage();
      app.group().createGroup(new GroupData().withName("test_group").withHeader("test2").withFooter("test1"));
    }
    app.group().groupPage();//FFF

  }


  @Test
  public void testAddContactToTheGroup() {

    app.contact().HomePage();

    Contacts someContacts = app.db().contacts();
    Groups someGroups = app.db().groups();

    if (someGroups.size() == 0) {
      app.group().createGroup(new GroupData().withName("test_group").withHeader("test2").withFooter("test1"));
      //addNewGroup("additionalGroup");
    }

    search:
    for (ContactData selectedContact : someContacts) {

      for (GroupData selectedGroup : someGroups) {

        if (!app.contact().isContactInGroup(selectedContact, selectedGroup)) {

          app.contact().addToGroup(selectedContact.getId(), selectedGroup.getId(), selectedGroup.getName());

          Assert.assertTrue(!app.contact().isContactInGroup(selectedContact, selectedGroup));
          System.out.println("Контакт добавлен");

          break search;

        }
      }
    }
  }
/*
  public void addNewGroup(String groupName) {
    app.contact().HomePage();
    Groups groups = app.db().groups();

    app.group().groupPage(); //???
    app.group().createGroup(new GroupData().withName("test_group").withHeader("test2").withFooter("test1"));

  }
*/
}