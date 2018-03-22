package com.example.naman.tarp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FindFaculty extends AppCompatActivity {

    public EditText inputname;
    public TextView facemail,faccabno, facphno;
    public Button find;
    String facid;
    public void setFacid(String x){
        facid=x;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_faculty);
        if (getIntent().getStringExtra("facid")== null) {
            setFacid("0000");
        } else {
            setFacid(getIntent().getStringExtra("facid"));
        }
        inputname = (EditText) findViewById(R.id.faculty_name);
        find = (Button) findViewById(R.id.btn_search);
        facemail = (TextView) findViewById(R.id.faculty_email);
        facphno = (TextView) findViewById(R.id.faculty_phno);
        faccabno = (TextView) findViewById(R.id.faculty_cabno);
        //Toast.makeText(FindFaculty.this, "Huehuehue", Toast.LENGTH_SHORT).show();
//        faccabno.setText("Cabin");
//        facphno.setText("Phone");
//        facemail.setText("Email");
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String facultynameinput = inputname.getText().toString().trim();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference databaseReference= database.getReference();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        int flag = 0;
                        for (DataSnapshot snapshot : children) {
//                            if(!snapshot.getKey().equals("schedule") && snapshot.child("name").equals(facultynameinput))
//                            {
//                                String x = "Email: " + details.getEmail();
//                                String y = "Phone Number: " + details.getPhone().toString();
//                                String z = "Cabin Number: " + details.getCabin();
//                                facemail.setText(x);
//                                facphno.setText(y);
//                                faccabno.setText(z);
//                                flag = 1;
//                                break;
//                            }
                            String g=snapshot.child("name").getKey();
                            if (g.length() <= 4 && !g.equals("0001"))
                            {
                                TeacherDetails details = snapshot.getValue(TeacherDetails.class);
                                if (details.getName()!=null && details.getName().toLowerCase().contains(facultynameinput.toLowerCase()))
                                {
//                                    Toast.makeText(FindFaculty.this, snapshot.getKey().toString(), Toast.LENGTH_SHORT).show();
                                    String x = "Email: " + details.getEmail();
                                    String y = "Phone Number: " + details.getPhone().toString();
                                    String z = "Cabin Number: " + details.getCabin();
                                    facemail.setText(x);
                                    facphno.setText(y);
                                    faccabno.setText(z);
                                    flag = 1;
                                    break;
                                }
                            }
                            else
                                continue;
                        }
                        if (flag == 0) {
                            facemail.setText("");
                            facphno.setText("No such faculty found!");
                            faccabno.setText("Please check spelling and try again");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
