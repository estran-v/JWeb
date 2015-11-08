<%@ page import="com.biorgan.model.Products" %>
<%@ page import="com.biorgan.model.Reviews" %><%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 08/11/2015
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp"%>

<%if (request.getParameter("e") != null) {
        if (request.getParameter("e").compareTo("1") == 0)
        { %>
            <div class="error">An error occurred : Not too short/too long description</div>
<%}
        if (request.getParameter("e").compareTo("0") == 0) {
            %>
            <div class="success">Review successfully posted</div><%
        }
}%>

<%
    Products prod = (Products)request.getAttribute("product");
    System.out.println(request.getParameter("id"));
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
    </div>

    <h3 style="text-align: center;">Reviews for this product</h3>

    <%
        for (Reviews rev : prod.getReviews()) {
    %>
    <div class="products">

        <div class="titlep"><%=rev.getUsername()%></div>
        <div class="boxcontentp">
            Date:  <%=rev.getDate()%>
        </div>
        <div class="desc">
            <%=rev.getReview()%>
        </div>
    </div>
    <%
        }
    %>

<% if (request.getSession().getAttribute("name") != null){%>

<div class="box">
    <div class="title">Add a review</div>
    <form action="reviews" method="post">
        <fieldset>
            <div align="center">
                <input type="hidden" name="product_id" id="product_id" value="<%=request.getParameter("id")%>"/>
                Your name : <%= request.getSession().getAttribute("name")%><br>
                Short description : <br><textarea cols="100" rows="10" name="review" class="space"></textarea><br>
                <input type="submit" value="Add product" />
            </div>
        </fieldset>
    </form>
</div><%}%>

</body>
</html>
