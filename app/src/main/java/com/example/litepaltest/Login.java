package com.example.litepaltest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class Login extends BaseActivity {

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    private Button tong;

    private CheckBox rememberPass;

    private Button music;

    private Button video;

    private Button http;
    String channelId;

    String cececeshishishiee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);



        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        Button zhuce=(Button)findViewById(R.id.zc) ;


        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                //Connector.getDatabase();
            }
        });


        tong = (Button)findViewById(R.id.tong);
        tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, TongActivity.class);
                startActivity(intent);

            }
        });


        music = (Button)findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, musicActivity.class);
                startActivity(intent);
            }
        });


        video = (Button)findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, VideoActivity.class);
                startActivity(intent);
            }
        });


        http = (Button)findViewById(R.id.http);
        http.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, HttpActivity.class);
                startActivity(intent);
            }
        });







        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();



                //通知

                switch (v.getId()) {
                    case R.id.login:
                        Intent intent = new Intent(Login.this, Login.class);
                        PendingIntent pi = PendingIntent.getActivity(Login.this, 0, intent, 0);
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(Login.this)
                                .setContentTitle("个人信息系统通知")
                                .setContentText("点击进入APP首页")
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                .setContentIntent(pi)

                                //        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                                //        .setVibrate(new long[]{0, 1000, 1000, 1000})
                                //        .setLights(Color.GREEN, 1000, 1000)
                                .setDefaults(NotificationCompat.DEFAULT_ALL)
                                //        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                               // .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
                                .setPriority(NotificationCompat.PRIORITY_MAX)
                                .build();
                        manager.notify(1, notification);
                        break;
                    default:
                        break;
                }


                //通知












                //Book book=DataSupport.where("userr=? and password=?",account,password);;
                List<Book> books = DataSupport.where("userr=?",account).find(Book.class);
               // List<Book> books = DataSupport.findAll(Book.class);

                if (books.isEmpty()){Toast.makeText(Login.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                }
               // Log.d("Login", "book name is " + books);
                for (Book book: books) {
                    //Log.d("Login", "book name is " + book.getUserr());
                    if (password.equals(book.getPassword())){
               // if (account.equals(book.getUserr()) && password.equals(book.getPassword())) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) { // 检查复选框是否被选中
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(Login.this, xinxiActivity.class);
                    intent.putExtra("sex",account);
                    startActivity(intent);
                    finish();
                }
                else{
                        Toast.makeText(Login.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }


                }


            }

        });
    }
}
