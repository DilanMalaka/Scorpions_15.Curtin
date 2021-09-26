package com.example.scorpions_15curtin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatePet extends AppCompatActivity {
    EditText name_input, type_input, gender_input, breed_input, birthday_input, color_input, description_input;
    Button update_button, delete_button;
    String id, name, type,gender, breed, birthday, color, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pet);

        name_input = findViewById(R.id.name_input2);
        type_input = findViewById(R.id.type_input2);
        gender_input = findViewById(R.id.gender_input2);
        breed_input = findViewById(R.id.breed_input2);
        birthday_input = findViewById(R.id.birthday_input2);
        color_input = findViewById(R.id.color_input2);
        description_input = findViewById(R.id.description_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle(name);
        }
        update_button.setOnClickListener((view) -> {

                PetDatabaseHelper myDB = new PetDatabaseHelper(updatePet.this);
                name = name_input.getText().toString().trim();
                type = type_input.getText().toString().trim();
                gender = gender_input.getText().toString().trim();
                breed = breed_input.getText().toString().trim();
                birthday = birthday_input.getText().toString().trim();
                color = color_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                myDB.updateData(id, name, type, gender, breed, birthday, color, description);


        });delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });




    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("type") && getIntent().hasExtra("gender") &&
                getIntent().hasExtra("breed") && getIntent().hasExtra("birthday") &&
                getIntent().hasExtra("color") && getIntent().hasExtra("description")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
            gender = getIntent().getStringExtra("gender");
            breed = getIntent().getStringExtra("breed");
            birthday = getIntent().getStringExtra("birthday");
            color = getIntent().getStringExtra("color");
            description = getIntent().getStringExtra("description");

            name_input.setText(name);
            type_input.setText(type);
            gender_input.setText(gender);
            breed_input.setText(breed);
            birthday_input.setText(birthday);
            color_input.setText(color);
            description_input.setText(description);

        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + " ?");
        builder.setMessage("Are you sure you want to delete?" + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PetDatabaseHelper myDB = new PetDatabaseHelper(updatePet.this);
                myDB.deleteOneRow(id);
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