package com.example.testmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static User user;
    static DrawerLayout drawerLayout;
    DollsFragment dollsFragment = new DollsFragment();
    InsertDollFragment insertDollFragment = new InsertDollFragment();
    AboutUsFragment aboutUsFragment = new AboutUsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if(dolls.isEmpty()){
//            Doll dolls1 = new DollFactory().createDoll("Doll 1", "Doll 1", user);
//            Doll dolls2 = new DollFactory().createDoll("Doll 2", "Doll 2", user);
//            Doll dolls3 = new DollFactory().createDoll("Doll 3", "Doll 3", user);
//            new DollFactory().insertDolls(dolls1);
//            new DollFactory().insertDolls(dolls2);
//            new DollFactory().insertDolls(dolls3);
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        replaceFragment(dollsFragment);
//        init();
//        recyclerView();
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
        replaceFragment(dollsFragment);
    }
    
    public void ClickInsertDoll(View view){
        replaceFragment(insertDollFragment);
    }

    public void ClickAboutUs(View view){
        replaceFragment(aboutUsFragment);
    }

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}