package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;
import trs.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();

    File photo = new File("src/test/resources/stru.jpg");
    ContactData contact = new ContactData()
            .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Petrov").withAddress("21 E Mossovet str")
            .withHomePhone("123456789").withMobilePhone("2222222").withWorkPhone("3333333")
            .withEmail("abc@job.com").withPhoto(photo)
            .withGroup("test_group");

    app.contact().create(contact,true);
    app.contact().HomePage();

    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
/*
  @Test
  public void testCurrentdir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
*/

}
