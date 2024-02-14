package com.example.navigationdrawerapp.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawerapp.R;

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
                     Intent intent =new Intent(MainActivity.this, DrawerMain_Activity.class);
                     startActivity(intent);
                     finish();
                 }else{

                     Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                     startActivity(intent);
                     finish();
                 }
             }
         },4000);


    }



}