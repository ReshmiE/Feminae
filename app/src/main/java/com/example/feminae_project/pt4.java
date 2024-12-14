package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class pt4 extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt4);


        // Obtain a reference to the TextView
        TextView textView = findViewById(R.id.pms);//last
        TextView textView1 = findViewById(R.id.lc);//cycle
        TextView textView2 = findViewById(R.id.up);//next

        Button imgp = (Button) findViewById(R.id.btn_track);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String next = textView2.getText().toString();

                Map<String, Object> userUpdates = new HashMap<>();
                userUpdates.put("next", next);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref1 = ref.child("User");
                DatabaseReference ref2 = ref1.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DatabaseReference ref3 = ref2.child("periodtracker");


                ref3.updateChildren(userUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error != null) {
                            Log.w(TAG, "", error.toException());
                        } else {
                            Intent in = new Intent(pt4.this, pt7.class);
                            startActivity(in);
                        }
                    }
                });
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

        childRef.child("cycle").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String number = snapshot.getValue(String.class).trim();
                    textView1.setText(number);

                    try {
                        int cyclelen = Integer.parseInt(number);
                        String numberString = String.valueOf(cyclelen);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        Date date = dateFormat.parse(textView.getText().toString());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.DATE, cyclelen);
                        String futuredateString = dateFormat.format(calendar.getTime());
                        textView2.setText(futuredateString);
                    } catch (NumberFormatException e) {
                        Log.e(TAG, "error" + e.getMessage());
                    } catch (ParseException e) {
                        Log.e(TAG, "Error" + e.getMessage());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Error retrieving date value: " + error.getMessage());

            }
        });
    }
}