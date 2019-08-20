package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().initContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("Ivan",  "Ivanovich", "Petrov", "21 E Mossovet str", "123456789", "abc@job.com", "test_group"),true);
      app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().goToHomePage();
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Petrov" ,"21 E Mossovet str", "123456789", "abc@job.com", null),false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();


  }
}
