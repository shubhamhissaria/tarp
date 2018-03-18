package com.example.naman.tarp;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookSlot extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    int preSelectedIndex = -1;
    DatabaseReference fDatabaseRoot,ref2;
    Calendar c;
    Spinner tarpstudentloginpinner;
    final List<UserModel> users = new ArrayList<>();
    String regnumb;
    public void setaccountnumber(String x){
        regnumb=x;
    }
    public String findtiming(int a)
    {
        switch (a)
        {
            case 1:
                return "8:00 to 9:00";
            case 2:
                return "9:00 to 10:00";
            case 3:
                return "10:00 to 11:00";
            case 4:
                return "11:00 to 12:00";
            case 5:
                return "12:00 to 13:00";
            case 6:
                return "14:00 to 15:00";
            case 7:
                return "15:00 to 16:00";
            case 8:
                return "16:00 to 17:00";
            case 9:
                return "17:00 to 18:00";
            default:
                return "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);
        if (getIntent().getStringExtra("regno")== null) {
            setaccountnumber("--");
        } else {
            setaccountnumber(getIntent().getStringExtra("regno"));
        }
        ListView listView = (ListView) findViewById(R.id.listview);
//        users.add(new UserModel(false, "Dharm"));
        final AdapterModifylist adapter = new AdapterModifylist(this, users);
        listView.setAdapter(adapter);
        adapter.type=1;
        Button button1 = (Button) findViewById(R.id.date1);
        Button button2 = (Button) findViewById(R.id.getres);

        listView.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);

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
                String facultyname=tarpstudentloginpinner.getSelectedItem().toString();
                int l=facultyname.length()-4;
                String facultyid=facultyname.substring(l);
                ref2=FirebaseDatabase.getInstance().getReference().child("schedule").child(dateString).child(facultyid);
//                Toast.makeText(BookSlot.this, dateString+facultyid, Toast.LENGTH_SHORT).show();
                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int flag=0;
                        for(int i=1;i<10;i++)
                        {
                            String timing=findtiming(i);
                            if (dataSnapshot.hasChild(Integer.toString(i)) && dataSnapshot.child(Integer.toString(i)).child("regno").getValue().toString().equals("--"))
                            {
                                users.add(new UserModel(false, timing));
                                flag=1;
                            }
                        }
                        if (flag==0)
                            Toast.makeText(BookSlot.this, "No free time!", Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        fDatabaseRoot=FirebaseDatabase.getInstance().getReference();
        fDatabaseRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> tarpstudentlogin = new ArrayList<String>();
                String q="";
                for (DataSnapshot tarpstudentloginnapshot: dataSnapshot.getChildren()) {
                    String name = tarpstudentloginnapshot.child("name").getValue(String.class);
                    String regnum = tarpstudentloginnapshot.getKey();
                    if(!regnum.equals("0001"))
                        q=name+" - "+regnum;
                    else
                        q=name;
                    if(regnum.length()==4)
                        tarpstudentlogin.add(q);
                }

                tarpstudentloginpinner = (Spinner) findViewById(R.id.teacherspin);
                tarpstudentloginpinner.setVisibility(View.GONE);
                ArrayAdapter<String> tarpstudentloginAdapter = new ArrayAdapter<String>(BookSlot.this, android.R.layout.simple_spinner_item, tarpstudentlogin);
                tarpstudentloginAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tarpstudentloginpinner.setAdapter(tarpstudentloginAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserModel model = users.get(i); //changed it to model because viewers will confused about it
                model.setSelected(true);
                users.set(i, model);
                if (preSelectedIndex > -1){
                    UserModel preRecord = users.get(preSelectedIndex);
                    preRecord.setSelected(false);
                    users.set(preSelectedIndex, preRecord);
                }
                preSelectedIndex = i;
                adapter.updateRecords(users);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= DateFormat.getDateInstance().format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.date_show);
        textView.setText(currentDateString);
        Button button2 = (Button) findViewById(R.id.getres);
        Spinner tarpstudentloginpinner = (Spinner) findViewById(R.id.teacherspin);
        button2.setVisibility(View.VISIBLE);
        tarpstudentloginpinner.setVisibility(View.VISIBLE);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setVisibility(View.VISIBLE);
    }
}
