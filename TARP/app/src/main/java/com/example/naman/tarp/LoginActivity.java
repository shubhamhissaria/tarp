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

public class LoginActivity extends AppCompatActivity {

    public EditText inputregno, inputPassword;
    public ProgressBar progressBar;
    public Button btnStudSignup, btnFacSignup, btnLogin, btnReset;

    DatabaseReference data;
    String user, passw,regnum,passwor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputregno = (EditText) findViewById(R.id.regnum);
        inputPassword = (EditText) findViewById(R.id.passwo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnStudSignup = (Button) findViewById(R.id.btn_studsignup);
        btnFacSignup = (Button) findViewById(R.id.btn_facsignup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        regnum = inputregno.getText().toString().trim();
        passwor = inputPassword.getText().toString().trim();

        data = FirebaseDatabase.getInstance().getReference().child(regnum);
        //Toast.makeText(getApplicationContext(), password, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), data.child("name").toString(), Toast.LENGTH_SHORT).show();

        btnStudSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, StudentSignUp.class));
            }
        });
        btnFacSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, FacultySignUp.class));
            }
        });

//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (regnum == null) {
                    Toast.makeText(getApplicationContext(), "Enter registration number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (passwor == null) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            user = dataSnapshot.child("regno").getValue(String.class);
                            Toast.makeText(getApplicationContext(), user, Toast.LENGTH_SHORT).show();
                            passw = dataSnapshot.child("pass").getValue(String.class);
                            if (regnum.equals(user) && passwor.equals(passw)) {
//                            Intent intent = new Intent(LoginActivity.this, DisplayHome.class);
//                            intent.putExtra("accno", accnumber);
//                            startActivity(intent);
//                            finish();
                                Toast.makeText(LoginActivity.this, getString(R.string.auth_success), Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(LoginActivity.this, getString(R.string.database_error), Toast.LENGTH_LONG).show();
                        }
                    });
                }
//
//
            }
        });
    }
}