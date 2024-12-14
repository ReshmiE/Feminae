package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class pt3 extends AppCompatActivity {

    private Button save;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt3);

        NumberPicker numberPicker=findViewById(R.id.numberPicker);
        textView=findViewById(R.id.np2);


        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(45);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textView.setText(" "+newVal);
            }
        });


        save = findViewById(R.id.btn_next3);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String cycle = textView.getText().toString();

                Map<String, Object> userUpdates = new HashMap<>();
                userUpdates.put("cycle", cycle);

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
                            Toast.makeText(pt3.this, "Saved", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(pt3.this, pt4.class);
                            startActivity(in);
                        }
                    }
                });

            }
        });
    }
}