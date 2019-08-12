package contacts;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {

    selectContact();
    deleteSelectedContacts();
    closeAlertWindow();
    returnToHomePage();

  }


}
