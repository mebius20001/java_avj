package trs.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){

    app.getNavigationHelper().gotoGroupPage();



    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
      app.getNavigationHelper().gotoGroupPage();//FFF
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test_group", "test1", "test2");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
