package contacts.tests;

import contacts.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    app.getGroupHelper().initContactCreation();
    app.getGroupHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Petrov", "21 E Mossovet str", "123456789", "abc@job.com"));
    app.getGroupHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
    app.logout();
  }


}
