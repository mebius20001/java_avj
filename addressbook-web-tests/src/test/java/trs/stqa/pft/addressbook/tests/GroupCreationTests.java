package trs.stqa.pft.addressbook.tests;



import org.testng.annotations.*;
import org.openqa.selenium.*;
import trs.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test_group", "test1", "test2"));
    app.submitGroupCreation();
    app.gotoGroupPage();
    app.wd.findElement(By.linkText("Logout")).click();
  }


}
