<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 05/11/2015
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="header.jsp"%>
<%
    if (request.getParameter("e") != null) {
        if (request.getParameter("e").compareTo("1") == 0)
        { %>
<div class="error">Wrong password</div>
<%
}
    if (request.getParameter("e").compareTo("2") == 0) {
%> <div class="error">No field should be empty</div><%
    }
    }
%>


    <form method="post" action="login">
        <fieldset>
            Email : <input type="text" name="user_mail" /><br>
            Password : <input type="password" name="user_passwd" /><br>
            <input type="submit" value="Sign In" />
        </fieldset>
    </form>
</body>
</html>
