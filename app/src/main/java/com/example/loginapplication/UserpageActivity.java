package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserpageActivity extends AppCompatActivity {

    TextView txt_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        Intent intent=getIntent();
        txt_username= (TextView) findViewById(R.id.txt_usernameDIsplay);
        txt_username.setText("Welcome "+intent.getStringExtra("username").toUpperCase());
    }
    public void changePass(View v){
        Intent i=new Intent("act.changepass");
        String username=txt_username.getText().toString();
        i.putExtra("username",username);
        startActivity(i);

    }
    public void logout(View v){
        finishAffinity();
    }

}