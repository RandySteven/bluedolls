package com.example.testmad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.testmad.models.Doll;
import com.example.testmad.models.DollFactory;
import com.example.testmad.models.User;

public class CreateDollActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_doll);
        drawerLayout = findViewById(R.id.drawer_layout);
        init();
        createDoll();
    }

    EditText etDollName, etDollDescription;
    Button btnSaveDoll;
    String dollName, dollDescription;
    User user;
    Doll doll;
    private void init(){
        etDollName = findViewById(R.id.etDollName);
        etDollDescription = findViewById(R.id.etDollDescription);
        btnSaveDoll = findViewById(R.id.btnSaveDoll);
        user = (User) getIntent().getSerializableExtra("user");
    }

    private void createDoll(){
        btnSaveDoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dollName = etDollName.getText().toString();
                dollDescription = etDollDescription.getText().toString();
                doll = new DollFactory().createDoll(dollName, dollDescription, user);
                new DollFactory().insertDolls(doll);
                Intent intent = new Intent(CreateDollActivity.this, MainActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
    }
}
