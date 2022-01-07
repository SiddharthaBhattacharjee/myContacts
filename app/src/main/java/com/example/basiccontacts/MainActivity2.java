package com.example.basiccontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView PhV;
    TextView EmV;
    TextView NaV;
    String[] dats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        String vals = i.getStringExtra(myAdapter.key);
        dats = vals.split("/",-1);
        PhV = findViewById(R.id.PhoneD);
        EmV = findViewById(R.id.EmailD);
        NaV = findViewById(R.id.NameD);
        NaV.setText(dats[0]);
        EmV.setText(dats[1]);
        PhV.setText(dats[2]);
    }

    public void setdp(View view){
        Toast.makeText(this, "DP CHANGE OPTION OPENING...", Toast.LENGTH_SHORT).show();
    }
    public void makeCall(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",dats[2], null));
        startActivity(intent);
    }
    public void makeEmail(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL,new String[]{dats[1]});
        startActivity(i);
    }
    public void DelCon(View view){
        int p = Integer.parseInt(dats[3]);
        MainActivity.data.remove(p);
        finish();
    }
}