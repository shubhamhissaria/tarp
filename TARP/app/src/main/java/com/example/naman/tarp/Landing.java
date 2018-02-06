package com.example.naman.tarp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Landing extends AppCompatActivity {

    public Button findfa,books;
    String regnumb;
    public void setaccountnumber(String x){
        regnumb=x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        if (getIntent().getStringExtra("accno")== null) {
            setaccountnumber("15BCE0000");
        } else {
            setaccountnumber(getIntent().getStringExtra("accno"));
        }
        findfa = (Button) findViewById(R.id.btn_findfac);
        books = (Button) findViewById(R.id.btn_book);
        findfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, Landing.class);//change to find faculty
                intent.putExtra("regno", regnumb);
                startActivity(intent);
                finish();
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, Landing.class);//change to book schedule
                intent.putExtra("regno", regnumb);
                startActivity(intent);
                finish();
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, LoginActivity.class);//change to book schedule
                startActivity(intent);
                finish();
            }
        });
    }
}
