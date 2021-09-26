package com.example.scorpions_15curtin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCareActivity extends AppCompatActivity {

    EditText name_input, vaccine_input, date_input, due_input;
    Button addcare_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_care);

        name_input = findViewById(R.id.name_input);
        vaccine_input = findViewById(R.id.vaccine_input);
        date_input = findViewById(R.id.date_input);
        due_input = findViewById(R.id.due_input);
        addcare_button = findViewById(R.id.addcare_button);
        addcare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddCareActivity.this);
                myDB.addCare(name_input.getText().toString().trim(),
                        vaccine_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        due_input.getText().toString().trim());
            }
        });
    }
}