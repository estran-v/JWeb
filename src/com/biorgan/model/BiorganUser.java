package com.biorgan.model;

/**
 * Created by wilyr on 11/7/2015.
 */
public class BiorganUser {

    private String fname;
    private String lname;
    private String mail;
    private boolean isAdmin;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMail() {
        return mail;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public BiorganUser(String fname, String lname, String mail, boolean isAdmin)
    {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.isAdmin = isAdmin;
    }
}
