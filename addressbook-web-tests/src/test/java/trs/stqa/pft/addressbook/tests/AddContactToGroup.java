package trs.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class AddContactToGroup extends TestBase {
  public SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp2() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();//!!!
    }
    catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }

  @Test
  public void testAddContactToTheGroup(){

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated ='0000-00-00'" ).list();
    for (ContactData contact : result) {
      System.out.println( "ContactID = " + contact.getId() );
      System.out.println("GetGroups = " + contact.getGroups());
    }
    session.getTransaction().commit();
    session.close();

    //*************************************


    app.contact().HomePage();

    Contacts someContact = app.db().contacts();
    ContactData selectedContact = someContact.iterator().next();

    app.contact().findContactByID(selectedContact.getId());
    app.contact().initAddContactToGroup() ;

    Groups someGroup = app.db().groups();
    GroupData selectedGroup = someGroup.iterator().next();

    String groupName = selectedGroup.getName();
    Integer groupID = selectedGroup.getId();

   // System.out.println(groupName + "   " + groupID);

    app.contact().selectGroupToAdd(groupName, groupID) ;
    app.contact().addToGroupButton () ;

    app.contact().goToGroupPageWithName(groupName);

  }



  @Test
  public ContactData AddContactToGroup(ContactData contact){
    /*
    app.contact().selectContactById(contact.getId());
    app.contact().selectGroupToAdd();
    app.contact().addToGroup();


*/
   /*
   public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }

  public void initContactModification(int index) {
    click(By.xpath("//a[@href='edit.php?id=" + index + "']"));
    */
    return contact;
  }



  public void  addNewGroup(GroupData groupName){
    app.contact().HomePage();
    Groups groups = app.db().groups();

    app.group().groupPage(); //???
    app.group().createGroup(new GroupData().withName("test_group"));

  }

}