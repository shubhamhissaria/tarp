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

    public class Teacherdetails implements Serializable
    {
        public String nameof,email,empid,phno,cab,pass;
        public Teacherdetails(){ }
        public Teacherdetails(String a, String b, String c, String d, String e, String f)
        {
            this.nameof=a;
            this.email=b;
            this.empid=c;
            this.phno=d;
            this.cab=e;
            this.pass=f;
        }
        public String getNameof()
        {
            return nameof;
        }
        public String getEmail()
        {
            return nameof;
        }
        public String getEmpid()
        {
            return nameof;
        }
        public String getPhno()
        {
            return nameof;
        }
        public String getCab()
        {
            return nameof;
        }
        public String getPass()
        {
            return nameof;
        }
    }
    public EditText inputname;
    public Button find;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_faculty);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= database.getReference();
        inputname = (EditText) findViewById(R.id.faculty_name);
        find = (Button) findViewById(R.id.btn_search);
        final String facultynameinput = inputname.getText().toString();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Teacherdetails details= new Teacherdetails();
                for(DataSnapshot child: children)
                {
                    details = child.getValue(Teacherdetails.class);
                    if(details.nameof.equals(facultynameinput))
                    {
                            inputname.setText("huehuehue found you");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
