package com.example.litepaltest;

import org.litepal.crud.DataSupport;

public class Book extends DataSupport {

    private int id;
    private String userr;
    private String password;
    private String sexx;

    public String getSexx() {
        return sexx;
    }

    public void setSexx(String sexx) {
        this.sexx = sexx;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserr() {
        return userr;
    }

    public void setUserr(String userr) {
        this.userr = userr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
