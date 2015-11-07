package com.biorgan.view;

import com.biorgan.model.BiorganNews;
import com.biorgan.sql.BiorganSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by wilyr on 11/7/2015.
 */
@WebServlet(name = "NewsServlet")
public class NewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BiorganSQL db = new BiorganSQL("root", "root");
        try {
            java.sql.ResultSet res = db.getAllNews();
            ArrayList<BiorganNews> news = new ArrayList<>();
            while (res.next() == true)
                news.add(new BiorganNews(res.getString("titlenews"), res.getString("contentNews"), res.getDate("datenews")));
            db.CloseDB();
            request.setAttribute("resNewsList", news);
        } catch (Exception e){
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/news.jsp").forward(request, response);
    }
}
