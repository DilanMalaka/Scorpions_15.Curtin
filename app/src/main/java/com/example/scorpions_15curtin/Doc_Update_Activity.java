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

public class Doc_Update_Activity extends AppCompatActivity {

    EditText doc_name_input, doc_phone, doc_work_hours_input, doc_address_input, doc_province_input, doc_country_input;
    Button update_button, delete_button;

    String id, name, phone, work, address, province, country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_update);

        doc_name_input = findViewById(R.id.doc_name_input2);
        doc_phone = findViewById(R.id.doc_phone2);
        doc_work_hours_input = findViewById(R.id.doc_work_hours_input2);
        doc_address_input = findViewById(R.id.doc_address_input2);
        doc_province_input = findViewById(R.id.doc_province_input2);
        doc_country_input = findViewById(R.id.doc_country_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //This should be called first
        getAndSetIntentData();

        //set actionbar title after getAndSetIntendData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DoctorsDataBase myDB = new DoctorsDataBase(Doc_Update_Activity.this);

                //------------------------------------------------------------------------------------

                name=doc_name_input.getText().toString().trim();
                phone=doc_phone.getText().toString().trim();
                work=doc_work_hours_input.getText().toString().trim();
                address=doc_address_input.getText().toString().trim();
                province=doc_province_input.getText().toString().trim();
                country=doc_country_input.getText().toString().trim();

                //-------------------------------------------------------------------------------------

                //After that we should called that
                myDB.updateData(id, name, phone, work, address, province, country);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDialog();

            }
        });



    }

    void  getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("phone") && getIntent().hasExtra("work")
                && getIntent().hasExtra("address") && getIntent().hasExtra("province") && getIntent().hasExtra("country")){

            //Getting data from Intent

            id= getIntent().getStringExtra("id");
            name= getIntent().getStringExtra("name");
            phone= getIntent().getStringExtra("phone");
            work= getIntent().getStringExtra("work");
            address= getIntent().getStringExtra("address");
            province= getIntent().getStringExtra("province");
            country= getIntent().getStringExtra("country");

            //Setting data to Intent

            doc_name_input.setText(name);
            doc_phone.setText(phone);
            doc_work_hours_input.setText(work);
            doc_address_input.setText(address);
            doc_province_input.setText(province);
            doc_country_input.setText(country);


        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to Delete Dr " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DoctorsDataBase myDB = new DoctorsDataBase(Doc_Update_Activity.this);
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