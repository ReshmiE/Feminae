package com.example.feminae_project;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ic2 extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ic2);


        ImageButton fe = (ImageButton) findViewById(R.id.feed);
        fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ic2.this,ic3.class);
                startActivity(in);
            }
        });

        ImageButton sl = (ImageButton) findViewById(R.id.sleep);
        sl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ic2.this,ic5.class);
                startActivity(in);
            }
        });

        ImageButton vacc = (ImageButton) findViewById(R.id.vaccin);
        vacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ic2.this,ic6.class);
                startActivity(in);
            }
        });

        ImageButton ca = (ImageButton) findViewById(R.id.care);
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ic2.this,ic7.class);
                startActivity(in);
            }
        });

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ic2.this,home.class);
                startActivity(in);
            }
        });

        TextView textView = findViewById(R.id.name);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        // Obtain a reference to the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

// Obtain a reference to the child node
        DatabaseReference childRef = databaseRef.child("User").child(userID).child("babyinfo");


        childRef.child("babyname").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String babyname = snapshot.getValue(String.class);
                    textView.setText(babyname);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });
        TextView textView1 = findViewById(R.id.date);
        TextView textView2 = findViewById(R.id.bt);
        TextView textView3 = findViewById(R.id.et);

        DatabaseReference childRef1 = databaseRef.child("User").child(userID).child("feeding");

        childRef1.child("dof").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String dof = snapshot.getValue(String.class);
                    textView1.setText(dof);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });
        childRef1.child("bt").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String bt = snapshot.getValue(String.class);
                    textView2.setText(bt);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });

        childRef1.child("et").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String et = snapshot.getValue(String.class);
                    textView3.setText(et);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });


        TextView textView4 = findViewById(R.id.date1);
        TextView textView5 = findViewById(R.id.tf);
        TextView textView6 = findViewById(R.id.es);

        DatabaseReference childRef2 = databaseRef.child("User").child(userID).child("feedingsolid");

        childRef2.child("dof").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String dof = snapshot.getValue(String.class);
                    textView4.setText(dof);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });
        childRef2.child("tof").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String bt = snapshot.getValue(String.class);
                    textView5.setText(bt);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });

        childRef2.child("solid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String et = snapshot.getValue(String.class);
                    textView6.setText(et);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });


        TextView textView7 = findViewById(R.id.date2);
        TextView textView8 = findViewById(R.id.bt2);
        TextView textView9 = findViewById(R.id.et2);

        DatabaseReference childRef3 = databaseRef.child("User").child(userID).child("sleeping");

        childRef3.child("dos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String dof = snapshot.getValue(String.class);
                    textView7.setText(dof);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });
        childRef3.child("bt").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String bt = snapshot.getValue(String.class);
                    textView8.setText(bt);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });

        childRef3.child("et").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String et = snapshot.getValue(String.class);
                    textView9.setText(et);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(ContentValues.TAG, "Error retrieving date value: " + error.getMessage());

            }
        });
    }

}