<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 04/11/2015
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>

<!--taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Biorganic</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" href="css/header.css" type="text/css" />
    </head>
    <body id="top">
    <div class="header">
        <div class="left">
            <h1 id="title">Biorgan</h1>
            <p id="subtitle">Les organes les plus bio du marche ! </p>
        </div>

        <div class="right">
            <a class="button_header" href="index.jsp">Accueil</a>
            <a class="button_header">Nos organes</a>
            <a class="button_header">Contacts</a>
            <% if (session.getAttribute("mail") == null) {
                System.out.print(session.getAttribute("mail"));
            %>
            <a class="button_header" href="/SignupServlet">Inscription</a>
            <a class="button_header" href="/SigninServlet">Connexion</a>
            <% } else { %>
            <a class="button_header" href="/SignoutServlet">Deconnexion</a>
            <%}%>
        </div>
    </div>

