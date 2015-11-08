package com.biorgan.model;

import com.biorgan.sql.BiorganSQL;
import com.biorgan.sql.BiorganSqlException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Vincent on 08/11/2015.
 */
public class BiorganReviews {

    private BiorganSQL db = new BiorganSQL("root", "root");
    private Products p = new Products();
    private java.sql.ResultSet res = null;

    public Products find(String id) throws SQLException, BiorganSqlException{

        p = db.findProduct(id);

        res = db.findReviews(p.getProduct_id());
        System.out.println(res);

        ArrayList<Reviews> reviews = new ArrayList<Reviews>();
        while (res.next()){
            Reviews rev = new Reviews();

            rev.setUsername(res.getString("username"));
            rev.setDate(res.getString("date_review"));
            rev.setReview(res.getString("review"));

            reviews.add(rev);
        }

        p.setReviews(reviews);
        return p;
    }

    public String save(String product_id, String name, String review) throws SQLException, BiorganSqlException {
        if (review.trim().length() < 3 || review.trim().length() > 500)
            return "1";

        db.postReview(product_id, name, review);
        return "0";
    }
}
