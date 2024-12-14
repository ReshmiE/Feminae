package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class pt1 extends AppCompatActivity {

    private Button save;
    private CalendarView cal;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt1);

        textView=findViewById(R.id.text);

        cal = (CalendarView) findViewById(R.id.cal);
        save = findViewById(R.id.btn_next1);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                        @Override
                                        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.set(year, month, dayOfMonth);
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                            String formattedDate = dateFormat.format(calendar.getTime());
                                            textView.setText(formattedDate);
                                        }
                                        });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String last = textView.getText().toString();

                        Map<String, Object> userUpdates = new HashMap<>();
                        userUpdates.put("last", last);

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference ref1 = ref.child("User");
                        DatabaseReference ref2 = ref1.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        DatabaseReference ref3 = ref2.child("periodtracker");


                        ref3.updateChildren(userUpdates, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error != null) {
                                    Log.w(TAG, "Not saved", error.toException());
                                } else {
                                    Toast.makeText(pt1.this, "Saved", Toast.LENGTH_LONG).show();
                                    Intent in = new Intent(pt1.this, pt2.class);
                                    startActivity(in);
                                }
                            }
                        });
                    }
                });
    }
}
