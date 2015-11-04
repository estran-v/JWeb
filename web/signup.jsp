<%--
  Created by IntelliJ IDEA.
  User: wilyr
  Date: 11/4/2015
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <form action="SignupServlet" method="post">
        <fieldset>
            First Name : <input type="text" name="user_firstname" />
            Last Name : <input type="text" name="user_lastname" />
            Email : <input type="text" name="user_mail" />
            Password : <input type="text" name="user_passwd" />
            Retype password : <input type="text" name="user_passwd_r" />
            <input type="submit" value="Sign Up" />
        </fieldset>
    </form>
</body>
</html>
