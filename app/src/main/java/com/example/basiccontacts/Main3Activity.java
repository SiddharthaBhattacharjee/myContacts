package com.example.basiccontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private EditText nameV;
    private EditText emailV;
    private EditText numberV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void save(View view){
        nameV = findViewById(R.id.iName);
        emailV = findViewById(R.id.iEmail);
        numberV = findViewById(R.id.iPhone);
        String n = nameV.getText().toString();
        String e = emailV.getText().toString();
        String p = numberV.getText().toString();
        if(n.equals("") || e.equals("") || p.equals("")){
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        else {
            String d = n + "/" + e + "/" + p;
            MainActivity.data.add(d);
            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}