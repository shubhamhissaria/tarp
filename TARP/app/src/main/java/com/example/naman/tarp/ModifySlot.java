package com.example.naman.tarp;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ModifySlot extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    int preSelectedIndex = -1;
    DatabaseReference mDatabase;
    Calendar c;
    String facid;
    public void setFacid(String x){
        facid=x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_slot);
        if (getIntent().getStringExtra("facid")== null) {
            setFacid("0000");
        } else {
            setFacid(getIntent().getStringExtra("facid"));
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Button button1 = (Button) findViewById(R.id.date);
        Button button2 = (Button) findViewById(R.id.submitslots);

        final ListView listView = (ListView) findViewById(R.id.list);
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

        final AdapterModifylist adapter = new AdapterModifylist(ModifySlot.this ,users);
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
                adapter.updateRecords(users);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DialogFragment datePicker = new DatePickerFrag();
               datePicker.show(getFragmentManager(),"date picker");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH)+1;
                int day = c.get(Calendar.DAY_OF_MONTH);
                String dateString=Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year);
                UserModel a;
                for(int i=0;i<10;i++)
                {
                    a=users.get(i);
                    if(a.isSelected==true)
                    {
                        int q=i+1;// demo slot number
                        mDatabase.child("schedule").child(dateString).child(facid).child(Integer.toString(q)).child("regno").setValue("--").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ModifySlot.this, "Saved", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ModifySlot.this, FacultyHome.class);
                                    intent.putExtra("regno", facid);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(ModifySlot.this, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
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
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setVisibility(View.VISIBLE);
    }
}


