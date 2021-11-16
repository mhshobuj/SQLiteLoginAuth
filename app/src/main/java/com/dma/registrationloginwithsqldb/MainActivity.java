package com.dma.registrationloginwithsqldb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText category_name, sub_category_name, name, phone, address, date;
    Button submit_bt, View_bt;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category_name = findViewById(R.id.category_name);
        sub_category_name = findViewById(R.id.sub_category_name);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        date = findViewById(R.id.date);

        submit_bt = findViewById(R.id.submit_bt);
        View_bt = findViewById(R.id.View_bt);

        dbHelper = new DBHelper(this);

        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat_name = category_name.getText().toString();
                String sub_cat_name = sub_category_name.getText().toString();
                String name_user = name.getText().toString();
                String phone_user = phone.getText().toString();
                String address_user = address.getText().toString();
                String date_user = date.getText().toString();

                if (cat_name.equals("") || sub_cat_name.equals("") || name_user.equals("") ||
                        phone_user.equals("") || address_user.equals("") || date_user.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all field", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkUserData = dbHelper.insertUserData(cat_name,sub_cat_name, name_user, phone_user, address_user, date_user);
                    if (checkUserData){
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        View_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getData();
                if (cursor.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_LONG).show();
                    return;
                }

                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()){
                    stringBuffer.append("Cat_Name: "+cursor.getString(0)+ "\n");
                    stringBuffer.append("Sub_Cat_Name: "+cursor.getString(1)+ "\n");
                    stringBuffer.append("Name: "+cursor.getString(2)+ "\n");
                    stringBuffer.append("Phone: "+cursor.getString(3)+ "\n");
                    stringBuffer.append("Address: "+cursor.getString(4)+ "\n");
                    stringBuffer.append("Date: "+cursor.getString(5)+ "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Details");
                builder.setMessage(stringBuffer.toString());
                builder.show();
            }
        });
    }
}