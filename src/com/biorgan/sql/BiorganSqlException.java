package com.biorgan.sql;

/**
 * Created by wilyr on 11/4/2015.
 */
public class BiorganSqlException extends Exception {
    String message = "";

    public BiorganSqlException(String err)
    {
        this.message = err;
    }

    @Override
    public String getMessage()
    {
        return (message);
    }

    @Override
    public void printStackTrace()
    {
        System.err.print(message);
    }
}
