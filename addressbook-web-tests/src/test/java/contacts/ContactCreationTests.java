package contacts;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    initContactCreation();
    fillContactForm(new GroupData("Ivan", "Ivanovich", "Petrov", "21 E Mossovet str", "123456789", "abc@job.com"));
    submitContactCreation();
    returnToHomePage();
    logout();
  }


}
