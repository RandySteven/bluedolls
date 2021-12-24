package com.example.a2301876316;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2301876316.models.Doll;
import com.example.a2301876316.models.User;
import com.example.a2301876316.factory.UserFactory;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    DataHelper dataHelper = null;
    User user;
    List<User> users;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_menu);

        init();

        if(users.isEmpty()){
            User admin = new User("AA001", "Admin", "admin21@gmail.com", "admin1234", "Male", "Admin");
            users.add(admin);
        }
        insertData();
        toLogin();
    }

    EditText etFullName, etEmail, etPassword, etPasswordConfirm;
    Button btnRegister, btnLogin;
    TextView msgFullName, msgEmail, msgPassword, msgGender, msgPasswordConfirm, msgCheckBoxTermService;
    CheckBox checkBoxTermService;

    private void init(){
        if(dataHelper == null){
            dataHelper = new DataHelper(this);
        }
        users = dataHelper.getAllUsers();
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        checkBoxTermService = findViewById(R.id.checkboxTermService);
        btnRegister = findViewById(R.id.btnRegister);
        msgFullName = findViewById(R.id.msgFullName);
        msgEmail = findViewById(R.id.msgEmail);
        msgPassword = findViewById(R.id.msgPassword);
        msgGender = findViewById(R.id.msgGender);
        msgPasswordConfirm = findViewById(R.id.msgPasswordConfirm);
        msgCheckBoxTermService = findViewById(R.id.msgCheckBoxTermService);
        btnLogin = findViewById(R.id.btnLogin);
    }

    String gender="";
    String fullName,email,password, passwordConfirm;

    private void toLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    int userId;

    private void insertData(){

        RadioGroup genders = findViewById(R.id.radioGenders);
        genders.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_male:
                        gender = "Male";
                        break;
                    case R.id.radio_female:
                        gender = "Female";
                        break;
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fullName = etFullName.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                passwordConfirm = etPasswordConfirm.getText().toString();
                boolean validation1 = false;boolean validation2 = false;boolean validation3 = false;
                boolean validation4 = false;boolean validation5 = false;boolean validation6 = false;
                if(fullName.length() != 0){
                    validation1 = true;
                    msgFullName.setText(null);
                }

                if(password.length() != 0 && password.length() >= 6){
                    validation2 = true;
                    msgPassword.setText(null);
                }

                if(password.equals(passwordConfirm)){
                    validation3 = true;
                    msgPasswordConfirm.setText(null);
                }

                if(gender.equals("Male") || gender.equals("Female")){
                    validation4 = true;
                    msgGender.setText(null);
                }

                if(checkBoxTermService.isChecked()){
                    validation5 = true;
                    msgCheckBoxTermService.setText(null);
                }

                if(validation1 == false){
                    msgFullName.setText("Full Name must required");
                }

                if(validation2 == false){
                    msgPassword.setText("Password must required at least 6 characters");
                }

                if(validation3 == false){
                    msgPasswordConfirm.setText("Password confirm and password must match");
                }

                if(validation4 == false){
                    msgGender.setText("Gender must selected");
                }

                if(validation5 == false){
                    msgCheckBoxTermService.setText("Must checked");
                }

                if(validation1 == true && validation2 == true &&
                        validation3 == true && validation4 == true &&
                        validation5 == true){
                    user = new UserFactory().userCreate(users, fullName, email, password, gender, "Users");
                    dataHelper.addUser(user);
                    intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                
            }
        });
    }
}
