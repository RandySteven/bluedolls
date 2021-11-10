package com.example.a2301876316;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2301876316.models.User;
import com.example.a2301876316.models.UserFactory;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    static int index = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_menu);
        init();
        run();
    }

    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;
    private void init(){
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    String email, password;
    private void run(){
        ArrayList<User> users = new UserFactory().getUsers();
        for(int i = 0 ; i < users.size() ; i++){
            System.out.println(users.get(i).getUserEmail());
            System.out.println(users.get(i).getUserId());
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                boolean search = false;

                for(int i = 0 ; i < users.size() ; i++){
                    if(email.equals(users.get(i).getUserEmail()) && password.equals(users.get(i).getUserPassword())){
                        search = true;
                        index = i;
                    }
                }

                if(search == true) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    User user = new UserFactory().getUserByIndex(index);
                    intent.putExtra("User", user);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Email and password are wrong", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
