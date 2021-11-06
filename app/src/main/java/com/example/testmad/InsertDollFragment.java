package com.example.testmad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testmad.models.Doll;
import com.example.testmad.models.DollFactory;
import com.example.testmad.models.User;

public class InsertDollFragment extends Fragment {

    DrawerLayout drawerLayout;
    ViewGroup vg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vg = (ViewGroup)inflater.inflate(R.layout.insert_doll_fragment, container, false);
        init();
        createDoll();
        return vg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.insert_doll_fragment);
//        drawerLayout = findViewById(R.id.drawer_layout);

    }

    EditText etDollName, etDollDescription;
    Button btnSaveDoll;
    String dollName, dollDescription;
    int dollImage;
    User user;
    Doll doll;
    private void init(){
        etDollName = vg.findViewById(R.id.etDollName);
        etDollDescription = vg.findViewById(R.id.etDollDescription);
        btnSaveDoll = vg.findViewById(R.id.btnSaveDoll);
        user = (User) getActivity().getIntent().getSerializableExtra("User");
    }

    private void createDoll(){
        btnSaveDoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dollName = etDollName.getText().toString();
                dollDescription = etDollDescription.getText().toString();
                doll = new DollFactory().createDoll(dollName, dollDescription, user);
                new DollFactory().insertDolls(doll);
                Toast.makeText(getContext(), "Doll insert success", Toast.LENGTH_LONG);
            }
        });
    }
}
