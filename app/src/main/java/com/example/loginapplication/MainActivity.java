package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DatabaseHelper(this);

        Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.txt_usernameDIsplay);
                EditText password = findViewById(R.id.txt_password);

                Boolean useraccept = DB.checkusernamepassword(username.getText().toString(), password.getText().toString());
                if (username.getText().toString().equals("")==true||password.getText().toString().equals("")==true){
                    Toast.makeText(getApplicationContext(), "Field Cannot be blank", Toast.LENGTH_LONG).show();
                }
                else {
                    if (useraccept) {
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, UserpageActivity.class);
                        i.putExtra("username",username.getText().toString());
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void reg(View v){
        Intent k=new Intent("act.regpage");
        startActivity(k);
    }
    public void exit(View v){
        finishAffinity();
    }
}