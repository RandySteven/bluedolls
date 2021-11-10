package com.example.a2301876316;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a2301876316.models.Doll;
import com.example.a2301876316.models.DollFactory;
import com.example.a2301876316.models.User;

import java.util.ArrayList;

public class DollFragment extends Fragment {

    ViewGroup vg;
    Doll doll;
    User user;
    ArrayList<Doll>dolls = new DollFactory().getDolls();
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

    int index = -1;
    TextView tvDollName, tvDollDescription, tvUserName;
    ImageView ivDollImage;
    private void init(){
        ivDollImage = vg.findViewById(R.id.ivDoll);
        tvDollName = vg.findViewById(R.id.tvDollName);
        tvDollDescription = vg.findViewById(R.id.tvDollDescription);
        tvUserName = vg.findViewById(R.id.tvUserName);
        doll = dolls.get(index);
        user = (User) getActivity().getIntent().getSerializableExtra("User");
        ivDollImage.setImageResource(doll.getDollImage());
        tvDollName.setText(doll.getDollName());
        tvDollDescription.setText(doll.getDollDescription());
        tvUserName.setText(doll.getUser().getUserName());
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public DollFragment(int index){
        this.index = index;
    }
}
