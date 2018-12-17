package com.example.litepaltest;

import org.litepal.crud.DataSupport;

public class Hobby extends DataSupport {
    private int id;
    private String userr;
    private String hobby;

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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
