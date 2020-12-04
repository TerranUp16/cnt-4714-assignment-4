/*
  Name: Robert Schwyzer
  Course: CNT 4714 -- Fall 2020 -- Project Four
  Assignment title: A Three-Tier Distributed Web-Based Application
  Date: December 4, 2020
*/

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class BusinessLogicSession extends HttpServlet {
  private Connection connection;

  @Override
  public void init() throws ServletException{
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String dbURL = "jdbc:mysql://mysql8:3306/project4";
      String username = "root";
      String password = "M7vDRWBJW1EAGzih";
      connection = DriverManager.getConnection(dbURL, username, password);
    } catch (ClassNotFoundException e) {
      System.out.println("Error loading driver.");
    } catch (SQLException e) {
      System.out.println("Error opening the db connection: " + e.getMessage());
    }
  }

  public void destroy() {
    try {
      connection.close();
    } catch (SQLException e) {
      System.out.println("Error closing the db connection: " + e.getMessage());
    }
  }

  // process "get" requests from clients
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String sqlCommand = request.getParameter("sqlCommand");
    System.out.println(sqlCommand);
    String results = "";
    StringBuilder bld = new StringBuilder();

    if (MysqlHelper.isSelect(sqlCommand)) {
      // Do a SELECT query and output a table
      ResultSet rs = null;

      try (Statement stmt = connection.createStatement()) {
        rs = stmt.executeQuery(sqlCommand);
        results = MysqlHelper.getHtmlRows(rs);
      } catch (SQLException e) {
        // Set results to an error alert
        results = errorOutput(e);
      }
    } else {
      // Get starting state of supplier shipments
      generateTemporaryTable();

      // Do write query
      try (Statement stmt = connection.createStatement()) {
        try (Statement statemnt = connection.createStatement()) {
          // Run query and get number of affected rows
          int rowsAffected = statemnt.executeUpdate(sqlCommand);

          // Generate output string for this query
          bld.append("<div class=\"alert alert-success\" role=\"alert\">");
          bld.append("<h3>The statement executed successfully.</h3>");
          bld.append("<p class=\"lead\">");
          bld.append(rowsAffected);
          if (rowsAffected == 1) {
            bld.append(" row");
          } else {
            bld.append(" rows");
          }
          bld.append(" affected.</p>");

          // Process business logic and store affected row count
          try (Statement statement = connection.createStatement()) {
            rowsAffected = statement.executeUpdate("UPDATE suppliers SET status = status + 5 WHERE snum in (SELECT DISTINCT snum FROM shipments LEFT JOIN beforeShipments USING (snum, pnum, jnum, quantity) WHERE beforeShipments.snum IS NULL AND quantity > 100);");

            // Output based on business logic
            bld.append("<h3>Business Logic Detected! - Updating Supplier Status</h3>");
            bld.append("<p class=\"lead\">Business Logic updated ");
            bld.append(rowsAffected);
            if (rowsAffected == 1) {
              bld.append(" supplier status mark.");
            } else {
              bld.append(" supplier status marks.");
            }
            bld.append("</p>");
          }
        }

        // Clean-up output
        bld.append("</div>");
        results = bld.toString();
      } catch (SQLException e) {
        results = errorOutput(e);
      }
    }

    // Set and dispatch output
    HttpSession session = request.getSession();
    session.setAttribute("results", results);
    session.setAttribute("sqlCommand", sqlCommand);
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doGet(request, response);
  }

  /**
   * Generates fancy output for SQLException errors
   * @param e SQLException
   * @return  Formatted String to be used for results
   */
  private String errorOutput(SQLException e) {
    StringBuilder bld = new StringBuilder();
    bld.append("<div class=\"alert alert-danger\" role=\"alert\">");
    bld.append("<h2>Error executing the SQL statement:</h2>");
    bld.append("<pre><code>");
    bld.append(e.toString());
    bld.append("</code></pre></div>");
    return bld.toString();
  }

  private void generateTemporaryTable() {
    try (Statement stmt = connection.createStatement()) {
      // Get starting state of supplier shipments
      stmt.executeUpdate("DROP TABLE IF EXISTS beforeShipments;");
      try (Statement stamt = connection.createStatement()) {
        stamt.executeUpdate("CREATE TABLE beforeShipments LIKE shipments;");
        try (Statement statement = connection.createStatement()) {
          statement.executeUpdate("INSERT INTO beforeShipments SELECT * FROM shipments;");
        }
      }
    } catch (SQLException e) {
      System.out.println(e.toString());
    }
  }
}