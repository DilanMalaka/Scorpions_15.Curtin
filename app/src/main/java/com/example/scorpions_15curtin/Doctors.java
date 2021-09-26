package com.example.scorpions_15curtin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Doctors extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton doc_add_button, doc_charges;
    ImageView empty_doc_imageview;
    TextView empty_textview;


    DoctorsDataBase myDB;
    ArrayList<String> _id, doctor_name, doctor_phone, working_hours, doc_address, doc_province, doc_country;
    Doc_Custom_Adapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        recyclerView = findViewById(R.id.recyclerView);
        doc_add_button = findViewById(R.id.doc_add_button);
        empty_doc_imageview = findViewById(R.id.empty_doc_imageview);
        empty_textview = findViewById(R.id.empty_textview);

        doc_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Doctors.this, activity_doctors_add.class);
                startActivity(intent);
            }
        });

        myDB = new DoctorsDataBase(Doctors.this);
        _id = new ArrayList<>();
        doctor_name = new ArrayList<>();
        doctor_phone = new ArrayList<>();
        working_hours = new ArrayList<>();
        doc_address = new ArrayList<>();
        doc_province = new ArrayList<>();
        doc_country = new ArrayList<>();

        storeDataInArray();

        customAdapter = new Doc_Custom_Adapter(Doctors.this, Doctors.this, _id, doctor_name, doctor_phone, working_hours, doc_address, doc_province, doc_country);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Doctors.this));


        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.doctors);

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
                        startActivity(new Intent(getApplicationContext()
                                ,Care.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.doctors:
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

        doc_charges = findViewById(R.id.doc_charges);
        doc_charges.setOnClickListener(view -> {
            Intent intent = new Intent(Doctors.this, Doc_cal.class);
        startActivity(intent);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            recreate();
        }
    }

    void storeDataInArray(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_doc_imageview.setVisibility(View.VISIBLE);
            empty_textview.setVisibility(View.VISIBLE);

        }else{
            while (cursor.moveToNext()){
                _id.add(cursor.getString(0));
                doctor_name.add(cursor.getString(1));
                doctor_phone.add(cursor.getString(2));
                working_hours.add(cursor.getString(3));
                doc_address.add(cursor.getString(4));
                doc_province.add(cursor.getString(5));
                doc_country.add(cursor.getString(6));
            }
            empty_doc_imageview.setVisibility(View.GONE);
            empty_textview.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_doc_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.doc_delete_all){

            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to Delete All Doctor Details ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //DoctorsDataBase myDB = new DoctorsDataBase(Doctors.this);
                DoctorsDataBase myDB = new DoctorsDataBase(Doctors.this);
                myDB.deleteAllData();

                //refresh the activity
                Intent intent = new Intent(Doctors.this, Doctors.class);
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