package com.example.scorpions_15curtin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPets extends AppCompatActivity {
    EditText name_input, type_input, gender_input, breed_input, birthday_input, color_input, description_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pets);

        name_input = findViewById(R.id.name_input);
        type_input = findViewById(R.id.type_input);
        gender_input = findViewById(R.id.gender_input);
        breed_input = findViewById(R.id.breed_input);
        birthday_input = findViewById(R.id.birthday_input);
        color_input = findViewById(R.id.color_input);
        description_input = findViewById(R.id.description_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PetDatabaseHelper myDB=new PetDatabaseHelper(AddPets.this);
                myDB.addPet(name_input.getText().toString().trim(),
                        type_input.getText().toString().trim(),
                        gender_input.getText().toString().trim(),
                        breed_input.getText().toString().trim(),
                        birthday_input.getText().toString().trim(),
                        color_input.getText().toString().trim(),
                        description_input.getText().toString().trim());
            }
        });
    }
}