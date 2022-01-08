package com.example.basiccontacts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView PhV;
    TextView EmV;
    TextView NaV;
    String[] dats;
    CardView dp;
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
        dp = findViewById(R.id.secdp);
        NaV.setText(dats[0]);
        EmV.setText(dats[1]);
        PhV.setText(dats[2]);
    }
//    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @RequiresApi(api = Build.VERSION_CODES.M)
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        // There are no request codes
//                        Intent data = result.getData();
//                        final Bundle extras = data.getExtras();
//                        Bitmap ProfilePic = extras.getParcelable("data");
//                        Canvas canvas = new Canvas(ProfilePic);
//                        canvas.scale(60, 60);
//                        dp.onDrawForeground(canvas);
//                    }
//                }
//            });

    public void setdp(View view){
        Toast.makeText(this, "DP CHANGE OPTION OPENING...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
//        someActivityResultLauncher.launch(intent);
        startActivityForResult(intent, 1);
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
}