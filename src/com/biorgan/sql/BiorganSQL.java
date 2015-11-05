package com.biorgan.sql;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;

/**
 * Created by wilyr on 11/4/2015.
 */
public class BiorganSQL {

    private static String DB_URL = "jdbc:mysql://localhost:3306/";
    private String user;
    private String passwd;
    private Connection connection = null;
    private boolean connected = false;
    private java.sql.ResultSet res = null;

    public BiorganSQL(String user, String passwd)
    {
        this.user = user;
        this.passwd = passwd;
        connected = false;
    }

    private void OpenDB() throws BiorganSqlException {
        try {
            String connectionURL = DB_URL;
            connection = null;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root", "root");
            if (!connection.isClosed())
                System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
            connected = true;
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            connected = false;
            throw ex;
        }
    }

    private void CloseDB() throws BiorganSqlException {
        try {
            connection.close();
            connected = false;
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public boolean isConnected() {
        return (connected);
    }

    public void RegisterUser(String user_firstname, String user_lastname, String user_mail,
                                String user_passwd) throws BiorganSqlException {
        try {
            OpenDB();
            if (isConnected()) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO biorgan.users(passwd, fname, lname, email) VALUES (?, ?, ?, ?)");
                ps.setString(1, user_passwd);
                ps.setString(2, user_firstname);
                ps.setString(3, user_lastname);
                ps.setString(4, user_mail);
                ps.execute();
                CloseDB();
            } else
                throw new Exception("Connection lost");
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public boolean getUser(String user_mail, String passwd) throws BiorganSqlException {

        try {
            OpenDB();
            if (isConnected()) {
                PreparedStatement ps = connection.prepareStatement("SELECT user_id, email, fname, lname FROM biorgan.users WHERE email = ? && passwd = ?");
                ps.setString(1, user_mail);
                ps.setString(2, passwd);

                res = ps.executeQuery();
                if (!res.next())
                    return false;
                return true;
            } else
                throw new Exception("Connection lost");
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }

    }
}
