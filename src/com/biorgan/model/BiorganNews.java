package com.biorgan.model;

import java.util.Date;

/**
 * Created by wilyr on 11/7/2015.
 */
public class BiorganNews {
    String title;
    String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    Date date;

    public BiorganNews(String title, String content, Date date){
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
