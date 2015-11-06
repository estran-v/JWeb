package com.biorgan.view;

import com.biorgan.sql.BiorganSQL;
import com.biorgan.sql.BiorganSqlException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by wilyr on 11/4/2015.
 */
@WebServlet(name = "SignupServlet")
public class SignupServlet extends HttpServlet {
    public boolean isEmail(String s) {
        return s.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganSQL db = new BiorganSQL("root", "root");
        String passwd = request.getParameter("user_passwd");
        String passwd_r = request.getParameter("user_passwd_r");
        String mail = request.getParameter("user_mail");
        String firstname = request.getParameter("user_firstname");
        String lastname = request.getParameter("user_lastname");
        if (lastname == null || passwd == null || passwd_r == null || mail == null || firstname == null ||
                lastname.length() == 0 || passwd.length() == 0  || passwd_r.length() == 0  || mail.length() == 0  || firstname.length() == 0 )
        {
            System.err.println("No field shout be empty");
            this.getServletContext().getRequestDispatcher("/signup.jsp?e=1").forward(request, response);
        }
        else if (passwd.compareTo(passwd_r) != 0) {
            System.err.println("Passwords don't match");
            this.getServletContext().getRequestDispatcher("/signup.jsp?e=2").forward(request, response);
        }
        else if (isEmail(mail) == false)
        {
            System.err.println("Email is not valid");
            this.getServletContext().getRequestDispatcher("/signup.jsp?e=5").forward(request, response);
        }
        else {
            System.out.println("Registering....");
            try {
                if (db.findUser(mail) == false) {
                    db.RegisterUser(firstname, lastname, mail, passwd);
                    System.out.println("Registering ok");
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } else {
                    System.out.println("User already exists");
                    this.getServletContext().getRequestDispatcher("/signup.jsp?e=4").forward(request, response);
                }
            } catch (BiorganSqlException e) {
                e.printStackTrace();
                this.getServletContext().getRequestDispatcher("/signup.jsp?e=3").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
