<!DOCTYPE html>
<!-- Name: Robert Schwyzer -->
<!-- Course: CNT 4714 -- Fall 2020 -- Project Four -->
<!-- Assignment title: A Three-Tier Distributed Web-Based Application -->
<!-- Date: December 4, 2020 -->
<%
  String sqlCommand = (String) session.getAttribute("sqlCommand");
  if (sqlCommand == null) sqlCommand = "select * from suppliers;";
  String results = (String) session.getAttribute("results");
  if (results == null) results = " ";
%>

<html lang="en">
  <!-- head section of document -->
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>A Three-Tier Distributed Web-Based Application</title>
  </head>
  <body>
    <!-- Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    <div class="container">
      <div class="jumbotron">
        <h1 class="display-4">Welcome to the Fall 2020 Project 4 Enterprise Database System</h1>
        <p class="lead">A Servlet/JSP-based Multi-tiered Enterprise Application Using a Tomcat Container</p>
      </div>
      <form action="/Project4/businesslogic" method="post">
        <div class="row">
          <div class="col">
            <label for="sqlCommand" class="text-center">You are connected to the Project 4 Enterprise System database. Please enter any valid SQL query or update command.</label>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <textarea class="form-control form-control-lg text-light text-monospace bg-dark" id="sqlCommand" name="sqlCommand" rows="6"><%=sqlCommand%></textarea>
          </div>
        </div>
        <div class="form-row">
          <div class="col">
            <button type="submit" class="btn btn-primary btn-block float-right" formmethod="post">Execute Command</button>
          </div>
          <div class="col">
            <button type="button" class="btn btn-warning btn-block text-light float-left" type="reset" onClick="document.getElementById('sqlCommand').value = ''">Reset Form</button>
          </div>
        </div>
      </form>
      <row>
        <p class="lead text-center">
          <br />
          All execution results will appear below.
        </p>
      </row>
      <row>
        <div class="card">
          <div class="card-body">
            <h3 class="card-title text-center">Database Results</h3>
            <%-- JSP expression to access the results from the servlet --%>
            <%=results%>
          </div>
        </div>
      </row>
    </div>
  </body>
</html>  <!-- end HTML document -->