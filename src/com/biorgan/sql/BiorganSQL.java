package com.biorgan.sql;

import java.io.InputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.biorgan.model.BiorganUser;
import com.biorgan.model.Products;
import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;

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

    public void OpenDB() throws BiorganSqlException {
        try {
            System.err.println("Connection to DB...");
            String connectionURL = DB_URL;
            connection = null;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root", "root");
            if (!connection.isClosed())
                System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
            connected = true;
        } catch (Exception e) {
            System.err.println("Connection Failed");
            e.printStackTrace();
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            connected = false;
            throw ex;
        }
    }

    public void CloseDB() throws BiorganSqlException {
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
                PreparedStatement ps = connection.prepareStatement("INSERT INTO biorgan.users(passwd, fname, lname, email, isadmin) VALUES (?, ?, ?, ?, 0)");
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

    public BiorganUser getUser(String user_mail, String passwd) throws BiorganSqlException {

        try {
            OpenDB();
            if (isConnected()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM biorgan.users WHERE email = ? AND passwd = ?");
                ps.setString(1, user_mail);
                ps.setString(2, passwd);

                res = ps.executeQuery();
                if (!res.next())
                    return (null);
                res.first();
                System.out.println(res.getString("fname"));
                System.out.println(res.getInt("isadmin"));
                System.out.println(res.getInt("isadmin") == 1);
                return (new BiorganUser(res.getString("fname"), res.getString("lname"), res.getString("email"),
                        res.getInt("isadmin") == 1));
            } else
                throw new Exception("Connection lost");
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public boolean findUser(String user_mail) throws BiorganSqlException {

        try {
            OpenDB();
            if (isConnected()) {
                PreparedStatement ps = connection.prepareStatement("SELECT email FROM biorgan.users WHERE email = ?");
                ps.setString(1, user_mail);
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

    public void RegisterProduct(String name, int stock, String desc,
                                int price) throws BiorganSqlException {
        try {
            OpenDB();
            if (isConnected()) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO biorgan.products(product_name, product_stock, product_desc, product_price) VALUES (?, ?, ?, ?)");
                ps.setString(1, name);
                ps.setInt(2, stock);
                ps.setString(3, desc);
                ps.setInt(4, price);
                ps.execute();
                CloseDB();
            } else
                throw new Exception("Connection lost");
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public Products findProduct(String product_id) throws BiorganSqlException {
        Products p = new Products();

        try {
            OpenDB();
            if (isConnected()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM biorgan.products WHERE product_id = ?");

                ps.setString(1, product_id);
                res = ps.executeQuery();

                if (!res.next())
                    return null;

                p.setProduct_id(res.getString("product_id"));
                p.setProduct_name(res.getString("product_name"));
                p.setProduct_stock(res.getString("product_stock"));
                p.setProduct_desc(res.getString("product_desc"));
                p.setProduct_stock(res.getString("product_stock"));
                p.setProduct_price(res.getString("product_price"));

                return p;
            } else
                throw new Exception("Connection lost");
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public java.sql.ResultSet getAllProducts() throws BiorganSqlException {

        try {
            OpenDB();
            if (isConnected()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM biorgan.products");
                res = ps.executeQuery();
                return res;
            } else
                throw new Exception("Connection lost");
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public void PostNews(String title, String content) throws BiorganSqlException {
        try {
            OpenDB();
            if (isConnected())
            {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO biorgan.news(titlenews, contentnews, datenews) VALUES (?, ?, NOW())");
                ps.setString(1, title);
                ps.setString(2, content);
                ps.execute();
                CloseDB();
            } else
                throw new Exception("Connection lost");
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public java.sql.ResultSet getAllNews() throws BiorganSqlException {
        try {
            OpenDB();
            if (isConnected()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM biorgan.news");
                res = ps.executeQuery();
                return res;
            } else
                throw new Exception("Connection lost");
        } catch (Exception e) {
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public java.sql.ResultSet findReviews(String id) throws SQLException, BiorganSqlException{

        try{
            OpenDB();
            PreparedStatement ps = connection.prepareStatement("SELECT username, review, date_review FROM biorgan.reviews WHERE product_id = ? ORDER BY date_review DESC");
            ps.setString(1, id);

            res = ps.executeQuery();

            return res;
        } catch (Exception e){
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }

    public void postReview(String product_id, String name, String review) throws BiorganSqlException{
        try {
            OpenDB();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO biorgan.reviews (product_id, username, review, date_review) VALUES(?, ?, ?, NOW());");
            ps.setString(1, product_id);
            ps.setString(2, name);
            ps.setString(3, review);

            ps.execute();
        } catch (Exception e){
            BiorganSqlException ex = new BiorganSqlException(e.getMessage());
            throw ex;
        }
    }
}
