package trs.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().initContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("Ivan",  "Ivanovich", "Petrov", "21 E Mossovet str", "123456789", "abc@job.com", "test_group"),true);
      app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().closeAlertWindow();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }


}
