<%@ page import="java.sql.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.biorgan.model.Products" %><%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 06/11/2015
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp"%>

            <%
                for (Products prod : (ArrayList<Products>)request.getAttribute("products")) {
                %>
                <div class="products">

                    <div class="titlep"><%=prod.getProduct_name()%></div>
                    <div class="boxcontentp">
                        Stock: <%=prod.getProduct_stock()%><br>
                        Prix:  <%=prod.getProduct_price()%>euros
                    </div>
                    <div class="desc">
                        Description: <%=prod.getProduct_desc()%>
                    </div>
                    <a class="reviewlink" href="/reviews?id=<%=prod.getProduct_id()%>">Review this product !</a>
                </div>
                <%
                }
            %>

</body>
</html>
