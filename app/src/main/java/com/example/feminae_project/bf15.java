package com.example.feminae_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class bf15 extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bf15);

        button=findViewById(R.id.time);

        long duration= TimeUnit.MINUTES.toMillis(1);

        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long l) {
                button.setText("Started:"+l/1000);
            }

            @Override
            public void onFinish() {
                button.setText("Done");
                Intent in = new Intent(bf15.this,bf5.class);
                startActivity(in);
            }
        }.start();
    }
}