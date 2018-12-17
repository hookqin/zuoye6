package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity {

    TextView responseText;
    private Button sendrequest;

    String shu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);


        sendrequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        //send_request.setOnClickListener(HttpActivity.);
        sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String address="http://www.baidu.com";
             //   String response=HttpUtil.sendHttpRequest(address);


                HttpUtil.sendHttpRequest("http://www.hao123.com", new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        shu=response.toString();
                        //showResponse(shu);

                    }
                    @Override
                    public void onError(Exception e) {
                        showResponse("访问出错");

                    }
                });


                HttpUtil.sendOkHttpRequest("http://www.baidu.com", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        showResponse("访问出错");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseData = response.body().string();
                        showResponse(responseData);
                    }
                });











            }
        });
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
}
