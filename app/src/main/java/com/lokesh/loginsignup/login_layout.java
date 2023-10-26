package com.lokesh.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.lokesh.loginsignup.afterlogin.mainpage_layout;
import com.lokesh.loginsignup.database.dataBaseHelper;
import com.lokesh.loginsignup.self_define.hashCode;

public class login_layout extends AppCompatActivity {

    TextInputEditText input_username,input_password;
    Button btn_login;
    TextView txt_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);
        btn_login = findViewById(R.id.btn_login);
        txt_signup = findViewById(R.id.txt_signup);


        dataBaseHelper databaseHelper = dataBaseHelper.getDB(this);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = input_username.getText().toString().trim();
                String password = input_password.getText().toString().trim();


                if(databaseHelper.userEntityDao().checkUsernamePassword(username, new hashCode().getHashCode(password))>0){
                    Intent iLogin = new Intent( login_layout.this, mainpage_layout.class);
                    iLogin.putExtra("username",username);
                    startActivity(iLogin);
                    finish();

                }
                else {
                    input_username.setError("Enter Correct username");
                    input_password.setError("Enter Correct password");
                }
            }
        });




        txt_signup.setOnClickListener(view -> {
            Intent iSignup = new Intent(login_layout.this,signup_layout.class);
            startActivity(iSignup);
            finish();
        });


    }


}