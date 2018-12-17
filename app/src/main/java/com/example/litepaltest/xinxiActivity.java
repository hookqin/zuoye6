package com.example.litepaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class xinxiActivity extends BaseActivity{

    private Button xia;
    String kkk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinxi);
        TextView lis = (TextView) findViewById(R.id.xixi);
        Intent intent = getIntent();
        String sex=intent.getStringExtra("sex");
        List<Book> books = DataSupport.where("userr=?",sex).find(Book.class);
        List<Hobby> hobbys = DataSupport.where("userr=?",sex).find(Hobby.class);

        for (Book book: books) {
            kkk="用户名：" + book.getUserr() +"\n密码："+ book.getPassword()+"\n性别："+book.getSexx();
        }

        for (Hobby hobby: hobbys){
            lis.setText(kkk+"\n爱好："+hobby.getHobby());
        }

        xia=(Button) findViewById(R.id.xia);
        xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });



    }
}
