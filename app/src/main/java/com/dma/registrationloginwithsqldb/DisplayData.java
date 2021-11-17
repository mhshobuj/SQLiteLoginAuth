package com.dma.registrationloginwithsqldb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayData extends AppCompatActivity {
    ArrayList<Model> arrayList;
    RecyclerView recyclerView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        arrayList = new ArrayList<>();

        Cursor cursor = dbHelper.getData();
        if (cursor.getCount() == 0){
            Toast.makeText(DisplayData.this, "Data Not Found", Toast.LENGTH_LONG).show();
            return;
        }
        while (cursor.moveToNext()){
            Model model = new Model(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            arrayList.add(model);
        }

        MyAdapter myAdapter = new MyAdapter(arrayList,this);
        recyclerView.setAdapter(myAdapter);


    }
}