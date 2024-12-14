package com.example.feminae_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class bf3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bf3);

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(bf3.this,bf1.class);
                startActivity(in);
            }
        });

        Button bt = (Button) findViewById(R.id.btn1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(bf3.this,bf17.class);
                startActivity(in);
            }
        });

        Button ht = (Button) findViewById(R.id.btn2);
        ht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(bf3.this,bf18.class);
                startActivity(in);
            }
        });

        Button nt = (Button) findViewById(R.id.btn3);
        nt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(bf3.this,bf19.class);
                startActivity(in);
            }
        });
    }
}