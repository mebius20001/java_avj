package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().initContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("Ivan",  "Ivanovich", "Petrov", "21 E Mossovet str", "123456789", "abc@job.com", "test_group"),true);
      app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().goToHomePage();
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().closeAlertWindow();
    app.getNavigationHelper().goToHomePage();

  }


}
