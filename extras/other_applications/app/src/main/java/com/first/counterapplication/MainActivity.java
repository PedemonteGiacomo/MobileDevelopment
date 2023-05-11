package com.first.counterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    private Number previous_value;
    private Number step_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous_value = 0;
        step_value = 1;
    }

    public void AddValue(View v) throws ParseException {
        EditText step_value_view = findViewById(R.id.StepValue);
        TextView label = findViewById(R.id.CV);
        // check if empty submission
        if(TextUtils.isEmpty(step_value_view.getText().toString())){
            Context context = getApplicationContext();
            CharSequence text = "Choose a step size";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        } else {
            step_value = NumberFormat.getInstance().parse(step_value_view.getText().toString());
            previous_value = previous_value.floatValue() + step_value.floatValue();
        }
        label.setTextSize(200);
        label.setText("Value = " + previous_value);
    }

    public void RemoveValue(View v) throws ParseException {
        EditText step_value_view = findViewById(R.id.StepValue);
        TextView label = findViewById(R.id.CV);
        if(TextUtils.isEmpty(step_value_view.getText().toString())){
            Context context = getApplicationContext();
            CharSequence text = "Choose a step size";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        } else {
            step_value = NumberFormat.getInstance().parse(step_value_view.getText().toString());
            previous_value = previous_value.floatValue() - step_value.floatValue();
        }
        label.setTextSize(200);
        label.setText("Value = " + previous_value);
    }
}