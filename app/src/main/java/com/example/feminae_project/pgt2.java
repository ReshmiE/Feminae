package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class pgt2 extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgt2);

        ImageButton b1 = (ImageButton) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt2.this, pgt2.class);
                startActivity(in);
            }
        });

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt2.this,home.class);
                startActivity(in);
            }
        });

        ImageButton b2 = (ImageButton) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt2.this, pgt3.class);
                startActivity(in);
            }
        });

        ImageButton b3 = (ImageButton) findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt2.this, pgt4.class);
                startActivity(in);
            }
        });
        ImageButton b4 = (ImageButton) findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt2.this, pgt5.class);
                startActivity(in);
            }
        });

        TextView textView = findViewById(R.id.gest);
        TextView textView1 = findViewById(R.id.due);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        // Obtain a reference to the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

// Obtain a reference to the child node
        DatabaseReference childRef = databaseRef.child("User").child(userID).child("pggest");


        childRef.child("gest_age").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String gest_age = snapshot.getValue(String.class);
                    textView.setText(gest_age);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(TAG, "Error retrieving date value: " + error.getMessage());

            }
        });

        childRef.child("due_date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String due_date = snapshot.getValue(String.class).trim();
                    textView1.setText(due_date);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(TAG, "Error retrieving date value: " + error.getMessage());

            }
        });
        ImageView imageView = findViewById(R.id.image1);
        TextView texty = findViewById(R.id.text9);

                childRef.child("gest_age").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String gest_age = snapshot.getValue(String.class).trim();
                            textView.setText(gest_age);

                            // Convert gest_age to an integer
                            final int[] gestAgeInt = {0}; // Set default value to 0
                            try {
                                gestAgeInt[0] = Integer.parseInt(gest_age);
                                Handler handler = new Handler();
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        gestAgeInt[0]++; // Increment gestAgeInt by 1
                                        handler.postDelayed(this, 30 * 24 * 60 * 60 * 1000); // Schedule the task again after 30 days
                                    }
                                };

// Call the runnable to start the periodic task
                                handler.postDelayed(runnable, 30 * 24 * 60 * 60 * 1000);

                            } catch (NumberFormatException e) {
                                Log.e(TAG, "Error parsing gestational age to integer: " + e.getMessage());
                            }


                            childRef.child("gest_age").setValue(String.valueOf(gestAgeInt[0]));


                            // Update ImageView based on gest_age
                            switch (gestAgeInt[0]) {
                                case 1:
                                    imageView.setImageResource(R.drawable.monone); // Set image for Month 1
                                    texty.setText("RED CURRENT BERRY");

                                    break;
                                case 2:
                                    imageView.setImageResource(R.drawable.montwo); // Set image for Month 2
                                    texty.setText("CHERRY");
                                    break;
                                // Add cases for other months and update ImageView and TextView accordingly
                                case 3:
                                    imageView.setImageResource(R.drawable.monthree); // Set image for Month 2
                                    texty.setText("PLUM");
                                    break;
                                case 4:
                                    imageView.setImageResource(R.drawable.monfour); // Set image for Month 2
                                    texty.setText("PEAR");
                                    break;
                                case 5:
                                    imageView.setImageResource(R.drawable.monfive); // Set image for Month 2
                                    texty.setText("GRAPE FRUIT");
                                    break;
                                case 6:
                                    imageView.setImageResource(R.drawable.monsix); // Set image for Month 2
                                    texty.setText("PAPAYA");
                                    break;
                                case 7:
                                    imageView.setImageResource(R.drawable.monseven); // Set image for Month 2
                                    texty.setText("PINEAPPLE");
                                    break;
                                case 8:
                                    imageView.setImageResource(R.drawable.moneight); // Set image for Month 2
                                    texty.setText("CANTALOUPE");
                                    break;
                                default:
                                    // Set a default image if no matching case is found
                                    imageView.setImageResource(R.drawable.monnine);
                                    texty.setText("WATERMELON");
                                    break;
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Error retrieving gestational age value: " + error.getMessage());
                    }
                });
    }
}