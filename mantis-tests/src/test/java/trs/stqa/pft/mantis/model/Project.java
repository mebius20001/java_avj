package trs.stqa.pft.mantis.model;

public class Project {
  private Integer id;
  private String name;

  public Integer getId() {
    return id;
  }

  public Project withId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }


}
