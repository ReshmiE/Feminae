package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class ic1 extends AppCompatActivity {

    private Button done;
    private EditText editText1,editText2,editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ic1);

        editText1=(EditText) findViewById(R.id.txt_babyname);
        editText2=(EditText) findViewById(R.id.txt_babysex);
        editText3=(EditText) findViewById(R.id.txt_birthday);

        done=findViewById(R.id.btn_done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



        String babyname = editText1.getText().toString();
        String bsex = editText2.getText().toString();
        String bbirth = editText3.getText().toString();


                if (babyname.isEmpty()) {
                    editText1.setError(" Baby Name is Required.");
                    editText1.requestFocus();
                    return;
                }

                if (bsex.isEmpty()) {
                    editText2.setError("Baby sex is Required.");
                    editText2.requestFocus();
                    return;
                }
                if (bbirth.isEmpty()) {
                    editText3.setError("Birth Date is Required.");
                    editText3.requestFocus();
                    return;
                }

        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("babyname", babyname);
        userUpdates.put("sex", bsex);
        userUpdates.put("birth", bbirth);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref1 = ref.child("User");
        DatabaseReference ref2 = ref1.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference ref3 = ref2.child("babyinfo");

        ref3.updateChildren(userUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error != null){
                    Log.w(TAG,"failed to read data",error.toException());
                }
                else{
                    Toast.makeText(ic1.this, "Successful", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(ic1.this,ic2.class);
                    startActivity(in);
                }
            }
        });

            }
        });
    }
}