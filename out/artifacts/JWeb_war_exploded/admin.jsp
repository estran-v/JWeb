<%--
  Created by IntelliJ IDEA.
  User: wilyr
  Date: 11/7/2015
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp"%>

<%
        if (request.getParameter("e") != null) {
            if (request.getParameter("e").compareTo("1") == 0)
            { %>
              <div class="error">An error occurred : can't post news</div>
            <%}
        }
        if (request.getParameter("success") != null) {
            %> <div class="success">News successfully posted</div><%
        }
%>
<div class="box">
    <div class="title">Write a News</div>
    <form action="admin" method="post">
        <fieldset>
            <div align="center">
                <label><b>Title</b></label><br><input type="text" name="title" style="width: 70%"/><br><br>
                <label><b>Content</b></label><br><textarea name="content" cols="200" rows="20"></textarea><br>
                <input type="hidden" name="type" value="1" />
                <input type="submit" value="Submit" /><br>
            </div>
        </fieldset>
    </form>
</div>

 <!-- ##################################################################################################### -->


<%
    if (request.getParameter("p") != null) {
        if (request.getParameter("p").compareTo("1") == 0)
        { %>
    <div class="error">No field shout be empty</div>
    <%}
        if (request.getParameter("p").compareTo("2") == 0) {
    %> <div class="error">Product already exists</div><%
    }
    if (request.getParameter("p").compareTo("3") == 0) {
    %> <div class="error">Problem with db</div><%
    }
    }
        if (request.getParameter("psuccess") != null) {
    %> <div class="success">News successfully posted</div><%
        }
    %>

<div class="box">
    <div class="title">Add a product</div>
    <form action="products" method="post">
        <fieldset>
            <div align="center">
                Product name : <input type="text" name="product_name" class="space"/><br>
                Product stock : <input type="number" name="product_stock" class="space"/><br>
                Short description : <textarea cols="100" rows="10" name="product_desc" class="space"></textarea><br>
                Price(euro): <input type="number" name="product_price" class="space"/><br>
                <input type="submit" value="Add product" />
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
