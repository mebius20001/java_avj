package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase2 {

  @Test
  public void testContactDeletion() throws Exception {

    app.getGroupHelper().selectContact();
    app.getGroupHelper().deleteSelectedContacts();
    app.closeAlertWindow();
    app.getNavigationHelper().goToHomePage();

  }


}
