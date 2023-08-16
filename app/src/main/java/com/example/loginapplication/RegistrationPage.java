package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity {
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        DB=new DatabaseHelper(this);
        EditText username=findViewById(R.id.txt_usernameReg);
        EditText emailReg=findViewById(R.id.txt_emailReg);
        EditText password=findViewById(R.id.txt_passwordReg);
        EditText confirmPass=findViewById(R.id.txt_confirmPassReg);
        Button registerBtn=(Button) findViewById(R.id.btn_register);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String email=emailReg.getText().toString();
                String pass=password.getText().toString();
                String Cpass=confirmPass.getText().toString();

                if (user.equals("") || pass.equals("") || email.equals("") || Cpass.equals("")) {

                    Toast.makeText(getApplicationContext(),"Field cannot be blank",Toast.LENGTH_LONG).show();
                }
                else {
                    if (pass.equals(Cpass)==true){
                        Boolean checkuser=DB.checkUsername(user);
                        if (checkuser==false){
                            Boolean insert= DB.insertData(user,email,pass);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Some problem in registering",Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"User with this username exists",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password does not Match",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }

    public void backtoMain(View v) {
        Intent i = new Intent(RegistrationPage.this, MainActivity.class);
        startActivity(i);
    }
}
