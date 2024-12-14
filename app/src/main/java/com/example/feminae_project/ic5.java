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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ic5 extends AppCompatActivity {

    private Button save;
    private EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ic5);

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ic5.this,ic2.class);
                startActivity(in);
            }
        });

        editText1 = (EditText) findViewById(R.id.date);
        editText2 = (EditText) findViewById(R.id.time_begin);
        editText3 = (EditText) findViewById(R.id.time_end);

        save = findViewById(R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String dos = editText1.getText().toString();
                String bt = editText2.getText().toString();
                String et = editText3.getText().toString();

                if (dos.isEmpty()) {
                    editText1.setError("Date of sleeping is Required.");
                    editText1.requestFocus();
                    return;
                }

                if (bt.isEmpty()) {
                    editText2.setError("Begin Time is Required.");
                    editText2.requestFocus();
                    return;
                }
                if (et.isEmpty()) {
                    editText3.setError("End Time is Required.");
                    editText3.requestFocus();
                    return;
                }

                Map<String, Object> userUpdates = new HashMap<>();
                userUpdates.put("dos", dos);
                userUpdates.put("bt", bt);
                userUpdates.put("et", et);


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref1 = ref.child("User");
                DatabaseReference ref2 = ref1.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DatabaseReference ref3 = ref2.child("sleeping");

                ref3.updateChildren(userUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error != null) {
                            Log.w(TAG, "Not saved", error.toException());
                        } else {
                            Toast.makeText(ic5.this, "Saved", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ic5.this, ic2.class);
                            startActivity(in);
                        }
                    }
                });

            }
        });
    }
}