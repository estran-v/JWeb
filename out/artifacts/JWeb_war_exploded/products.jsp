<%@ page import="java.sql.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 06/11/2015
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp"%>

<%
    String errorCode;
    String errorHtml = "<div>Please enter all the following informations to register</div>";
    String product_name = request.getParameter("product_name") == null ? "" : request.getParameter("product_name");
    String product_stock = request.getParameter("product_stock") == null ? "" : request.getParameter("product_stock");
    String product_desc = request.getParameter("product_desc") == null ? "" : request.getParameter("product_desc");
    String product_price = request.getParameter("product_price") == null ? "" : request.getParameter("product_price");
    if (request != null && (errorCode = request.getParameter("e")) != null)
    {
        switch (errorCode){
            case "1":
                errorHtml = "<div class=\"productserror\">No field should be empty</div>";
                break;
            case "2":
                errorHtml = "<div class=\"productserror\">Product already exists</div>";
                break;
            case "3":
                errorHtml = "<div class=\"productserror\">Can't connect to database</div>";
                break;
        }
    }
    if (errorHtml != null)
        out.println(errorHtml);

%>

    <form action="products" method="post">
        <fieldset>
            Product name : <input type="text" name="product_name" value="<%= product_name%>"/><br>
            Product stock : <input type="number" name="product_stock" value="<%= product_stock%>"/><br>
            Description : <input type="text" name="product_desc" value="<%= product_desc%>"/><br>
            Price(euro): <input type="number" name="product_price" value="<%= product_price%>"/><br>
            <input type="submit" value="Add product" />
        </fieldset>
    </form>
<%
    ArrayList rows = new ArrayList();

    if (request.getAttribute("resList") != null) {
    rows = (ArrayList)request.getAttribute("resList");
    }

    for(int i = 0; i < rows.size(); i++){
    ArrayList<String> row = (ArrayList)rows.get(i);
    int y = 1;
    while(y < 5){
    String prod_name = row.get(y++);
    String prod_stock = row.get(y++);
    String prod_desc = row.get(y++);
    String prod_price = row.get(y++);
    %>
    <div class="products">
        <p><%=prod_name%></p>
        <p>Stock: <%=prod_stock%></p>
        <p>Description: <%=prod_desc%></p>
        <p>Prix: <%=prod_price%> euros</p>
    </div>
    <%
        }
        }

%>
</body>
</html>
