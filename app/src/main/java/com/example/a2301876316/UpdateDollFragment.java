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

import java.util.ArrayList;

public class UpdateDollFragment extends Fragment {
    DrawerLayout drawerLayout;
    ViewGroup vg;
    Doll doll;
    int index = -1;
    ArrayList<Doll> dolls = new DollFactory().getDolls();
    Integer dollImages[] = {
            R.drawable.teddy_bear,
            R.drawable.matryoshka_doll,
            R.drawable.girl_doll
    };
    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public UpdateDollFragment(int index){
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vg = (ViewGroup)inflater.inflate(R.layout.update_doll_fragment, container, false);

        init();
        updateDoll();

        return vg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    EditText etDollName, etDollDescription;
    Button btnUpdateDoll;
    String dollName, dollDescription;
    Spinner spnDollImage;
    TextView tvMessageDollName, tvMessageDollDescription;
    int dollImage;
    User user;
    private void init(){
        etDollName = vg.findViewById(R.id.etDollName);
        etDollDescription = vg.findViewById(R.id.etDollDescription);
        spnDollImage = vg.findViewById(R.id.spnDollImage);
        user = (User) getActivity().getIntent().getSerializableExtra("User");
        SpinnerDollImageAdapater adapter = new SpinnerDollImageAdapater(this.getActivity(), dollImages);
        spnDollImage.setAdapter(adapter);
        tvMessageDollName = vg.findViewById(R.id.tvMessageDollName);
        tvMessageDollDescription = vg.findViewById(R.id.tvMessageDollDescription);
        btnUpdateDoll = vg.findViewById(R.id.btnUpdateDoll);
    }

    private boolean dollNameChecker(ArrayList<Doll> dolls, String dollName){
        boolean search = false;
        if(dolls.isEmpty()){
            return true;
        }else{
            for(int i = 0 ; i < dolls.size() ; i++){
                if(dollName.equals(dolls.get(i).getDollName())){
                    search = false;
                }else{
                    search = true;
                }
            }
        }
        return search;
    }

    private void updateDoll(){
        etDollName.setText(dolls.get(index).getDollName());
        etDollDescription.setText(dolls.get(index).getDollDescription());
        btnUpdateDoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dollName = etDollName.getText().toString();
                dollDescription = etDollDescription.getText().toString();
                dollImage = Integer.parseInt(spnDollImage.getSelectedItem().toString());
                boolean validation1 = false;boolean validation2 = false;boolean validation3 = false;
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

                validation3 = dollNameChecker(dolls, dollName);

                if(validation3 == true){
                    tvMessageDollName.setText(null);
                }else{
                    tvMessageDollName.setText("There are same doll's name");
                }

                if(validation1 == true && validation2 == true && validation3 == true){
                    dolls.get(index).setDollName(dollName);
                    dolls.get(index).setDollDescription(dollDescription);
                    dolls.get(index).setDollImage(dollImage);
                    etDollName.setText(null);
                    etDollDescription.setText(null);
                    Toast.makeText(getContext(), "Doll insert success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
