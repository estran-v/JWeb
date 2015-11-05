<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 04/11/2015
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Biorgan</title>
  </head>
  <body>
  <a href="/SigninServlet">co toi</a>
  <a href="/SignupServlet">1scri twa</a>
  <% if (session != null)
        System.out.print(session.getAttribute("mail"));
  %>

  </body>
</html>
