package com.example.frontcafe;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.frontcafe.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.BreakIterator;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private EditText ed;
    private FrameLayout Wow;

    private ConstraintLayout log;

    private Home HomeFragment;
    private Menu MenuFragment;
    private Discounts DiscFragment;
    private Ect EctFragment;
    private AboutUs AUFragment;
    private LoginActivity loggin;
    private BreakIterator editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        Wow = (FrameLayout)findViewById(R.id.wow);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav2);


        ImageButton au = (ImageButton)findViewById(R.id.about_us);
        ImageButton cart = (ImageButton)findViewById(R.id.cart);
        HomeFragment = new Home();
        MenuFragment = new Menu();
        DiscFragment = new Discounts();
        EctFragment = new Ect();
        AUFragment = new AboutUs();
        loggin = new LoginActivity();






        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        setFragment(HomeFragment);
                        return true;
                    case R.id.nav_menu:
                        setFragment(MenuFragment);
                        return true;
                    case R.id.nav_disc:
                        setFragment(DiscFragment);
                        return true;
                    case R.id.nav_ect:
                        setFragment(EctFragment);
                        return true;

                    default:
                        return false;

                }
            }
        });


    }

    public void onMyClick(View view) {
        setFragment(AUFragment);
    }

    public void clc(View view) {
        setFragment(HomeFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }



    private void setContentView() {
    }

    public void vhod(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}

