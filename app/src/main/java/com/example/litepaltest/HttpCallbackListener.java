package com.example.litepaltest;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}