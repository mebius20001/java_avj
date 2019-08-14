package trs.stqa.pft.addressbook.tests;

import contacts.tests.TestBase;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {

    app.getGroupHelper().selectContact();
    app.getGroupHelper().deleteSelectedContacts();
    app.closeAlertWindow();
    app.getNavigationHelper().goToHomePage();

  }


}
