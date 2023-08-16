package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button save = findViewById(R.id.btn_save);
        DatabaseHelper DB = new DatabaseHelper(this);

        Intent intent = getIntent();
        String usr = intent.getStringExtra("username");

        EditText currPass = findViewById(R.id.txt_currPass);
        EditText newPass = findViewById(R.id.txt_newpass);
        EditText confPass = findViewById(R.id.txt_confpass);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPassword = currPass.getText().toString();
                String newPassword = newPass.getText().toString();
                String confirmPassword = confPass.getText().toString();

                if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields Cannot Be Blank", Toast.LENGTH_LONG).show();
                } else {
                    Boolean isCurrentPasswordValid = DB.checkusernamepassword(usr, currentPassword);
                    if (isCurrentPasswordValid) {
                        if (newPassword.equals(confirmPassword)) {
                            boolean isPasswordUpdated = DB.updatePassword(usr, newPassword);
                            if (isPasswordUpdated) {
                                Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to Update Password", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "New Passwords Do Not Match", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Current Password Does Not Match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void backtouserpage(View v){
        Intent i= new Intent("act.userpage");
        startActivity(i);
    }
}