package com.dma.registrationloginwithsqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText email_et,password_et;
    Button logIn_bt;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        db = new DBHelper(this);

        email_et = findViewById(R.id.email_et);
        password_et = findViewById(R.id.password_et);

        logIn_bt = findViewById(R.id.logIn_bt);

        logIn_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_et.getText().toString();
                String password = password_et.getText().toString();

                if (email.equals("") || password.equals("")){
                    Toast.makeText(SignInActivity.this, "Please enter all field", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkEmailPass = db.checkedEmailPassword(email, password);
                    if (checkEmailPass){
                        Toast.makeText(SignInActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}