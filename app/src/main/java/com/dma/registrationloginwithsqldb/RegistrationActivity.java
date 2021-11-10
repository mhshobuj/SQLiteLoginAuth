package com.dma.registrationloginwithsqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText email_et,password_et,rePassword_et;
    Button submit_bt, signIn_bt;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email_et = findViewById(R.id.email_et);
        password_et = findViewById(R.id.password_et);
        rePassword_et = findViewById(R.id.rePassword_et);

        submit_bt = findViewById(R.id.submit_bt);
        signIn_bt = findViewById(R.id.signIn_bt);

        db = new DBHelper(this);


        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_et.getText().toString();
                String password = password_et.getText().toString();
                String rePassword = rePassword_et.getText().toString();

                if (email.equals("") || password.equals("") || rePassword.equals("")){
                    Toast.makeText(RegistrationActivity.this, "Please enter all field", Toast.LENGTH_LONG).show();
                }
                else {
                    if (password.equals(rePassword)){
                        Boolean checkEmail = db.checkEmail(email);
                        if (!checkEmail){
                            Boolean insert = db.insertData(email, password);
                            if (insert){
                                Toast.makeText(RegistrationActivity.this, "Registration Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegistrationActivity.this, SignInActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "User already exits! please login", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Passwords not matching", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signIn_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}