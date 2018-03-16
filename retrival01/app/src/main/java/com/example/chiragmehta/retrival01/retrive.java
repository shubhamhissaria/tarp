package com.example.chiragmehta.retrival01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class retrive extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Schedule schedule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrive);

        schedule = new Schedule();
        listView=(ListView)findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref = database.getReference("Schedule");
        list = new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.retrive_data,R.id.retriveData, list);
        ref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            user.getValue(Schedule.class);
                            String slotnum= user.getValue().getKey().toString();
                            list.add(schedule.getRegno().toString()+"");



                        }
                        listView.setAdapter(adapter);

                    }

                }


            }
            @Override
            public void onCancelled(DatabaseError databaseErrpr){

            }
        });

    }
}
