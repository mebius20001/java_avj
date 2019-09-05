package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTest extends TestBase {

  @Test
  public void testContactPhones(){
    app.contact().HomePage();

    ContactData contact =  app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditform(contact);

    assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
    assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditForm.getMobilePhone()));
    assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));

  }

}
