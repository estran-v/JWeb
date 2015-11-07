<%@ page import="java.util.ArrayList" %>
<%@ page import="com.biorgan.model.BiorganNews" %><%--
  Created by IntelliJ IDEA.
  User: wilyr
  Date: 11/7/2015
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<%
    ArrayList<BiorganNews> news = (ArrayList) request.getAttribute("resNewsList");
    String title;
    String content;
    String date;
    if (news != null)
    {
        for (int i = 0; i < news.size(); i++)
        {
            %>
        <div class="box">
            <div class="title"><%= news.get(i).getTitle()%></div>
            <div class="boxcontent"><%= news.get(i).getContent()%></div>
        </div>
<%
        }
    }
%>
</body>
</html>
