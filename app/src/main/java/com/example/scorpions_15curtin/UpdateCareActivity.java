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

public class UpdateCareActivity extends AppCompatActivity {

    EditText name_input, vaccine_input, date_input, due_input;
    Button updatecare_button, deletecare_button;

    String id, name, vaccine, date, due;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_care);

        name_input = findViewById(R.id.name_input2);
        vaccine_input = findViewById(R.id.vaccine_input2);
        date_input = findViewById(R.id.date_input2);
        due_input = findViewById(R.id.due_input2);
        updatecare_button = findViewById(R.id.updatecare_button);
        deletecare_button = findViewById(R.id.deletecare_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        updatecare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateCareActivity.this);
                name=name_input.getText().toString().trim();
                vaccine=vaccine_input.getText().toString().trim();
                date=date_input.getText().toString().trim();
                due=due_input.getText().toString().trim();
                myDB.updateData(id, name, vaccine, date, due);
            }
        });

        deletecare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });



    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
        getIntent().hasExtra("vaccine") && getIntent().hasExtra("date") &&
        getIntent().hasExtra("due")){
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            vaccine = getIntent().getStringExtra("vaccine");
            date = getIntent().getStringExtra("date");
            due = getIntent().getStringExtra("due");

            //Setting Intent Data
            name_input.setText(name);
            vaccine_input.setText(vaccine);
            date_input.setText(date);
            due_input.setText(due);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateCareActivity.this);
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