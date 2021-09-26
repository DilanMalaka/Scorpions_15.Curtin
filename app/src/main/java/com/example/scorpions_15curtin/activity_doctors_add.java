package com.example.scorpions_15curtin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_doctors_add extends AppCompatActivity {

    EditText doc_name_input, doc_phone, doc_work_hours_input, doc_address_input, doc_province_input, doc_country_input;
    Button doc_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_add);

        doc_name_input = findViewById(R.id.doc_name_input);
        doc_phone = findViewById(R.id.doc_phone);
        doc_work_hours_input = findViewById(R.id.doc_work_hours_input);
        doc_address_input = findViewById(R.id.doc_address_input);
        doc_province_input = findViewById(R.id.doc_province_input);
        doc_country_input = findViewById(R.id.doc_country_input);
        doc_add_button = findViewById(R.id.doc_add_button);
        doc_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorsDataBase myDB = new DoctorsDataBase(activity_doctors_add.this);
                myDB.addDoctor(doc_name_input.getText().toString().trim(),
                        doc_phone.getText().toString().trim(),
                        doc_work_hours_input.getText().toString().trim(),
                        doc_address_input.getText().toString().trim(),
                        doc_province_input.getText().toString().trim(),
                        doc_country_input.getText().toString().trim());
            }
        });
    }
}