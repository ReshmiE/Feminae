package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class pt7 extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private TextView countdownTextView;
    private CountDownTimer countDownTimer;


    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private Date lastDate;
    private Date nextDate;

    private String futuredateString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt7);

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pt7.this,home.class);
                startActivity(in);
            }
        });

        // Obtain a reference to the TextView
        TextView textView = findViewById(R.id.pms);//last
        TextView textView2 = findViewById(R.id.up);//next
        TextView textView3 = findViewById(R.id.text_view_countdown);


        Button btn_diet = (Button) findViewById(R.id.btn_diet);
        btn_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pt7.this, pt5.class);
                startActivity(in);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        // Obtain a reference to the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

// Obtain a reference to the child node
        DatabaseReference childRef = databaseRef.child("User").child(userID).child("periodtracker");


// Add a ValueEventListener to the child node to retrieve the date value
        childRef.child("last").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    // Retrieve the date value as a String
                    String dateString = dataSnapshot.getValue(String.class);

                    // Parse the date string into a Date object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date date = null;
                    try {
                        date = dateFormat.parse(dateString);
                    } catch (ParseException e) {
                        Log.e(TAG, "Error parsing date string: " + e.getMessage());
                    }

                    // Format the Date object into a new string format
                    SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String displayDate = displayFormat.format(date);

                    // Set the formatted date string to the TextView
                    textView.setText(displayDate);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error retrieving date value: " + databaseError.getMessage());
            }
        });

        childRef.child("next").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String number = snapshot.getValue(String.class).trim();
                    textView2.setText(number);


                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date nextDate = null;
                    try {
                        nextDate = dateFormat.parse(number);
                    } catch (ParseException e) {
                        Log.e(TAG, "Error parsing date string: " + e.getMessage());
                    }

// Get the current date
                    Calendar calendar = Calendar.getInstance();
                    Date currentDate = calendar.getTime();

// Calculate the difference between the next date and the current date in milliseconds
                    long difference = nextDate.getTime() - currentDate.getTime();

// Convert the difference to days
                    long daysDifference = difference / (1000 * 60 * 60 * 24);

// Display the difference in days
                    textView3.setText(" " + daysDifference);

                    countDownTimer = new CountDownTimer(daysDifference * 24 * 60 * 60 * 1000, 1000) {
                        // Update the countdown in TextView every second
                        @Override
                        public void onTick(long millisUntilFinished) {
                            long seconds = millisUntilFinished / 1000;
                            long minutes = seconds / 60;
                            long hours = minutes / 60;
                            long days = hours / 24;

                            String countdown = String.format(Locale.getDefault(), "%d Days Left",
                                    days);
                            textView3.setText(countdown);
                        }

                        // Show a message when the countdown is finished
                        @Override
                        public void onFinish() {
                            textView3.setText("Your period starts today");
                            textView.setText(textView2.getText().toString());
                            childRef.child("cycle").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        String number = snapshot.getValue(String.class).trim();

                                        try {
                                            int cyclelen = Integer.parseInt(number);
                                            String numberString = String.valueOf(cyclelen);

                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                            Date date = dateFormat.parse(textView.getText().toString());
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTime(date);
                                            calendar.add(Calendar.DATE, cyclelen);
                                            futuredateString = dateFormat.format(calendar.getTime());
                                            textView2.setText(futuredateString);
                                            onTick();

                                        } catch (NumberFormatException e) {
                                            Log.e(TAG, "error" + e.getMessage());
                                        } catch (ParseException e) {
                                            Log.e(TAG, "Error" + e.getMessage());
                                        }
                                    }
                                }






                                private void onTick() {
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e(TAG, "Error retrieving date value: " + error.getMessage());

                                }
                            });
                        }
                    }.start();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Error retrieving date value: " + error.getMessage());

            }
        });

    }
}