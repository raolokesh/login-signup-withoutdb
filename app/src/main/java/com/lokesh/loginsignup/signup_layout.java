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
import com.lokesh.loginsignup.database.userEntity;
import com.lokesh.loginsignup.self_define.hashCode;



public class signup_layout extends AppCompatActivity {

    TextInputEditText txt_first_name,txt_last_name,txt_phone_no,txt_email,txt_username,txt_password;
    Button btn_sign_up;
    TextView txt_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_layout);

        txt_first_name = findViewById(R.id.txt_first_name);
        txt_last_name = findViewById(R.id.txt_last_name);
        txt_email = findViewById(R.id.txt_email);
        txt_phone_no = findViewById(R.id.txt_phone_no);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        txt_login = findViewById(R.id.txt_login);

        dataBaseHelper databaseHelper = dataBaseHelper.getDB(this);

//
//        private boolean isLastNameValid(;){
//            String lastName = txt_last_name.getText().toString().trim();
//            return !lastName.isEmpty();
//        }
//
//        private boolean isEmailValid() {
//            String email = txt_email.getText().toString().trim();
//            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
//        }
//
//        private boolean isPasswordValid() {
//            String password = txt_password.getText().toString();
//            // Implement your password strength criteria here
//            // For example, check for minimum length, special characters, etc.
//            return password.length() >= 8; // Example: Minimum length of 8 characters
//        }

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = txt_first_name.getText().toString().trim();
                String last_name = txt_last_name.getText().toString().trim();
                String email = txt_email.getText().toString().trim();
                String phone_no = txt_phone_no.getText().toString();
                String username = txt_username.getText().toString().trim();
                String password = txt_password.getText().toString();

                if ( isFirstNameValidate(first_name) && isLastNameValidate(last_name) && isEmailValidate(email) && isPhoneNoValidate(phone_no) && isPasswordvalidate(password)) {

                    

                    if (!databaseHelper.userEntityDao().checkUsername(username)) {
                        databaseHelper.userEntityDao().
                                insert(new userEntity(first_name, last_name, email, phone_no, username, new hashCode().getHashCode(password)));

                        Intent iSignupNext = new Intent(signup_layout.this, mainpage_layout.class);
                        iSignupNext.putExtra("username",username);
                        startActivity(iSignupNext);
                        finish();
                    } else {
                        txt_username.setError("Choose Another Username");
                    }


                }
            }
        });
    }

    private boolean isPasswordvalidate(String password) {

//        String checkPassword = "^" +
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=S+$)" +           //no white spaces
//                ".{4,}" +               //at least 4 characters
//                "$";

        if(password.isEmpty()){
            txt_password.setError("Field can not be empty");
            return false;
//        } else if (!password.matches(checkPassword)) {
//            txt_password.setError("Password should contain 4 characters!");
//            return false;
        } else {
            txt_password.setError(null);
            return true;
        }
    }

    private boolean isPhoneNoValidate(String phone_no) {
        if(phone_no.isEmpty()){
            txt_phone_no.setError("Enter Phone Number");
            return false;
        } else if (phone_no.length()>10 | phone_no.length()<10) {
            txt_phone_no.setError("Enter Correct Phone NO");
            return  false;
        }else {
            txt_phone_no.setError(null);
            return  true;
        }

    }

    private boolean isFirstNameValidate(String first_name) {
        if(first_name.isEmpty()){
            txt_first_name.setError("Enter Last Name");
            return false;
        }else {
            txt_first_name.setError(null);
            return true;
        }


    }

    private boolean isLastNameValidate(String last_name) {

        if(last_name.isEmpty()){
            txt_last_name.setError("Enter Last Name");
            return false;
        }else {
            txt_last_name.setError(null);
            return true;
        }
    }

    private boolean isEmailValidate( String email) {
        String checkEmail = "[a-zA-z0-9._]+@[a-z]+.+[a-z]+";

        if (email.isEmpty()){
            txt_email.setError("Field can not be empty");
            return false;
        } else if (!email.matches(checkEmail)) {
            txt_email.setError("Invalid Email");
            return false;
        } else {
            txt_email.setError(null);
            return true;

        }

    }


    public void onLoginClick(View view) {
        Intent iLogin = new Intent(signup_layout.this, login_layout.class);
        startActivity(iLogin);
        finish();
    }

}