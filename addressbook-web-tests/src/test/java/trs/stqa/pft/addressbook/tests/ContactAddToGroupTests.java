package trs.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

import java.util.List;

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

  @Test
  public void testAddContactToTheGroup() {

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated ='0000-00-00'").list();
    for (ContactData contact : result) {
      System.out.println("ContactID = " + contact.getId());
      System.out.println("GetGroups = " + contact.getGroups());
      System.out.println("GetGroups.size = " + contact.getGroups().size());
    }
    session.getTransaction().commit();
    session.close();

    //*************************************


    app.contact().HomePage();

    Contacts someContacts = app.db().contacts();
    Groups someGroups = app.db().groups();

    if (someGroups.size() == 0) {
      addNewGroup("additionalGroup");
    }

    search:
    for (ContactData selectedContact : someContacts) {

      for (GroupData selectedGroup : someGroups) {

        if (!app.contact().isContactInGroup(selectedContact, selectedGroup)) {

          app.contact().findContactByID(selectedContact.getId());
          app.contact().initAddToGroup();

          System.out.println("ID выбранного контакта = " + selectedContact.getId());
          System.out.println("Количество групп для контакта " + selectedContact.getGroups());

          app.contact().selectGroupToAdd(selectedGroup.getName(), selectedGroup.getId());
          app.contact().addToGroup_Button();
          app.contact().HomePage();

          System.out.println("Контакт не в группе. Добавляем.****************");
          Assert.assertTrue(!app.contact().isContactInGroup(selectedContact, selectedGroup));
          System.out.println("Контакт добавлен");

          break search;

        }

      }

    }


  }


  public void addNewGroup(String groupName) {
    app.contact().HomePage();
    Groups groups = app.db().groups();

    app.group().groupPage(); //???
    app.group().createGroup(new GroupData().withName(groupName));

  }

}