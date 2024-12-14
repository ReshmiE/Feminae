package com.example.feminae_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
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

import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView reg,lh;
    private EditText name,dob,email,phone,phony,password;
    boolean passwordVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        password=findViewById(R.id.Text_password);
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=password.getSelectionEnd();
                        if(passwordVisible){
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;

                        }else{
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();

        lh=(TextView)findViewById(R.id.txtlh);
        lh.setOnClickListener(this);

        reg=(Button)findViewById(R.id.btn_signup);
        reg.setOnClickListener(this);

        name=(EditText) findViewById(R.id.Text_Name);
        dob=(EditText) findViewById(R.id.Text_DOB);
        email=(EditText) findViewById(R.id.Text_email);
        phone=(EditText) findViewById(R.id.Text_PhoneNo);
        phony=(EditText)findViewById(R.id.phone);
        password=(EditText) findViewById(R.id.Text_password);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.txtlh:
                startActivity(new Intent(this, login.class));
                break;

            case R.id.btn_signup:
                reg();
                break;
        }

    }
    private void reg() {
        String emailid = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String fullname = name.getText().toString().trim();
        String date = dob.getText().toString().trim();
        String phoneno = phone.getText().toString().trim();

        if (fullname.isEmpty()) {
            name.setError("Name is Required.");
            name.requestFocus();
            return;
        }

        if (date.isEmpty()) {
            dob.setError("Date is Required.");
            dob.requestFocus();
            return;
        }
        if (emailid.isEmpty()) {
            email.setError("Valid Email-ID is Required.");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            email.setError("Please provide valid email.");
            email.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("Password is Required.");
            password.requestFocus();
            return;
        }
        if (phoneno.isEmpty()) {
            phone.setError("Phone No is Required.");
            phone.requestFocus();
            return;
        }
        if (pass.length() < 6) {
            password.setError("Minimum password length should be 6 characters!");
            password.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(emailid, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(fullname, date, emailid, phoneno, pass);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(register.this, "user has been registered successfully", Toast.LENGTH_LONG).show();
                                                Intent in = new Intent(register.this,login.class);
                                                startActivity(in);
                                            } else {
                                                Toast.makeText(register.this, "failed to register try again", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        } else {
                            Toast.makeText(register.this, "Email is already existing", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
