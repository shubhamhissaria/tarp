package com.example.naman.tarp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class StudentSignUp extends AppCompatActivity {

    public EditText nam,accn,pinpass,pin2pass,num;
    public Button b1;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        nam= (EditText) findViewById(R.id.name);
        accn= (EditText) findViewById(R.id.accnno);
        pinpass= (EditText) findViewById(R.id.pin);
        pin2pass= (EditText) findViewById(R.id.pin2);
        num= (EditText) findViewById(R.id.phno);
        b1=(Button)findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name=nam.getText().toString().trim();
                final String accno=accn.getText().toString().trim();
                int pin=Integer.parseInt(pinpass.getText().toString().trim());
                String number=num.getText().toString().trim();
                String bal="1000";

                Map<String,Object> taskMap = new HashMap<>();
                taskMap.put("name", name);
                taskMap.put("accno", accno);
                taskMap.put("pin", pin);
                taskMap.put("number", number);
                taskMap.put("balance", bal);
                mDatabase.child(accno).setValue(taskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    //mDatabase.updateChildren(taskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Stored", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, DisplayHome.class);
                            intent.putExtra("accno", accno);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Register.this, "Error while storing", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
