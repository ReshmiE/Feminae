package com.example.feminae_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText memail,mpassword;
    Button btn_signin;
    FirebaseAuth fAuth;

    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mpassword=findViewById(R.id.txt_password);
        mpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=mpassword.getRight()-mpassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=mpassword.getSelectionEnd();
                        if(passwordVisible){
                            mpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            mpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;

                        }else{
                            mpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            mpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        mpassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        memail= findViewById(R.id.txt_email);
        mpassword= findViewById(R.id.txt_password);
        btn_signin= findViewById(R.id.btn_login);
        fAuth= FirebaseAuth.getInstance();
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                if (email.isEmpty()) {
                    memail.setError("Email is Required.");
                    memail.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    mpassword.setError("Password is Required");
                    mpassword.requestFocus();
                    return;
                }
                if(password.length() <6) {
                    mpassword.setError("Password Must be >= 6 Characters");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Logged in Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),home.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        } }
                }); }
        });

        TextView txtsignup = (TextView)findViewById(R.id.register);
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(login.this,register.class);
                startActivity(in);
            }
        });

        TextView txtfp = (TextView)findViewById(R.id.txtfp);
        txtfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(login.this,forgotpassword.class);
                startActivity(in);
            }
        });

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), home.class));
            finish();
        }
    }
}



