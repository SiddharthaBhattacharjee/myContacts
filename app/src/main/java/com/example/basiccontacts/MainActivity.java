package com.example.basiccontacts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final List<String> data = new ArrayList<String>();
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.mylv);
        myAdapter ad = new myAdapter(this,R.layout.mylayout,data);
        listview.setAdapter(ad);
    }

    public void add(View view){
        Intent i = new Intent(this,Main3Activity.class);
        startActivity(i);
    }
}