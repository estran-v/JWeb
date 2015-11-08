package com.biorgan.view;

import com.biorgan.sql.BiorganSQL;
import com.biorgan.sql.BiorganSqlException;
import com.biorgan.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Vincent on 06/11/2015.
 */
@WebServlet(name = "ProductsServlet")
public class ProductsServlet extends HttpServlet {
    private ArrayList<Products> list;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganSQL db = new BiorganSQL("root", "root");
        String name = request.getParameter("product_name");
        String stock = request.getParameter("product_stock");
        String desc = request.getParameter("product_desc");
        String price = request.getParameter("product_price");

        if (name == null || stock == null || desc == null || price == null ||
                name.length() == 0 || stock.length() == 0  || desc.length() == 0  || price.length() == 0)
        {
            System.err.println("No field shout be empty");
            this.getServletContext().getRequestDispatcher("/admin.jsp?p=1").forward(request, response);
        }
        else {
            System.out.println("Registering....");
            try {
                if (db.findProduct(name) == null) {
                    db.RegisterProduct(name, Integer.parseInt(stock), desc, Integer.parseInt(price));
                    System.out.println("Product ok");
                    this.getServletContext().getRequestDispatcher("/admin.jsp?psuccess=1").forward(request, response);
                } else {
                    System.out.println("Product already exists");
                    this.getServletContext().getRequestDispatcher("/admin.jsp?p=2").forward(request, response);
                }
            } catch (BiorganSqlException e) {
                e.printStackTrace();
                this.getServletContext().getRequestDispatcher("/admin.jsp?p=3").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganProducts prods = new BiorganProducts();

        try {
            list = prods.getProducts();
        } catch (BiorganSqlException | SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("products", list);
        this.getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
