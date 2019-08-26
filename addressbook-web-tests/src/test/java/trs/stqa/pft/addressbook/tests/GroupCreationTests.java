package trs.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
   // int before = app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test2_g", null, null);
    app.getGroupHelper().createGroup(group);
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
