package com.example.scorpions_15curtin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class bmical extends AppCompatActivity {
    TextView result;
    EditText weight, height, leg, hip;
    String cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmical);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        result = findViewById(R.id.resultbmi);
        leg = findViewById(R.id.leg);
        hip = findViewById(R.id.hip);
    }
    public void bmiDog(View view){
        String w = weight.getText().toString();
        String h = height.getText().toString();

        float wvalue = Float.parseFloat(w)/(float)0.45;
        float hvalue = Float.parseFloat(h)/(float)2.54;
        float res = wvalue/hvalue;
        cal = "DOG'S BMI: " + res;
        result.setText(cal);
    }

    public void bmiCat(View view){
        String hi = hip.getText().toString();
        String l = leg.getText().toString();

        float hivalue = Float.parseFloat(hi)/(float)0.7062;
        float lvalue = Float.parseFloat(l);
        float res = hivalue-lvalue;
        float ans = res/(float)0.9156;
        float fans = ans - lvalue;
        cal = "CAT'S BMI: " + fans;
        result.setText(cal);
    }

}