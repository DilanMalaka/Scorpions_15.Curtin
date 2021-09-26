package com.example.scorpions_15curtin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class pet_cal extends AppCompatActivity {


    TextView result;
    EditText previous, current;
    String cal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_cal);

        result = findViewById(R.id.result1);
        previous = findViewById(R.id.pet_cal1);
        current = findViewById(R.id.pet_cal2);
    }

    public void calPercentage(View view){
        String p = previous.getText().toString();
        String c = current.getText().toString();

        float pWeight = Float.parseFloat(p);
        float cWeight = Float.parseFloat(c);

        float re = cWeight - pWeight;
        float fRe= (re / pWeight) * 100;


        cal = "Percentage: " + fRe + "%";

        result.setText(cal);

    }
}