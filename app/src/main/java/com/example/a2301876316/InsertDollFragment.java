package com.example.a2301876316;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.a2301876316.models.Doll;
import com.example.a2301876316.models.DollFactory;
import com.example.a2301876316.models.User;

public class InsertDollFragment extends Fragment {

    DrawerLayout drawerLayout;
    ViewGroup vg;
    Doll doll;
    Integer dollImages[] = {
            R.drawable.teddy_bear,
            R.drawable.matryoshka_doll,
            R.drawable.girl_doll
    };
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
    }

    EditText etDollName, etDollDescription;
    Button btnSaveDoll;
    String dollName, dollDescription;
    TextView tvMessageDollName, tvMessageDollDescription;
    Spinner spnDollImage;
    User user;
    int dollImage;

    private void init(){
        etDollName = vg.findViewById(R.id.etDollName);
        etDollDescription = vg.findViewById(R.id.etDollDescription);
        btnSaveDoll = vg.findViewById(R.id.btnSaveDoll);
        spnDollImage = vg.findViewById(R.id.spnDollImage);
        SpinnerDollImageAdapater adapter = new SpinnerDollImageAdapater(this.getActivity(), dollImages);
        spnDollImage.setAdapter(adapter);
        tvMessageDollName = vg.findViewById(R.id.tvMessageDollName);
        tvMessageDollDescription = vg.findViewById(R.id.tvMessageDollDescription);
        user = (User) getActivity().getIntent().getSerializableExtra("User");
    }

    private void createDoll(){
        btnSaveDoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dollImage = Integer.parseInt(spnDollImage.getSelectedItem().toString());
                dollName = etDollName.getText().toString();
                dollDescription = etDollDescription.getText().toString();
                boolean validation1 = false;boolean validation2 = false;
                if(dollName.length() == 0){
                    tvMessageDollName.setText("Doll name must required");
                }else{
                    validation1 = true;
                    tvMessageDollName.setText(null);
                }

                if(dollDescription.length() == 0){
                    tvMessageDollDescription.setText("Doll description must required");
                }else{
                    validation2 = true;
                    tvMessageDollDescription.setText(null);
                }

                if(validation1 == true && validation2 == true){
                    doll = new DollFactory().createDoll(dollName, dollDescription, dollImage, user);
                    new DollFactory().insertDolls(doll);
                    etDollName.setText(null);
                    etDollDescription.setText(null);
                    Toast.makeText(getContext(), "Doll insert success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
