package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){

    app.getNavigationHelper().gotoGroupPage();
    
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
      app.getNavigationHelper().gotoGroupPage();//FFF
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test_group", "test1", "test2"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();

  }
}
