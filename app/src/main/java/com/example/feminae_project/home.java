package com.example.feminae_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {
    private FirebaseAuth fAuth;
    private boolean pagesVisited,pagesVisited1,pagesVisited2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton imgp = (ImageButton) findViewById(R.id.profile);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(home.this,myprofile.class);
                startActivity(in);
            }
        });

        fAuth = FirebaseAuth.getInstance();
        // Retrieve the value of pagesVisited from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        pagesVisited = sharedPreferences.getBoolean("pagesVisited", false);

        ImageButton imgbpt = (ImageButton) findViewById(R.id.imgbpt);
        imgbpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (!pagesVisited) {
                    intent = new Intent(home.this, pt0.class);
                    pagesVisited = true;
                } else {
                    intent = new Intent(home.this, pt8.class);
                }
                // Store the updated value of pagesVisited in shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("pagesVisited", pagesVisited);
                editor.apply();
                startActivity(intent);
            }
        });
        fAuth = FirebaseAuth.getInstance();
        // Retrieve the value of pagesVisited from shared preferences
        SharedPreferences sharedPreferences1 = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        pagesVisited1 = sharedPreferences1.getBoolean("pagesVisited1", false);

        ImageButton imgbpg = (ImageButton) findViewById(R.id.imgbpg);
        imgbpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (!pagesVisited1) {
                    intent = new Intent(home.this, pgt0.class);
                    pagesVisited1 = true;
                } else {
                    intent = new Intent(home.this, pgt7.class);
                }
                // Store the updated value of pagesVisited in shared preferences
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putBoolean("pagesVisited1", pagesVisited1);
                editor.apply();
                startActivity(intent);
            }
        });
        fAuth = FirebaseAuth.getInstance();
        // Retrieve the value of pagesVisited from shared preferences
        SharedPreferences sharedPreferences2 = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        pagesVisited2 = sharedPreferences2.getBoolean("pagesVisited2", false);

        ImageButton imgbic = (ImageButton) findViewById(R.id.imgbic);
        imgbic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (!pagesVisited2) {
                    intent = new Intent(home.this, ic0.class);
                    pagesVisited2 = true;
                } else {
                    intent = new Intent(home.this, ic8.class);
                }
                // Store the updated value of pagesVisited in shared preferences
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.putBoolean("pagesVisited2", pagesVisited2);
                editor.apply();
                startActivity(intent);
            }
        });

        ImageButton imgbfb = (ImageButton) findViewById(R.id.imgbbf);
        imgbfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(home.this,bf0.class);
                startActivity(in);
            }
        });

        TextView txtlogout = (TextView) findViewById(R.id.txtlogout);
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }

            private void logout() {
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
            }
        });
    }
}