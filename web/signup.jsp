<%--
  Created by IntelliJ IDEA.
  User: wilyr
  Date: 11/4/2015
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%
    String errorCode;
    String errorHtml = "<div>Please enter all the following informations to register</div>";
    String user_fname = request.getParameter("user_firstname") == null ? "" : request.getParameter("user_firstname");
    String user_lname = request.getParameter("user_lastname") == null ? "" : request.getParameter("user_lastname");
    String user_mail = request.getParameter("user_mail") == null ? "" : request.getParameter("user_mail");
    String user_passwd = request.getParameter("user_passwd") == null ? "" : request.getParameter("user_passwd");
    if (request != null && (errorCode = request.getParameter("e")) != null)
    {
        switch (errorCode){
            case "1":
                errorHtml = "<div class=\"error\">No field should be empty</div>";
                break;
            case "2":
                errorHtml = "<div class=\"error\">Passwords don't match</div>";
                break;
            case "3":
                errorHtml = "<div class=\"error\">Can't connect to database</div>";
                break;
            case "4":
                errorHtml = "<div class=\"error\">User already exists</div>";
                break;
            case "5":
                errorHtml = "<div class=\"error\">Email is not valid</div>";
                break;
        }
    }
    if (errorHtml != null)
        out.println(errorHtml);
%>
    <form action="signup" method="post">
        <fieldset>
            First Name : <input type="text" name="user_firstname" value="<%= user_fname%>"/><br>
            Last Name : <input type="text" name="user_lastname" value="<%= user_lname%>"/><br>
            Email : <input type="email" name="user_mail" value="<%= user_mail%>"/><br>
            Password : <input type="password" name="user_passwd" value="<%= user_passwd%>"/><br>
            Retype password : <input type="password" name="user_passwd_r" /><br>
            <input type="checkbox" name="user_sub" checked="true" />Subscribe to the newsletter<br>
            <input type="submit" value="Sign Up" />
        </fieldset>
    </form>
</body>
</html>
