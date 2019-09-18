package trs.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    /*app.group().groupPage();
    if (app.group().all().size() == 0){
      app.group().createGroup(new GroupData().withName("test_group"));
      app.group().groupPage();//FFF
    }

     */
    if (app.db().groups().size() == 0){
      app.group().groupPage();
      app.group().createGroup(new GroupData().withName("test_group"));
    }
    app.group().groupPage();//FFF
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));

    verifyGroupListInUI();
  }

}
