package com.biorgan.view;

import com.biorgan.model.BiorganReviews;
import com.biorgan.model.Products;
import com.biorgan.sql.BiorganSqlException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Vincent on 08/11/2015.
 */
@WebServlet(name = "ReviewServlet")
public class ReviewServlet extends HttpServlet {
    private Products product;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganReviews reviews = new BiorganReviews();
        HttpSession session = request.getSession();
        String name = (String)session.getAttribute("name");
        String err;
        String id = (String)request.getAttribute("product_id");
        try {
            err = reviews.save(request.getParameter("product_id"), name, request.getParameter("review"));
            if (err.equals("1")){
                System.out.println("Review too short or too long");
                request.setAttribute("product", product);
                this.getServletContext().getRequestDispatcher("/reviews.jsp?id=" + id + "&e=1").forward(request, response);
            }
            else if (err.equals("0")){
                System.out.println("Review sent");
                request.setAttribute("product", product);
                this.getServletContext().getRequestDispatcher("/reviews.jsp?id=" + id + "&e=0").forward(request, response);
            }
        } catch (BiorganSqlException | SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganReviews reviews = new BiorganReviews();

        try {
            product = reviews.find(request.getParameter("id"));
        } catch (BiorganSqlException | SQLException | NullPointerException e){
            e.printStackTrace();
        }
        request.setAttribute("product", product);
        this.getServletContext().getRequestDispatcher("/reviews.jsp?id=" + product.getProduct_id()).forward(request, response);
    }
}
