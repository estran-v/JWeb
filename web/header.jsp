<%@ page import="com.biorgan.model.BiorganUser" %><%--
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
        <title>Biorgan</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" href="css/header.css" type="text/css" />
        <link rel="stylesheet" href="css/global.css" type="text/css" />
    </head>
    <body id="top">
    <div class="header">
        <div class="left">
            <h1 id="title">Biorgan</h1>
            <p id="subtitle">Les organes les plus bio du marche ! </p>
        </div>

        <div class="right">
            <a class="button_header" href="index.jsp">Accueil</a>
            <a class="button_header" href="/products">Nos organes</a>
            <a class="button_header">Contacts</a>
            <% if (session.getAttribute("user") == null) {
            %>
                <a class="button_header" href="/signup">Inscription</a>
                <a class="button_header" href="/login">Connexion</a>
            <% } else {
                System.out.println(((BiorganUser)session.getAttribute("user")).isAdmin());
                if (((BiorganUser)session.getAttribute("user")).isAdmin()) {%>
                    <a class="button_header" href="/admin">Admin Panel</a>
                <% } %>
                <a class="button_header" href="/logout">Deconnexion</a>
            <%}%>
        </div>
    </div>

