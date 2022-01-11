package com.example.a2301876316.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.a2301876316.DataHelper;
import com.example.a2301876316.R;
import com.example.a2301876316.Utils;
import com.example.a2301876316.adapter.SpinnerDollImageAdapater;
import com.example.a2301876316.models.Doll;
import com.example.a2301876316.factory.DollFactory;
import com.example.a2301876316.models.User;

import java.io.InputStream;
import java.util.ArrayList;

public class UpdateDollFragment extends Fragment {

    DataHelper dataHelper = null;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "StoreImageActivity";

    ViewGroup vg;
    Doll doll;
    String dollId;
    ArrayList<Doll> dolls;
    public void setDollId(String dollId) {
        this.dollId = dollId;
    }

    public String getDollId() {
        return dollId;
    }

    public UpdateDollFragment(String dollId){
        this.dollId = dollId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vg = (ViewGroup)inflater.inflate(R.layout.update_doll_fragment, container, false);

        init();
        chooseImage();
        updateDoll();

        return vg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    EditText etDollName, etDollDescription;
    Button btnUpdateDoll, btnDollImage;
    String dollName, dollDescription;
    TextView tvMessageDollName, tvMessageDollDescription;
    User user;
    ImageView ivDollImage;
    private void init(){
        if(dataHelper == null){
            dataHelper = new DataHelper(getContext());
        }
        dolls = dataHelper.getAllDolls();
        doll = dataHelper.getDoll(dollId);
        etDollName = vg.findViewById(R.id.etDollName);
        etDollDescription = vg.findViewById(R.id.etDollDescription);
        btnDollImage = vg.findViewById(R.id.btnDollImage);
        ivDollImage = vg.findViewById(R.id.ivDollImage);
        user = (User) getActivity().getIntent().getSerializableExtra("user");
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

    private void chooseImage(){
        btnDollImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), SELECT_PICTURE);
            }
        });
    }

    private Uri selectedImageUri;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_PICTURE){
            if(resultCode == Activity.RESULT_OK){
                selectedImageUri = data.getData();
                ivDollImage.setImageURI(selectedImageUri);
            }
        }
    }

    private void updateDoll(){
        etDollName.setText(doll.getDollName());
        etDollDescription.setText(doll.getDollDescription());
        btnUpdateDoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dollName = etDollName.getText().toString();
                dollDescription = etDollDescription.getText().toString();
                InputStream inputStream = null;

                try{
                    inputStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
                }catch (Exception e){
                    e.printStackTrace();
                }

                byte []inputImage = null;
                try{
                    inputImage = Utils.getBytes(inputStream);
                }catch (Exception e){
                    e.printStackTrace();
                }

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
                    dataHelper.updateDoll(dollId, dollName, dollDescription, inputImage);
                    etDollName.setText(null);
                    etDollDescription.setText(null);
                    ivDollImage.setImageURI(null);
                    Toast.makeText(getContext(), "Doll update success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
