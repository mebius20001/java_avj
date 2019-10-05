package trs.stqa.pft.rest.tests;

public class Issue extends TestBase{
  private int id;
  private String subject;
  private  String description;
  private int state;
  private String state_name;

  public String getState_name() {
    return state_name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Issue issue = (Issue) o;

    return id == issue.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  public Issue withState_name(String state_name) {
    this.state_name = state_name;
    return this;
  }


  public int getState() {
    return state;
  }

  public Issue withState(int state) {
    this.state = state;
    return this;
  }

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public Issue withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }
}
