package com.example.navigationdrawerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.navigationdrawerapp.ui.AddproductFragment;
import com.example.navigationdrawerapp.ui.View_Fragment;
import com.example.navigationdrawerapp.ui.Show_Fragment;
import com.google.android.material.navigation.NavigationView;

public class DrawerMain_Activity extends AppCompatActivity {

    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_main);
        navigationView=findViewById(R.id.nav_Drawer_View);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.myDrawer);
//        getSupportActionBar().show();
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(DrawerMain_Activity.this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_add_product) {
                    addFragment(new AddproductFragment());
                }
                if (item.getItemId() == R.id.menu_view_product) {
                    addFragment(new View_Fragment());
                }
                if (item.getItemId() == R.id.menu_view_all_product) {
                    addFragment(new Show_Fragment());
                }
                if (item.getItemId() == R.id.menu_logout) {
                    Intent intent = new Intent(DrawerMain_Activity.this, LoginActivity.class);

                    startActivity(intent);
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });




    }

    private void addFragment(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction= fm.beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.commit();
    }


}






