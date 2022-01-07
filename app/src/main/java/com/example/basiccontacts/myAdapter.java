package com.example.basiccontacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class myAdapter extends ArrayAdapter<String> {
    private List<String> dat;
    public static final String key = "com.example.basiccontacts.myAdapter.key_6392";

    public myAdapter(@NonNull Context context, int resource, @NonNull List<String> dat) {
        super(context, resource, dat);
        this.dat = dat;
    }


    @Nullable
    @Override
    public String getItem(int position) {
        return dat.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.mylayout,parent,false);
        TextView na = convertView.findViewById(R.id.oName);
        TextView em = convertView.findViewById(R.id.oMail);
        TextView ph = convertView.findViewById(R.id.oNum);
        String temp = dat.get(position);
        String[] dats = temp.split("/",-1);
        try{na.setText(dats[0]);}catch(Exception e){na.setText("404");}
        try{em.setText(dats[1]);}catch(Exception e){na.setText("404");}
        try{ph.setText(dats[2]);}catch(Exception e){na.setText("404");}
        ImageButton emb = convertView.findViewById(R.id.mailB);
        ImageButton phb = convertView.findViewById(R.id.callB);
        emb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL,new String[]{dats[1]});
                getContext().startActivity(i);
            }
        });
        phb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", dats[2], null));
                getContext().startActivity(intent);
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_2 = new Intent(getContext(),MainActivity2.class);
                intent_2.putExtra(key,temp+"/"+position);
                getContext().startActivity(intent_2);
            }
        });

        return convertView;
    }
}
