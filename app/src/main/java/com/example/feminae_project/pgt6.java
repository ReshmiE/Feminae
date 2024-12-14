package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class pgt6 extends AppCompatActivity {

    private Button save;
    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgt6);


        save=findViewById(R.id.btn_con1);

        Spinner spinner = findViewById(R.id.spinner);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref1 = ref.child("User");
        DatabaseReference ref2 = ref1.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference ref3 = ref2.child("pggest");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                ref3.child("due_date").setValue(selectedOption);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

                save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            Toast.makeText(pgt6.this, "Saved", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(pgt6.this, pgt2.class);
                            startActivity(in);
            }
        });

    }
}