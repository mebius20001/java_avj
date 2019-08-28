package trs.stqa.pft.addressbook.tests;

import trs.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Ivan",  "Ivanovich", "Petrov", "21 E Mossovet str", "123456789", "abc@job.com", "test_group"),true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();


  }


}
