package contacts.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {

    app.selectContact();
    app.deleteSelectedContacts();
    app.closeAlertWindow();
    app.returnToHomePage();

  }


}
