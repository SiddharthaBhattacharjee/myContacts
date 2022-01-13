package com.example.basiccontacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView PhV;
    TextView EmV;
    TextView NaV;
    String[] dats;
    ImageView dp;
    private static final int SELECT_PHOTO = 100;
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
        dp = (ImageView) findViewById(R.id.profile_image);
        NaV.setText(dats[0]);
        EmV.setText(dats[1]);
        PhV.setText(dats[2]);
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
        List<String> data2 = MainActivity.data;
        data2.remove(p);
        StringBuilder tms = new StringBuilder();
        if(!data2.isEmpty()){
            for(String ts : data2){
                tms.append(ts+",");
            }
            tms.deleteCharAt(tms.length()-1);
            SharedPreferences sp = getSharedPreferences("contacts",MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("data",tms.toString());
            ed.apply();
        }
        else{
            SharedPreferences sp = getSharedPreferences("contacts",MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("data",null);
            ed.apply();
        }
        MainActivity.reloadNedeed = true;
        finish();
    }
    public void EditPhn(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setTitle("Edit Phone-Number");
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String t = input.getText().toString();
                PhV.setText(t);
                dats[2] = t;
                int p = Integer.parseInt(dats[3]);
                String d = dats[0]+"/"+dats[1]+"/"+t+"/"+dats[3];
                MainActivity.data.set(p,d);
                StringBuilder tms = new StringBuilder();
                for(String ts : MainActivity.data){
                    tms.append(ts+",");
                }
                tms.deleteCharAt(tms.length()-1);
                SharedPreferences sp = getSharedPreferences("contacts",MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("data",tms.toString());
                ed.apply();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        MainActivity.reloadNedeed = true;
    }
    public void EditEml(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setTitle("Edit Email");
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String t = input.getText().toString();
                EmV.setText(t);
                dats[1] = t;
                int p = Integer.parseInt(dats[3]);
                String d = dats[0]+"/"+t+"/"+dats[2]+"/"+dats[3];
                MainActivity.data.set(p,d);
                StringBuilder tms = new StringBuilder();
                for(String ts : MainActivity.data){
                    tms.append(ts+",");
                }
                tms.deleteCharAt(tms.length()-1);
                SharedPreferences sp = getSharedPreferences("contacts",MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("data",tms.toString());
                ed.apply();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        MainActivity.reloadNedeed = true;
    }
    public void EditName(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setTitle("Edit Name");
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String t = input.getText().toString();
                NaV.setText(t);
                dats[0] = t;
                int p = Integer.parseInt(dats[3]);
                String d = t+"/"+dats[1]+"/"+dats[2]+"/"+dats[3];
                MainActivity.data.set(p,d);
                StringBuilder tms = new StringBuilder();
                for(String ts : MainActivity.data){
                    tms.append(ts+",");
                }
                tms.deleteCharAt(tms.length()-1);
                SharedPreferences sp = getSharedPreferences("contacts",MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("data",tms.toString());
                ed.apply();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        MainActivity.reloadNedeed = true;
    }
    public void sendsms(View view){

        Intent sInt = new Intent(Intent.ACTION_VIEW);
        sInt.setData(Uri.parse("smsto:"));
        sInt.setType("vnd.android-dir/mms-sms");
        sInt.putExtra("address", dats[2] );
        sInt.putExtra("sms_body","Hello There !");
        startActivity(Intent.createChooser(sInt, "Send sms via:"));

    }
}