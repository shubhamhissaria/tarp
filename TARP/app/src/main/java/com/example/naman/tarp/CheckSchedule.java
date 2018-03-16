package com.example.naman.tarp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckSchedule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button schedule;
    private Button slot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_schedule);

        Button button1 = (Button) findViewById(R.id.date);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment datePicker = new DatePickerFrag();
                datePicker.show(getFragmentManager(),"date picker");
                //datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= DateFormat.getDateInstance().format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.display_date);
        textView.setText(currentDateString);


    }
}