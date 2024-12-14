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

public class ic4 extends AppCompatActivity {

    private Button save;
    private EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ic4);

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ic4.this,ic2.class);
                startActivity(in);
            }
        });

        Button btn_milk = (Button) findViewById(R.id.btn_milk);
        btn_milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ic4.this,ic3.class);
                startActivity(in);
            }
        });

        editText1 = (EditText) findViewById(R.id.dof);
        editText2 = (EditText) findViewById(R.id.tof);
        editText3 = (EditText) findViewById(R.id.time_end);

        save = findViewById(R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String dof = editText1.getText().toString();
                String tof = editText2.getText().toString();
                String solid = editText3.getText().toString();

                if (dof.isEmpty()) {
                    editText1.setError(" Date of feeding is Required.");
                    editText1.requestFocus();
                    return;
                }

                if (tof.isEmpty()) {
                    editText2.setError("Time of feeding is Required.");
                    editText2.requestFocus();
                    return;
                }
                if (solid.isEmpty()) {
                    editText3.setError("Solid taken is Required.");
                    editText3.requestFocus();
                    return;
                }

                Map<String, Object> userUpdates = new HashMap<>();
                userUpdates.put("dof", dof);
                userUpdates.put("tof", tof);
                userUpdates.put("solid", solid);


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref1 = ref.child("User");
                DatabaseReference ref2 = ref1.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DatabaseReference ref3 = ref2.child("feedingsolid");

                ref3.updateChildren(userUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error != null) {
                            Log.w(TAG, "Not saved", error.toException());
                        } else {
                            Toast.makeText(ic4.this, "Saved", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(ic4.this, ic2.class);
                            startActivity(in);
                        }
                    }
                });

            }
        });
    }
}