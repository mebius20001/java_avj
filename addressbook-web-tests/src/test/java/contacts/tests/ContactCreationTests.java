package contacts.tests;

import contacts.model.GroupData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    app.initContactCreation();
    app.fillContactForm(new GroupData("Ivan", "Ivanovich", "Petrov", "21 E Mossovet str", "123456789", "abc@job.com"));
    app.submitContactCreation();
    app.returnToHomePage();
    app.logout();
  }


}
