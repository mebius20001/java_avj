package trs.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().HomePage();
    if ( app.contact().all().size() == 0){

    ContactData contact = new ContactData()
            .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Petrov")
            .withAddress("21 E Mossovet str").withHomePhone("123456789").withEmail("abc@job.com").withGroup("test_group");

    app.contact().create(contact,true);
      app.contact().HomePage();
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Petrov")
            .withAddress("21 E Mossovet str").withHomePhone("123456789").withEmail("abc@job.com").withGroup("test_group");

    app.contact().modify(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
