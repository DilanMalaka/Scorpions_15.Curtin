package com.example.scorpions_15curtin;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scorpions_15curtin.databinding.ActivityPetsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Pets extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button, pet_cal;
    ImageView empty_imageview1;
    TextView no_pets;

    PetDatabaseHelper myDB;
    ArrayList<String> pet_id, pet_name, pet_type, pet_gender, pet_breed, pet_birthday, pet_color, pet_description;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);




        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.pets);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.pets:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.care:
                        startActivity(new Intent(getApplicationContext()
                                ,Care.class));
                        overridePendingTransition(0,0);
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
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview1 = findViewById(R.id.empty_imageview1);
        no_pets = findViewById(R.id.no_pets);
        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(Pets.this, AddPets.class);
            startActivity(intent);
        });

        myDB = new PetDatabaseHelper(Pets.this);
        pet_id = new ArrayList<>();
        pet_name = new ArrayList<>();
        pet_type = new ArrayList<>();
        pet_gender = new ArrayList<>();
        pet_breed = new ArrayList<>();
        pet_birthday = new ArrayList<>();
        pet_color = new ArrayList<>();
        pet_description = new ArrayList<>();

        StoreDataInArrays();
        customAdapter = new CustomAdapter(Pets.this, this, pet_id, pet_name, pet_type, pet_gender,
                pet_breed, pet_birthday, pet_color, pet_description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Pets.this));


        //.......//
        pet_cal = findViewById(R.id.pet_cal);
        pet_cal.setOnClickListener(view -> {
            Intent intent = new Intent(Pets.this, pet_cal.class);
            startActivity(intent);
        });
        //.................//


        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void StoreDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview1.setVisibility(View.VISIBLE);
            no_pets.setVisibility(View.VISIBLE);

        }else{
            while (cursor.moveToNext()){
                pet_id.add(cursor.getString(0));
                pet_name.add(cursor.getString(1));
                pet_type.add(cursor.getString(2));
                pet_gender.add(cursor.getString(3));
                pet_breed.add(cursor.getString(4));
                pet_birthday.add(cursor.getString(5));
                pet_color.add(cursor.getString(6));
                pet_description.add(cursor.getString(7));

            }
            empty_imageview1.setVisibility(View.GONE);
            no_pets.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pet_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete All?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PetDatabaseHelper myDB = new PetDatabaseHelper(Pets.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(Pets.this, Pets.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }
}