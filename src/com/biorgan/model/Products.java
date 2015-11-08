package com.biorgan.model;

import java.util.ArrayList;

/**
 * Created by Vincent on 07/11/2015.
 */
public class Products {
    private String product_id;
    private String product_name;
    private String product_stock;
    private String product_desc;
    private String product_price;
    private ArrayList<Reviews> reviews = new ArrayList<Reviews>();

    public void setProduct_name(String name){
        this.product_name = name;
    }

    public String getProduct_name(){
        return this.product_name;
    }

    public void setProduct_id(String id){
        this.product_id = id;
    }

    public String getProduct_id(){
        return this.product_id;
    }

    public void setProduct_stock(String stock){
        this.product_stock = stock;
    }

    public String getProduct_stock(){
        return this.product_stock;
    }

    public void setProduct_desc(String desc){
        this.product_desc = desc;
    }

    public String getProduct_desc(){
        return this.product_desc;
    }

    public void setProduct_price(String price){
        this.product_price = price;
    }

    public String getProduct_price(){
        return this.product_price;
    }

    public ArrayList<Reviews> getReviews() { return this.reviews; }

    public void setReviews(ArrayList<Reviews> reviews) { this.reviews = reviews; }
}
