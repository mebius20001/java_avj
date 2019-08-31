package trs.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().groupPage();
    if (app.group().list().size() == 0){
      app.group().createGroup(new GroupData().withName("test_group"));
      app.group().groupPage();//FFF
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);

    //app.group().groupPage();
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }




}
