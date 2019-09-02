package trs.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trs.stqa.pft.addressbook.model.GroupData;
import trs.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().groupPage();
    if ( app.group().all().size() == 0){
      app.group().createGroup(new GroupData().withName("test_group"));
      app.group().groupPage();//FFF
    }
  }


  @Test
  public void testGroupModification(){

    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test_group").withHeader("test2").withFooter("test1");

    app.group().modify(group);

    Groups after = app.group().all();

    Assert.assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

  }


}
