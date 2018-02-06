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

    public EditText nam,empid,pass,pass2;
    public Button b1;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_sign_up);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        nam= (EditText) findViewById(R.id.name);
        empid= (EditText) findViewById(R.id.regno);
        pass= (EditText) findViewById(R.id.password);
        pass2= (EditText) findViewById(R.id.password2);
        b1=(Button)findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name=nam.getText().toString().trim();
                final String empno=empid.getText().toString().trim();
                final String password=pass.getText().toString().trim();

                Map<String,Object> taskMap = new HashMap<>();
                taskMap.put("name", name);
                taskMap.put("regno", empno);
                taskMap.put("pass", password);
                mDatabase.child(empno).setValue(taskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    //mDatabase.updateChildren(taskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(FacultySignUp.this, "Stored", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(FacultySignUp.this, Landing.class);
                            intent.putExtra("regno", empno);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(FacultySignUp.this, "Error while storing", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
