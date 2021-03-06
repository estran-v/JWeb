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
import java.sql.PreparedStatement;

/**
 * Created by Vincent on 05/11/2015.
 */
@WebServlet(name = "SigninServlet")
public class SigninServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganSQL db = new BiorganSQL("root", "root");
        String passwd = request.getParameter("user_passwd");
        String mail = request.getParameter("user_mail");
        BiorganUser user = null;

        if (mail == null || passwd == null || mail.length() == 0 || passwd.length() == 0) {
            System.err.print("No field should be empty");
            this.getServletContext().getRequestDispatcher("/signin.jsp?e=2").forward(request, response);
        }
        else {
            try {
                user = db.getUser(mail, passwd);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("name", user.getFname());

                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }
                else {
                    System.err.print("Wrong password or email");
                    this.getServletContext().getRequestDispatcher("/signin.jsp?e=1").forward(request, response);
                }
            } catch (BiorganSqlException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);
    }
}
