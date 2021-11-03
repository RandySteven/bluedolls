package com.example.testmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.testmad.models.Doll;
import com.example.testmad.models.DollFactory;
import com.example.testmad.models.User;
import com.example.testmad.models.UserFactory;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static User user;
    ArrayList<Doll> dolls = new DollFactory().getDolls();
    static DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(dolls.isEmpty()){
            Doll dolls1 = new DollFactory().createDoll("Doll 1", "Doll 1", user);
            Doll dolls2 = new DollFactory().createDoll("Doll 2", "Doll 2", user);
            Doll dolls3 = new DollFactory().createDoll("Doll 3", "Doll 3", user);
            new DollFactory().insertDolls(dolls1);
            new DollFactory().insertDolls(dolls2);
            new DollFactory().insertDolls(dolls3);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        init();
        recyclerView();
    }

    TextView tvUserName;
    private void init(){
        user = (User) getIntent().getSerializableExtra("User");
        tvUserName = findViewById(R.id.tvUserName);
        tvUserName.setText(user.getUserName());
    }

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private void recyclerView(){
        mRecyclerView = findViewById(R.id.rvDolls);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new DollsAdpater(this, dolls);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        recreate();
    }
    
    public void ClickInsertDoll(View view){
        redirectActivity(this, CreateDollActivity.class);
    }

    public void ClickAboutUs(View view){
        redirectActivity(this, AboutUsActivity.class);
    }

    private static void redirectActivity(Activity activity, Class aClass){
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user", user);
        activity.startActivity(intent);
    }

}