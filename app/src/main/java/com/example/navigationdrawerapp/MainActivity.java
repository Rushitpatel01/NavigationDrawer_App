package com.example.navigationdrawerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawerapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public  static SharedPreferences preferences;
    public  static SharedPreferences.Editor editor;

    Boolean Login= false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         preferences= getSharedPreferences("MyPref",MODE_PRIVATE);
         editor=preferences.edit();
         Login=preferences.getBoolean("Login",false);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 if(Login)
                 {
                     Intent intent =new Intent(MainActivity.this,DrawerMain_Activity.class);
                     startActivity(intent);
                     finish();
                 }else{

                     Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                     startActivity(intent);
                     finish();
                 }
             }
         },1000);


    }



}