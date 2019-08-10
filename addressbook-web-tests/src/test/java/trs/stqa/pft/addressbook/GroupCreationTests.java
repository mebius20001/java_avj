package trs.stqa.pft.addressbook;



import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test_group", "test1", "test2"));
    submitGroupCreation();
    gotoGroupPage();
    wd.findElement(By.linkText("Logout")).click();
  }


}
