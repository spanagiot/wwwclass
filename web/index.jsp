<%--
  Created by IntelliJ IDEA.
  User: steel
  Date: 2019-11-03
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home page</title>
  </head>
  <body>
  <%
    Cookie[] cks = request.getCookies();
    String username = "";
    if (cks != null) {
      for (int i = 0; i < cks.length; i++) {
        String name = cks[i].getName();
        String value = cks[i].getValue();
        if (name.equals("auth")) {
          username = value;
          break; // exit the loop and continue the page
        }
      }
    }
  %>
  <% if(username.length() > 0){ %>
  <h1>Hello <% out.print(username); %></h1>
  <h3>You successfully logged in.</h3><br>
  <form action="logout" method="post">
    <input type="submit" value="Logout">
  </form>
  <% }else{ %>
  <h1>Hello stranger</h1>
  Please <a href="./login.jsp">login</a> or <a href="./register.jsp">register</a> to continue!
  <% } %>
  </body>
</html>
