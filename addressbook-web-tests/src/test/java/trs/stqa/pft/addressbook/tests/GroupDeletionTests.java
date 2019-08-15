package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import trs.stqa.pft.addressbook.tests.TestBase;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    app.getNavigationHelper().gotoGroupPage();
  }


}
