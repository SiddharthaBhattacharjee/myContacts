package com.example.basiccontacts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final List<String> data = new ArrayList<String>();
    ListView listview;
    public static boolean reloadNedeed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.mylv);
        SharedPreferences sp = getSharedPreferences("contacts",MODE_PRIVATE);
        String spdata = sp.getString("data",null);
        if(spdata != null){
            data.clear();
            String[] spdataspl = spdata.split(",",-1);
            for(String a:spdataspl){
                data.add(a);
            }
        }
        myAdapter ad = new myAdapter(this,R.layout.mylayout,data);
        listview.setAdapter(ad);
    }
    @Override
    public void onResume() {
        super.onResume();

        if (this.reloadNedeed){
            this.reloadProfileData();
            SharedPreferences sp = getSharedPreferences("contacts",MODE_PRIVATE);
            String spdata = sp.getString("data",null);
            if(spdata != null){
                data.clear();
                String[] spdataspl = spdata.split(",",-1);
                for(String a:spdataspl){
                    data.add(a);
                }
            }
        }

        this.reloadNedeed = false; // do not reload anymore, unless I tell you so...
    }
    public void add(View view){
        Intent i = new Intent(this,Main3Activity.class);
        startActivity(i);
    }
    public void reloadProfileData(){
        recreate();
    }
}