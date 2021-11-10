package com.example.a2301876316;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2301876316.models.Doll;
import com.example.a2301876316.models.DollFactory;
import com.example.a2301876316.models.User;

import java.util.ArrayList;

public class DollsFragment extends Fragment {
    static User user;
    ArrayList<Doll> dolls = new DollFactory().getDolls();
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
        user = (User) getActivity().getIntent().getSerializableExtra("User");
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
