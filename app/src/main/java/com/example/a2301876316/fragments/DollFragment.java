package com.example.a2301876316.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a2301876316.DataHelper;
import com.example.a2301876316.R;
import com.example.a2301876316.Utils;
import com.example.a2301876316.models.Doll;
import com.example.a2301876316.factory.DollFactory;
import com.example.a2301876316.models.User;

import java.util.ArrayList;

public class DollFragment extends Fragment {

    ViewGroup vg;
    DataHelper dataHelper = null;
    Doll doll;
    User user;
    ArrayList<Doll>dolls;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vg = (ViewGroup)inflater.inflate(R.layout.doll_fragment, container, false);
//        onCreate();
        init();
        return vg;
    }
    Bundle bundle;

    String dollId;
    public void onCreate() {
        bundle = this.getArguments();
    }

    TextView tvDollName, tvDollDescription, tvUserName;
    ImageView ivDollImage;
    private void init(){
        if(dataHelper == null){
            dataHelper = new DataHelper(getContext());
        }
        dolls = dataHelper.getAllDolls();
        ivDollImage = vg.findViewById(R.id.ivDoll);
        tvDollName = vg.findViewById(R.id.tvDollName);
        tvDollDescription = vg.findViewById(R.id.tvDollDescription);
        tvUserName = vg.findViewById(R.id.tvUserName);
        doll = dataHelper.getDoll(dollId);
        user = (User) getActivity().getIntent().getSerializableExtra("User");
        ivDollImage.setImageBitmap(Utils.getImage(doll.getDollImage()));
        tvDollName.setText(doll.getDollName());
        tvDollDescription.setText(doll.getDollDescription());
        tvUserName.setText(doll.getUser().getUserName());
    }

    public void setDollId(String dollId) {
        this.dollId = dollId;
    }

    public String getDollId() {
        return dollId;
    }

    public DollFragment(String dollId){
        this.dollId = dollId;
    }
}
