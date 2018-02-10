package com.example.chiragmehta.test;

import android.app.Activity;
import android.app.DatePickerDialog;
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

public class activity_modify_slot extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button schedule;
    private Button slot;
    int preSelectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_slot);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setVisibility(View.GONE);

        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false, "8:00 to 9:00"));
        users.add(new UserModel(false, "9:00 to 10:00"));
        users.add(new UserModel(false, "10:00 to 11:00"));
        users.add(new UserModel(false, "11:00 to 12:00"));
        users.add(new UserModel(false, "12:00 to 13:00"));
        users.add(new UserModel(false, "13:00 to 14:00"));
        users.add(new UserModel(true, "14:00 to 15:00"));
        users.add(new UserModel(true, "15:00 to 16:00"));
        users.add(new UserModel(true, "16:00 to 17:00"));
        users.add(new UserModel(false, "17:00 to 18:00"));

        final AdapterModifylist adapter = new AdapterModifylist(this, users);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users.get(i);

                if (model.isSelected())
                    model.setSelected(false);

                else
                    model.setSelected(true);

                users.set(i, model);

                //now update adapter
                adapter.updateRecords(users);
            }
        });


//        TableLayout table;
//        Button button;
//        table = (TableLayout) findViewById (R.id.tablelayout1);
//        button = (Button) findViewById(R.id.button1);
//
//        button.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                // View list = (View)findViewById(R.id.myviewId);
//                tbleview.setVisibility(View.INVISIBLE);
//
//            }
//        });

        Button button1 = (Button) findViewById(R.id.date);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFrag();
                datePicker.show(getSupportFragmentManager(),"date picker");


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
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setVisibility(View.VISIBLE);


    }
}


