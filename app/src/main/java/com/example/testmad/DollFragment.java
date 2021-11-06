package com.example.testmad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testmad.models.Doll;
import com.example.testmad.models.DollFactory;
import com.example.testmad.models.User;

public class DollFragment extends Fragment {

    ViewGroup vg;
    Doll doll;
    User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vg = (ViewGroup)inflater.inflate(R.layout.doll_fragment, container, false);
        return vg;
    }

    TextView tvDollName, tvDollDescription;
    private void init(){
        tvDollName = vg.findViewById(R.id.tvDollName);
        tvDollDescription = vg.findViewById(R.id.tvDollDescription);
        doll = new DollFactory().getDolls().get(index);
        user = (User) getActivity().getIntent().getSerializableExtra("User");
        tvDollName.setText(doll.getDollName());
        tvDollDescription.setText(doll.getDollDescription());
    }

    private int index;

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public DollFragment(int index){
        this.index = index;
    }

}
