package trs.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();

    int before = app.getGroupHelper().getGroupCount();

    app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
    app.getNavigationHelper().gotoGroupPage();

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);
  }


}
