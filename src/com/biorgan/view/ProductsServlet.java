package com.biorgan.view;

import com.biorgan.sql.BiorganSQL;
import com.biorgan.sql.BiorganSqlException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Vincent on 06/11/2015.
 */
@WebServlet(name = "ProductsServlet")
public class ProductsServlet extends HttpServlet {
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
            this.getServletContext().getRequestDispatcher("/products.jsp?e=1").forward(request, response);
        }
        else {
            System.out.println("Registering....");
            try {
                if (db.findProduct(name) == false) {
                    db.RegisterProduct(name, Integer.parseInt(stock), desc, Integer.parseInt(price));
                    System.out.println("Product ok");
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } else {
                    System.out.println("Product already exists");
                    this.getServletContext().getRequestDispatcher("/products.jsp?e=2").forward(request, response);
                }
            } catch (BiorganSqlException e) {
                e.printStackTrace();
                this.getServletContext().getRequestDispatcher("/products.jsp?e=3").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganSQL db = new BiorganSQL("root", "root");
        try {
            ArrayList Rows = new ArrayList();
            java.sql.ResultSet res = db.getAllProducts();

            while (res.next()){
                ArrayList<String> row = new ArrayList();
                for (int i = 1; i <= 5; i++){
                    row.add(res.getString(i));
                }
                Rows.add(row);
            }

            request.setAttribute("resList", Rows);
        } catch (Exception e){
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
