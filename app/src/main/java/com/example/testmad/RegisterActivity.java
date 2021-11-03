package com.example.testmad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testmad.models.User;
import com.example.testmad.models.UserFactory;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    User user;
    ArrayList<User> userArrayList = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_menu);

        init();

        insertData();
    }

    EditText etFullName, etEmail, etPassword, etPasswordConfirm;
    Button btnRegister, btnLogin;
    TextView msgFullName, msgPassword, msgGender, msgPasswordConfirm;

    private void init(){
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        btnRegister = findViewById(R.id.btnRegister);
        msgFullName = findViewById(R.id.msgFullName);
        msgPassword = findViewById(R.id.msgPassword);
        msgGender = findViewById(R.id.msgGender);
        msgPasswordConfirm = findViewById(R.id.msgPasswordConfirm);
        btnLogin = findViewById(R.id.btnLogin);
    }

    String gender="";
    String fullName,email,password, passwordConfirm;

    private void toLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                setIntent(intent);
            }
        });
    }

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
                boolean validation4 = false;
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

                if(validation1 == true && validation2 == true && validation3 == true && validation4 == true){
                    ArrayList<User> users = new UserFactory().getUsers();
                    int userId = 0;
                    String id = "";
                    if(users.isEmpty()){
                        userId = 1;
                    }else{
                        for(int i = 0 ; i < users.size() ; i++){
                            userId += 1;
                        }
                    }

                    if(userId < 10){
                        id = "US00" + userId;
                    }else if(userId >= 10 && userId < 100){
                        id = "US0" + userId;
                    }else{
                        id = "US" + userId;
                    }

                    User user = new UserFactory().userCreate(id, fullName, email, password, gender, "Users");
                    new UserFactory().insertUser(user);
                    intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                
            }
        });
    }
}
