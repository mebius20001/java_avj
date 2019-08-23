package trs.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();

    int before = app.getGroupHelper().getGroupCount();

    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
      app.getNavigationHelper().gotoGroupPage();//FFF
    }

    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    app.getNavigationHelper().gotoGroupPage();

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }


}
