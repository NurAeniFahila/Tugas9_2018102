package com.example.a2018102_tugas5_recyclerview;


import static com.example.a2018102_tugas5_recyclerview.DBmain.TABLEDEVISI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.a2018102_tugas5_recyclerview.databinding.ActivityDisplayItemBinding;

import java.util.ArrayList;

public class DisplayItem extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    ItemAdapter myAdapter;
    private ActivityDisplayItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =
                ActivityDisplayItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        findId();
        dBmain = new DBmain(this);
        displayData();
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DisplayItem.this, ManageItem.class);
                startActivity(a);
            }
        });
    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLEDEVISI, null);
        ArrayList<Model> models = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String devisi = cursor.getString(1);
            byte[] avatar = cursor.getBlob(4);
            String star = cursor.getString(2);
            String join = cursor.getString(3);
            models.add(new Model(id, avatar, devisi, star, join));
        }
        cursor.close();
        myAdapter = new ItemAdapter(this,
                R.layout.singledata, models, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findId() {
        recyclerView = findViewById(R.id.rv);
    }
}