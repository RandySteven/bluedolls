package com.example.a2301876316;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a2301876316.fragments.DollsFragment;
import com.example.a2301876316.fragments.InsertDollFragment;
import com.example.a2301876316.models.User;

public class MainActivity extends AppCompatActivity {

    static User user;
    static DrawerLayout drawerLayout;
    DollsFragment dollsFragment = new DollsFragment();
    InsertDollFragment insertDollFragment = new InsertDollFragment();
    AboutUsActivity aboutUsActivity = new AboutUsActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (User)getIntent().getSerializableExtra("user");
        drawerLayout = findViewById(R.id.drawer_layout);
        replaceFragment(dollsFragment);
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
        replaceActivity(aboutUsActivity);
    }

    public void ClickLogout(View view){
        user = null;
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    protected void replaceActivity(AppCompatActivity activity){
        Intent intent = new Intent(MainActivity.this, activity.getClass());
        intent.putExtra("user", user);
        finish();
        startActivity(intent);
    }

}