package trs.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {

  @BeforeClass
  public void init(){
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
  }

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    System.out.println("Created issue " + issueId);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  public Set<Issue> getIssues() throws IOException {

  //  String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=500"))
    //        .returnContent().asString();
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=500").asString();

    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");

    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }
/*
  private Executor getExecutor() {
    return  Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }
*/
  public int createIssue(Issue newIssue) throws IOException {
/*
    String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json?limit=500").
            bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                     new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
   */
    String json = RestAssured.given().parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json?limit=500").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();

  }

}
