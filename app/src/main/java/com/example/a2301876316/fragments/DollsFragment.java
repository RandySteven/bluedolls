package com.example.a2301876316.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a2301876316.DataHelper;
import com.example.a2301876316.adapter.DollsAdpater;
import com.example.a2301876316.R;
import com.example.a2301876316.models.Doll;
import com.example.a2301876316.factory.DollFactory;
import com.example.a2301876316.models.User;

import java.util.ArrayList;
import java.util.List;

public class DollsFragment extends Fragment {
    public  static User user;
    DataHelper dataHelper = null;
    ArrayList<Doll> dolls;
    ViewGroup vg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vg = (ViewGroup)inflater.inflate(R.layout.dolls_fragment, container, false);
        init();
        recyclerView();
        return vg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    TextView tvUserName;
    private void init(){
        if(dataHelper == null){
            dataHelper = new DataHelper(getContext());
        }
        dolls = dataHelper.getAllDolls();
        user = (User) getActivity().getIntent().getSerializableExtra("user");
        tvUserName = vg.findViewById(R.id.tvUserName);
        tvUserName.setText(user.getUserName());
    }

    ListView mListView;
    DollsAdpater mAdapter;
    private void recyclerView(){
        mListView = vg.findViewById(R.id.lvDolls);
        mAdapter = new DollsAdpater(getContext(), dolls);
        mListView.setAdapter(mAdapter);
    }
}
