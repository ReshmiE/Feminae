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
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class pgt1 extends AppCompatActivity {

    private Button save;

    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgt1);

        NumberPicker numberPicker=findViewById(R.id.numberPicker);
        textView=findViewById(R.id.np);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(9);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textView.setText(" "+newVal);
            }
        });


        save = findViewById(R.id.btn_con1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String gest_age = textView.getText().toString();

                Map<String, Object> userUpdates = new HashMap<>();
                userUpdates.put("gest_age", gest_age);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref1 = ref.child("User");
                DatabaseReference ref2 = ref1.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DatabaseReference ref3 = ref2.child("pggest");


                ref3.updateChildren(userUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error != null) {
                            Log.w(TAG, "Not saved", error.toException());
                        } else {
                            Toast.makeText(pgt1.this, "Saved", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(pgt1.this, pgt6.class);
                            startActivity(in);
                        }
                    }
                });

            }
        });

    }
}