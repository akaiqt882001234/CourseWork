package com.example.mobilecoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobilecoursework.database.MyDatabaseHelper;

public class AddActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    EditText text_name,text_destination,text_date,text_description;
    Button add_button;
    RadioGroup radioGroup;
    RadioButton radioNo,radioYes,radioButton;
    String radioText2 = "No";
    Boolean isAllFieldChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        text_name = findViewById(R.id.text_name);
        text_destination = findViewById(R.id.text_destination);
        text_date = findViewById(R.id.text_date);
        radioYes = findViewById(R.id.radioYes);
        radioNo = findViewById(R.id.radioNo);
        radioGroup = findViewById(R.id.radioGroup);
        text_description = findViewById(R.id.text_description);
        add_button = findViewById(R.id.add_button);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                radioText2 = radioButton.getText().toString();
                // Change Value RadioButton to Yes (No Button is always Auto Selected)
                ContentValues contentValues = new ContentValues();
                if(radioYes.isChecked()){
                    contentValues.put("Yes",radioYes.getText().toString());
                }
            }
        });


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert Value to Database
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                //Check Value of text_name,text_destination,text_date
                if(TextUtils.isEmpty(text_name.getText().toString())){
                    text_name.setError("Please enter Name of Trip!");
                }else if(TextUtils.isEmpty(text_destination.getText().toString())){
                    text_destination.setError("Please enter Destination!");
                }else if(TextUtils.isEmpty(text_date.getText().toString())){
                    text_date.setError("Please enter Date of Trip!");
                }else{
                    getInputs();

                    //Insert Value to Database
                    myDB.add_trip(text_name.getText().toString().trim(),
                            text_destination.getText().toString().trim(),
                            text_date.getText().toString().trim(),
                            radioText2.toString().trim(),
                            text_description.getText().toString().trim());

                }
            }
        });
    }

// Get Inputs form field
    private void getInputs() {
        //Set String input Value
        String strTripName = text_name.getText().toString();
        String strDestination = text_destination.getText().toString();
        String strDate = text_date.getText().toString();
        String radioBt = radioText2.toString();
        String strDescription = text_description.getText().toString();

        displayNextAlert(strTripName, strDestination, strDate, radioBt,strDescription);

    }
    // Display Alert Message and Close button
    private void displayNextAlert(String strTripName, String strDestination, String strDate, String radioBt, String strDescription) {
        new AlertDialog.Builder(this).setMessage("Your Trip Information: " +
                        "\n Trip name: " + strTripName +
                        "\n Destination: " + strDestination +
                        "\n Risk Assessment: " + radioBt +
                        "\n Date: " + strDate +
                        "\n Description: " + strDescription
                )
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();


    }

}