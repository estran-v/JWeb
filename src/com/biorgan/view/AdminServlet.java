package com.biorgan.view;

import com.biorgan.model.BiorganUser;
import com.biorgan.sql.BiorganSQL;
import com.biorgan.sql.BiorganSqlException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 * Created by wilyr on 11/7/2015.
 */
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    BiorganUser user;
    HttpSession session;
    private boolean hasAccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        System.out.println("Tring to access admin panel");
        if (session == null || ((BiorganUser)session.getAttribute("user") == null))
        {
            System.err.println("Error : not connected");
            this.getServletContext().getRequestDispatcher("/index.jsp?e=1").forward(request, response);
            return false;
        }
        user = (BiorganUser) session.getAttribute("user");
        if (!user.isAdmin()) {
            System.err.println("Error : you don't have the correct access rights");
            this.getServletContext().getRequestDispatcher("/index.jsp?e=2").forward(request, response);
            return false;
        }
        System.out.println("Access granted");
        return (true);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (hasAccess(request, response)) {
            if (request.getParameter("type") != null){
                if (request.getParameter("type").compareTo("1") == 0)
                {
                    if (!postNews(request, response)){
                        this.getServletContext().getRequestDispatcher("/admin.jsp?e=1").forward(request, response);
                    }
                }
            }
        }
        this.getServletContext().getRequestDispatcher("/admin.jsp?success=1").forward(request, response);
    }

    private boolean postNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Posting some news");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if (title != null && content != null && title.length() != 0 && content.length() != 0) {
            BiorganSQL db = new BiorganSQL("root", "root");
            try {
                db.PostNews(title, content);
                return (true);
            } catch (BiorganSqlException e) {
                e.printStackTrace();
                return (false);
            }
        } else {
            return (false);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (hasAccess(request, response))
            this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
