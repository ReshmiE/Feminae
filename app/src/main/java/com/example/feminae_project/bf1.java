package com.example.feminae_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class bf1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bf1);

        ImageButton imgbb = (ImageButton) findViewById(R.id.imgbb);
        imgbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(bf1.this,bf2.class);
                startActivity(in);
            }
        });

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(bf1.this,home.class);
                startActivity(in);
            }
        });

        ImageButton imgbf = (ImageButton) findViewById(R.id.imgbf);
        imgbf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(bf1.this,bf4.class);
                startActivity(in);
            }
        });

    }
}