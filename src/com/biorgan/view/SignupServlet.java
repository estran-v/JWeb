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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganSQL db = new BiorganSQL("root", "root");
        String passwd = request.getParameter("user_passwd");
        String passwd_r = request.getParameter("user_passwd_r");
        String mail = request.getParameter("user_mail");
        String firstname = request.getParameter("user_firstname");
        String lastname = request.getParameter("user_lastname");
        if (lastname == null || passwd == null || passwd_r == null || mail == null || firstname == null)
        {
            System.err.print("No field shout be empty");
        }
        else if (passwd.compareTo(passwd_r) != 0)
            System.err.print("Passwords don't match");
        else {
            System.out.print("Registering....");
            try {
                db.RegisterUser(firstname, lastname, mail, passwd);
                System.out.print("Registering ok");
            } catch (BiorganSqlException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
