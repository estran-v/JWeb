package com.biorgan.model;

import com.biorgan.sql.*;

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Vincent on 07/11/2015.
 */
public class BiorganProducts {

    BiorganSQL db = new BiorganSQL("root", "root");

    public ArrayList<Products> getProducts() throws BiorganSqlException, SQLException {
        ResultSet res = db.getAllProducts();

        ArrayList<Products> list = new ArrayList<Products>();

        while (res.next()) {
            String id = res.getString("product_id");
            String name = res.getString("product_name");
            String stock = res.getString("product_stock");
            String desc = res.getString("product_desc");
            String price = res.getString("product_price");

            Products prod = new Products();
            prod.setProduct_id(id);
            prod.setProduct_name(name);
            prod.setProduct_stock(stock);
            prod.setProduct_desc(desc);
            prod.setProduct_price(price);

            list.add(prod);
        }
        return list;
    }
}
