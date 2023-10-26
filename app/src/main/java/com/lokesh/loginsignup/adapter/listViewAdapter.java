package com.lokesh.loginsignup.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lokesh.loginsignup.R;
import com.lokesh.loginsignup.database.userEntity;

import java.util.List;


public class listViewAdapter extends ArrayAdapter<userEntity> {

    TextView txt_name,txt_email,txt_phone_no;
    private Context context;
    LinearLayout llrow;


    public listViewAdapter(Context context, List<userEntity> data) {
        super(context, 0, data);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_detail_row, parent, false);
        }
         txt_name = convertView.findViewById(R.id.txt_name);
         txt_email = convertView.findViewById(R.id.txt_email);
         txt_phone_no = convertView.findViewById(R.id.txt_phone_no);
         llrow = convertView.findViewById(R.id.llrow);

        userEntity dataItem = getItem(position);

        if (dataItem != null) {
            txt_name.setText(dataItem.getFirstName() + " " + dataItem.getLastName());
            txt_email.setText("Email:- "+ dataItem.getEmail());
            txt_phone_no.setText("Contact:- "+dataItem.getPhone_NO());
        }

        return convertView;
    }




}