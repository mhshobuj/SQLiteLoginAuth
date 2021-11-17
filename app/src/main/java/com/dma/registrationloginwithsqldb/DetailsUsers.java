package com.dma.registrationloginwithsqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsUsers extends AppCompatActivity {

    int id;
    DBHelper dbHelper;
    TextView order_id, category, sub_category, name, phone, address, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_users);

        dbHelper = new DBHelper(this);

        order_id = findViewById(R.id.order_id);
        category = findViewById(R.id.category);
        sub_category = findViewById(R.id.sub_category);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        date = findViewById(R.id.date);

        if (getIntent() != null){
            id = getIntent().getIntExtra("ID", 0);
            Cursor cursor = dbHelper.getDataByID(String.valueOf(id));
            if (cursor.getCount() == 0){
                Toast.makeText(DetailsUsers.this, "Data Not Found", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                while (cursor.moveToNext()){
                    order_id.setText(new StringBuilder("Order ID: ").append(cursor.getString(0)));
                    category.setText(new StringBuilder("Category: ").append(cursor.getString(1)));
                    sub_category.setText(new StringBuilder("Sub_Category: ").append(cursor.getString(2)));
                    name.setText(new StringBuilder("Name: ").append(cursor.getString(3)));
                    phone.setText(new StringBuilder("Phone: ").append(cursor.getString(4)));
                    address.setText(new StringBuilder("Address: ").append(cursor.getString(5)));
                    date.setText(new StringBuilder("Date: ").append(cursor.getString(6)));
                }
            }
        }
        else {
            Toast.makeText(DetailsUsers.this, "Order ID not found", Toast.LENGTH_LONG).show();
        }
    }
}