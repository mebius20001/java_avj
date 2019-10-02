package trs.stqa.pft.rest.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import org.testng.Assert;
import org.testng.annotations.Test;
import trs.stqa.pft.mantis.model.Issue;
import trs.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

  @Test
  public void testGetProgects() throws MalformedURLException, ServiceException, RemoteException {

    skipIfNotFixed(1);

    Set<Project> projects = app.soap().getProgects();
    System.out.println(projects.size());
    for (Project project : projects){
      System.out.println(project.getName());
    }
  }

  @Test
  public void testIssueStatus() throws MalformedURLException, ServiceException, RemoteException {

        skipIfNotFixed(1);

    Set<Project> projects = app.soap().getProgects();
    System.out.println(projects.size());

    for (Project project : projects){
      System.out.println(project.getName());
      IssueData issueData = new IssueData();
      System.out.println(issueData.getStatus());
      System.out.println(issueData.getView_state());

    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException{
    skipIfNotFixed(1);
    Set<Project> projects = app.soap().getProgects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(),created.getSummary());
  }


}
