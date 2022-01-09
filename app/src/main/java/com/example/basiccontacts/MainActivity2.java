package com.example.basiccontacts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

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
    //here
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        final Bundle extras = data.getExtras();
//                        Bitmap ProfilePic = extras.getParcelable("data");
                        Log.i("data", String.valueOf(data));
                        Log.i("extras", String.valueOf(extras));
//                        dp.setImageBitmap(ProfilePic);
                        File image = new  File(Environment.getExternalStorageDirectory() +"/Frag_list/"+extras+".jpg");
                        Log.i("image", String.valueOf(image));
                        if(image.exists()){
                            dp.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));/////////////setting image in imageview
                        }
                    }
                }
            });
    //here
private void requestStoragePermission() {
    final String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
        ActivityCompat.requestPermissions(this, permissions, 1000);
        return;
    }
}
//here
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadImageInImageview();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Allow permission.")
                        .setMessage("Please....")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();
            }
        }
    }
//here
    private void loadImageInImageview() {
//        Toast.makeText(this, "DP CHANGE OPTION OPENING...", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("scale", true);
//        intent.putExtra("outputX", 256);
//        intent.putExtra("outputY", 256);
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("return-data", true);
//        someActivityResultLauncher.launch(intent);
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
        someActivityResultLauncher.launch(photoPickerIntent);
    }

    public void setdp(View view){
//        Toast.makeText(this, "DP CHANGE OPTION OPENING...", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("scale", true);
//        intent.putExtra("outputX", 256);
//        intent.putExtra("outputY", 256);
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("return-data", true);
//        someActivityResultLauncher.launch(intent);
//        startActivityForResult(intent, 1);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestStoragePermission();
        } else {
            loadImageInImageview();
        }
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