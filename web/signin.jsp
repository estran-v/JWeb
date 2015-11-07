<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 05/11/2015
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="header.jsp"%>
    <form method="post" action="login">
        <fieldset>
            Email : <input type="text" name="user_mail" /><br>
            Password : <input type="text" name="user_passwd" /><br>
            <input type="submit" value="Sign In" />
        </fieldset>
    </form>
</body>
</html>
