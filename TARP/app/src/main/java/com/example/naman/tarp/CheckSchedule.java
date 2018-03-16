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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ScheduledExecutorService;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckSchedule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ListView listView;
    DatabaseReference ref,huehue;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Schedule schedule;
    Calendar c;
    String facid;
    Button b2,button1;
    public void setFacid(String x){
        facid=x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_schedule);

        if (getIntent().getStringExtra("facid")== null) {
            setFacid("0000");
        }
        else {
            setFacid(getIntent().getStringExtra("facid"));
        }
        button1 = (Button) findViewById(R.id.date);
        schedule = new Schedule();
        listView=(ListView)findViewById(R.id.listView);
        ref = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.retreive_data,R.id.retriveData, list);
        b2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment datePicker = new DatePickerFrag();
                datePicker.show(getFragmentManager(),"date picker");
                //datePicker.show(getSupportFragmentManager(),"date picker");

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for Retreival
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH)+1;
                int day = c.get(Calendar.DAY_OF_MONTH);
                String dateString=Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year);
                //String abc=dateString+":"+facid;
                //Toast.makeText(CheckSchedule.this, abc, Toast.LENGTH_SHORT).show();
                huehue=ref.child("schedule").child(dateString).child(facid);
                huehue.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String regnum = dataSnapshot.getValue(String.class);
//                        Toast.makeText(CheckSchedule.this, regnum, Toast.LENGTH_SHORT).show();
                        for(DataSnapshot ds1: dataSnapshot.getChildren()){
                                    //Toast.makeText(CheckSchedule.this, schedule.getRegno(), Toast.LENGTH_SHORT).show();
                                    schedule = ds1.getValue(Schedule.class);
                                    String slotnum= ds1.getKey().toString();
                                    list.add(slotnum+": \n" +schedule.getRegno().toString()+"");
                                }
                                listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//                ref.addValueEventListener(new ValueEventListener(){
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot){
//                        for(DataSnapshot ds: dataSnapshot.getChildren())
//                        {
//                            for(DataSnapshot ds1: ds.getChildren()){
//                                    //Toast.makeText(CheckSchedule.this, schedule.getRegno(), Toast.LENGTH_SHORT).show();
//                                    schedule = ds1.getValue(Schedule.class);
//                                    String slotnum= ds1.getKey().toString();
//                                    list.add(slotnum+": \n" +schedule.getRegno().toString()+"");
//                                }
//                                listView.setAdapter(adapter);
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError){
//                        Toast.makeText(CheckSchedule.this, "Database error!", Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });
    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= DateFormat.getDateInstance().format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.display_date);
        textView.setText(currentDateString);


    }
}