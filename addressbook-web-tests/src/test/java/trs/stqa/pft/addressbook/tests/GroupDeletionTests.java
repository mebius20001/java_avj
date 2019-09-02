package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().groupPage();
    if (app.group().all().size() == 0){
      app.group().createGroup(new GroupData().withName("test_group"));
      app.group().groupPage();//FFF
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);
    //app.group().groupPage();
    Groups after = app.group().all();

    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

}
