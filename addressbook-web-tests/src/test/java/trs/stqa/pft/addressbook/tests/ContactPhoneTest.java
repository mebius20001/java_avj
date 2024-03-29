package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTest extends TestBase {

  @Test
  public void testContactPhones(){
    app.contact().HomePage();

    ContactData contact =  app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditform(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergreEmails(contactInfoFromEditForm)));

  }

  private String mergreEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactPhoneTest::cleaned).collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
      .stream().filter((s)-> !s.equals(""))
      .map(ContactPhoneTest::cleaned).collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone){
    return  phone.replaceAll("\\s", "").replaceAll("[-()]","");

  }

}
