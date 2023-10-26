package com.lokesh.loginsignup.afterlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;
import com.lokesh.loginsignup.R;
import com.lokesh.loginsignup.adapter.listViewAdapter;
import com.lokesh.loginsignup.database.dataBaseHelper;
import com.lokesh.loginsignup.database.userEntity;
import com.lokesh.loginsignup.self_define.hashCode;
import com.lokesh.loginsignup.signup_layout;

import java.util.ArrayList;
import java.util.List;

public class mainpage_layout extends AppCompatActivity {
    TextInputEditText txt_edit_first_name,txt_edit_last_name,txt_edit_email,
            txt_edit_phone_no,txt_edit_username,
            txt_match_password,txt_reset_password,txt_reset_username;



    Toolbar toolbar;
    Button btn_edit_detail,btn_reset_password;


    ListView listview;

    List<userEntity>  data =new  ArrayList<userEntity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_layout);

        listview = findViewById(R.id.listview);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().isHideOnContentScrollEnabled();
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        dataBaseHelper databaseHelper = dataBaseHelper.getDB(this);


        data = databaseHelper.userEntityDao().getAllItem();

        listViewAdapter listviewAdapter = new listViewAdapter(this,data);

        listview.setAdapter(listviewAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Dialog dialog = new Dialog(mainpage_layout.this);
                dialog.setContentView(R.layout.user_detail_edit_layout);




                btn_edit_detail = dialog.findViewById(R.id.btn_edit_detail);

                txt_edit_first_name = dialog.findViewById(R.id.txt_edit_first_name);
                txt_edit_last_name = dialog.findViewById(R.id.txt_edit_last_name);
                txt_edit_email = dialog.findViewById(R.id.txt_edit_email);
                txt_edit_phone_no = dialog.findViewById(R.id.txt_edit_phone_no);
                txt_edit_username = dialog.findViewById(R.id.txt_edit_username);


                txt_edit_first_name.setText(data.get(i).getFirstName());
                txt_edit_last_name.setText(data.get(i).getLastName());
                txt_edit_username.setText(data.get(i).getUsername());
                txt_edit_email.setText(data.get(i).getEmail());
                txt_edit_phone_no.setText(data.get(i).getPhone_NO());


                btn_edit_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        if(isEmailValidate(txt_edit_email.getText().toString().trim()) && isFirstNameValidate(txt_edit_first_name.getText().toString().trim()) &&
                        isLastNameValidate(txt_edit_last_name.getText().toString().trim()) && isPhoneNoValidate(txt_edit_phone_no.getText().toString().trim())){
                            data.get(i).setFirstName(txt_edit_first_name.getText().toString().trim());
                            data.get(i).setLastName(txt_edit_last_name.getText().toString().trim());
                            data.get(i).setEmail(txt_edit_email.getText().toString().trim());
                            data.get(i).setPhone_NO(txt_edit_phone_no.getText().toString().trim());

                            databaseHelper.userEntityDao().update(data.get(i));
                            listviewAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }








                    }
                });

                dialog.show();

            }
        });


        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();

        if (itemid == R.id.log_out){
            Intent iStart = new Intent(mainpage_layout.this, signup_layout.class);
            startActivity(iStart);
            finish();
        } else if (itemid == R.id.reset_password) {
            Intent get_data_intent = getIntent();
            String username = get_data_intent.getStringExtra("username");
            dialog_reset_password(dataBaseHelper.getDB(this),username);


        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isPhoneNoValidate(String phone_no) {
        if(phone_no.isEmpty()){
            txt_edit_phone_no.setError("Enter Phone Number");
            return false;
        } else if (phone_no.length()>10 | phone_no.length()<10) {
            txt_edit_phone_no.setError("Enter Correct Phone NO");
            return  false;
        }else {
            txt_edit_phone_no.setError(null);
            return  true;
        }

    }

    private boolean isFirstNameValidate(String first_name) {
        if(first_name.isEmpty()){
            txt_edit_first_name.setError("Enter Last Name");
            return false;
        }else {
            txt_edit_first_name.setError(null);
            return true;
        }


    }

    private boolean isLastNameValidate(String last_name) {

        if(last_name.isEmpty()){
            txt_edit_last_name.setError("Enter Last Name");
            return false;
        }else {
            txt_edit_last_name.setError(null);
            return true;
        }
    }

    private boolean isEmailValidate( String email) {
        String checkEmail = "[a-zA-z0-9._]+@[a-z]+.+[a-z]+";

        if (email.isEmpty()){
            txt_edit_email.setError("Field can not be empty");
            return false;
        } else if (!email.matches(checkEmail)) {
            txt_edit_email.setError("Invalid Email");
            return false;
        } else {
            txt_edit_email.setError(null);
            return true;

        }

    }


    private void dialog_reset_password(dataBaseHelper databaseHelper,String username){

        Dialog dialog = new Dialog(mainpage_layout.this);
        dialog.setContentView(R.layout.reset_password_layout);


        btn_reset_password = dialog.findViewById(R.id.btn_reset_password);
        txt_reset_password = dialog.findViewById(R.id.txt_reset_password);
        txt_match_password = dialog.findViewById(R.id.txt_match_password);
        txt_reset_username = dialog.findViewById(R.id.txt_reset_username);


        txt_reset_username.setText(username);


        btn_reset_password.setOnClickListener(view -> {
            if(ispasswordmatch(txt_reset_password.getText().toString().trim(),txt_match_password.getText().toString().trim())){
                databaseHelper.userEntityDao().resetPassword(username,new hashCode().getHashCode(txt_reset_password.getText().toString().trim()));
                dialog.dismiss();
            }

        });


        dialog.show();


    }

    private boolean ispasswordmatch(String password,String match_password){
        if(password.matches(match_password)){
            txt_reset_password.setError(null);
            txt_match_password.setError(null);
            return true;

        }
        else {
            txt_reset_password.setError("Password does not Match");
            txt_match_password.setError("Password does not Match");
            return false;
        }
    }




}