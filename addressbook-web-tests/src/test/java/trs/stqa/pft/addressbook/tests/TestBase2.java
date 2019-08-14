package trs.stqa.pft.addressbook.tests;

import trs.stqa.pft.addressbook.appmanager.ApplicationManager2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase2 {

  protected final ApplicationManager2 app = new ApplicationManager2();

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();

  }

  public ApplicationManager2 getApp() {
    return app;
  }
}
