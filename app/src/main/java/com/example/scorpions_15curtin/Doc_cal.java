package com.example.scorpions_15curtin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Doc_cal extends AppCompatActivity {


    TextView result;
    EditText tax, charges;
    String cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_cal);

        result = findViewById(R.id.doc_result);
        tax = findViewById(R.id.doc_c_2);
        charges = findViewById(R.id.doc_c_1);

    }

    public void calculateCharges(View view){
        String t= tax.getText().toString();
        String c = charges.getText().toString();

        float taxValue = Float.parseFloat(t);
        float chargeValue = Float.parseFloat(c);

        float re = chargeValue * (taxValue/100);
        float finalRe = re + chargeValue;

        cal = "Total Payment LKR: " + finalRe;

        result.setText(cal);
    }
}