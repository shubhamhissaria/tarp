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
    private Button schedule;
    private Button slot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);
        Button button3 = (Button)findViewById(R.id.modify);
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

    }


    public void openactivity_modify(){
        Intent intent2 = new Intent(FacultyHome.this,ModifySlot.class);
        startActivity(intent2);
    }
    public void openactivity_check_schedule(){
        Intent intent1 = new Intent(FacultyHome.this,CheckSchedule.class);
        startActivity(intent1);
    }
}
