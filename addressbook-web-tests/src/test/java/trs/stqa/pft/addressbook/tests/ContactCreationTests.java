package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();

    ContactData contact = new ContactData()
            .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Petrov")
            .withAddress("21 E Mossovet str").withHomePhone("123456789").withEmail("abc@job.com").withGroup("test_group");

    app.contact().create(contact,true);
    app.contact().HomePage();

    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

  }


}
