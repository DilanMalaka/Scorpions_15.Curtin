package com.example.scorpions_15curtin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Care extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.care);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.pets:
                        startActivity(new Intent(getApplicationContext()
                                ,Pets.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.care:
                        return true;
                    case R.id.doctors:
                        startActivity(new Intent(getApplicationContext()
                                ,Doctors.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext()
                                ,About.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}