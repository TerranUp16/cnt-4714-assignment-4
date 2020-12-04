/*
  Name: Robert Schwyzer
  Course: CNT 4714 -- Fall 2020 -- Project Four
  Assignment title: A Three-Tier Distributed Web-Based Application
  Date: December 4, 2020
*/

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MysqlHelper {
  public static boolean isSelect(String q) {
    Pattern selectPattern = Pattern.compile("^(EXPLAIN[\s]+|ANALYZE[\s]+){0,1}SELECT[\s]+",
        Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
    Pattern showPattern = Pattern.compile("^SHOW[\s]+", Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
    Matcher matcher = selectPattern.matcher(q);
    Matcher showMatcher = showPattern.matcher(q);
    return matcher.find() || showMatcher.find();
  }

  public static String getHtmlRows(ResultSet resultSet) throws SQLException {
    // Use a StringBuilder to create the string we will return
    StringBuilder bld = new StringBuilder();

    // Setup the table and start the header row
    bld.append("<div class=\"table-responsive\"><table class=\"table table-striped table-hover table-sm table-dark\"><thead><tr>");

    // Obtain column metadata and loop through it
    ResultSetMetaData md = (ResultSetMetaData) resultSet.getMetaData();
    int columns = md.getColumnCount();

    // Generate header cells
    for (int i = 1; i <= columns; i++) {
      bld.append("<th scope=\"col\">");
      bld.append(md.getColumnLabel(i));
      bld.append("</th>");
    }

    // Finish-off header and start body
    bld.append("</tr></thead><tbody>");

    // Append rows
    while (resultSet.next()) {
      // Start new row
      bld.append("<tr>");

      // Loop through columns in row
      for (int i = 1; i <= columns; i++) {
        bld.append("<td>");
        bld.append(resultSet.getString(i));
        bld.append("</td>");
      }

      // End row
      bld.append("</tr>");
    }

    // Finish-off table
    bld.append("</tbody></table></div>");

    return bld.toString();
  }
}