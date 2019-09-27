package trs.stqa.pft.mantis.tests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelperM extends TestBase {

  public List<String> ids = new ArrayList<>();
  public List<String> usernames = new ArrayList<>();

  public void DbHelperStart() {

    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select * from mantis_user_table");


      while (rs.next()) {
        ids.add(rs.getString("id"));
        usernames.add(rs.getString("username"));
      }
      //System.out.println(ids);
      //System.out.println(usernames);

      rs.close();
      st.close();
      conn.close();

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
