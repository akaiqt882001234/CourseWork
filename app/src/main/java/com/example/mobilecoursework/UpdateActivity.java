package com.example.mobilecoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText text_name, text_destination, text_date, text_risk, text_description;
    Button update_button;
    String id, name, destination, date, risk, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        text_name = findViewById(R.id.text_name2);
        text_destination = findViewById(R.id.text_destination2);
        text_date = findViewById(R.id.text_date2);
        text_risk = findViewById(R.id.text_risk2);
        text_description = findViewById(R.id.text_description2);
        update_button = findViewById(R.id.update_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        getAndSetIntentData();

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("destination") && getIntent().hasExtra("date")
                && getIntent().hasExtra("risk")&& getIntent().hasExtra("description")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            destination = getIntent().getStringExtra("destination");
            date = getIntent().getStringExtra("date");
            risk = getIntent().getStringExtra("risk");
            description = getIntent().getStringExtra("description");

            //Setting Intent Data

            text_name.setText(name);
            text_destination.setText(destination);
            text_date.setText(date);
            text_risk.setText(risk);
            text_description.setText(description);

        }else{
            Toast.makeText(this,"No data...", Toast.LENGTH_SHORT).show();
        }
    }
}