package com.example.naman.tarp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FacultySignUp extends AppCompatActivity {

    public EditText nam,empidno,pass,pass2,email,phno,cabin;
    public Button b1;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_sign_up);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        nam= (EditText) findViewById(R.id.name);
        email= (EditText) findViewById(R.id.email);
        phno= (EditText) findViewById(R.id.phno);
        cabin= (EditText) findViewById(R.id.cabin);
        empidno= (EditText) findViewById(R.id.empid);
        pass= (EditText) findViewById(R.id.password);
        pass2= (EditText) findViewById(R.id.password2);
        b1=(Button)findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String name=nam.getText().toString().trim();
                final String password=pass.getText().toString().trim();
                final String password2=pass2.getText().toString().trim();
                final String em=email.getText().toString().trim();
                final String phone=phno.getText().toString().trim();
                final String cabinno=cabin.getText().toString().trim();
                final String emnum=empidno.getText().toString().trim();

                Map<String,Object> taskMap = new HashMap<>();
                taskMap.put("name", name);
                taskMap.put("empid", emnum);
                taskMap.put("pass", password);
                taskMap.put("email", em);
                taskMap.put("phone", phone);
                taskMap.put("cabin", cabinno);
                if(password.equals(password2)) {
                    mDatabase.child(emnum).setValue(taskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        //mDatabase.updateChildren(taskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(FacultySignUp.this, "Stored", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FacultySignUp.this, Landing.class);
                                intent.putExtra("empid",emnum);
                                startActivity(intent);
                            } else {
                                Toast.makeText(FacultySignUp.this, "Error while storing", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(FacultySignUp.this, "Both passwords do not match! Check and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
