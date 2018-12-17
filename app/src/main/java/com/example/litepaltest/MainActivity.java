package com.example.litepaltest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends BaseActivity {

    private MyDatabaseHelper dbHelper;
    private EditText yonghu;
    private EditText mima;
    private EditText aihao;
    String sexx;
    String xinxi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);

        final RadioButton radioButton1=(RadioButton)findViewById(R.id.man);
        final RadioButton radioButton2=(RadioButton)findViewById(R.id.woman);
        RadioGroup radioGroup1=(RadioGroup)findViewById(R.id.radio) ;
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Button button=findViewById(checkedId);
                if (checkedId==radioButton2.getId())
                    sexx="女";
                else
                    sexx="男";
            }
        });


        Button zhuce = (Button) findViewById(R.id.zhuce);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yonghu= (EditText) findViewById(R.id.account);
                mima= (EditText) findViewById(R.id.password);
                aihao= (EditText) findViewById(R.id.hobby);
                String user = yonghu.getText().toString();
                String password = mima.getText().toString();
                String aihaoo = aihao.getText().toString();

                Hobby hobby = new Hobby();
                hobby.setUserr(user);
                hobby.setHobby(aihaoo);
                hobby.save();

                Book book = new Book();
                book.setSexx(sexx);
                book.setUserr(user);
                book.setPassword(password);
                book.save();

                List<Book> books = DataSupport.findAll(Book.class);
                Log.d("MainActivity", "book name is " + book.getUserr());


                //下面是内容提供器加数据


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("name", user);
                values.put("author", password);
                values.put("pages", sexx);
                values.put("price", aihaoo);
                db.insert("Book", null, values); // 插入一条数据





                //内容提供器到此为止




                Toast.makeText(MainActivity.this, "注册成功，返回登录界面",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });

        TextView lis = (TextView) findViewById(R.id.lis);
        xinxi="";
        List<Book> books = DataSupport.findAll(Book.class);
        for (Book book: books) {
            xinxi=xinxi+"\n用户名：" + book.getUserr() +"密码："+ book.getPassword();
        }

        lis.setText(xinxi);



        }



    }

