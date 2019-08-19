package trs.stqa.pft.addressbook.model;

public class ContactData {
  private static String group;

  private final String firstname;

  private final String middlename;
  private final String lastname;
  private final String address;
  private final String homePhone;
  private final String email;

  public ContactData(String firstname,  String middlename, String lastname, String address, String homePhone, String email, String group) {
    this.firstname = firstname;
    this.group = group;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.homePhone = homePhone;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getEmail() {
    return email;
  }

  public static String getGroup() {  return group;  }
}
