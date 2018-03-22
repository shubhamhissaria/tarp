package com.example.naman.tarp;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class FacultyHome extends AppCompatActivity {

    String facid;
    public void setFacid(String x){
        facid=x;
    }
    Button button3, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);
        button3 = (Button)findViewById(R.id.modify);
        logout = (Button) findViewById(R.id.logoutbtn);
        if (getIntent().getStringExtra("regno")== null) {
            setFacid("0000");
        } else {
            setFacid(getIntent().getStringExtra("regno"));
        }
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity_modify();
            }
        });
        Button button2 = (Button)findViewById(R.id.schedule);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity_check_schedule();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyHome.this, LoginActivity.class);//change to login page
                startActivity(intent);
                finish();
            }
        });

    }


    public void openactivity_modify(){
        Intent intent2 = new Intent(FacultyHome.this,ModifySlot.class);
        intent2.putExtra("facid", facid);
        startActivity(intent2);
        finish();

    }
    public void openactivity_check_schedule(){
        Intent intent1 = new Intent(FacultyHome.this,CheckSchedule.class);
        intent1.putExtra("facid", facid);
        startActivity(intent1);
        finish();
    }
}
