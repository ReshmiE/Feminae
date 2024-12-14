package com.example.feminae_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private EditText Text_email;
    private Button btn_reset;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        TextView txtgb = (TextView)findViewById(R.id.txtgb);
        txtgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(forgotpassword.this,login.class);
                startActivity(in);
            }
        });

        Text_email = (EditText)findViewById((R.id.Text_email));
        btn_reset = (Button) findViewById((R.id.btn_reset));

        mAuth = FirebaseAuth.getInstance();

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword(){
        String emailid = Text_email.getText().toString().trim();
        if (emailid.isEmpty()) {
            Text_email.setError("Email is Required.");
            Text_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            Text_email.setError("Please provide valid email.");
            Text_email.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(emailid).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgotpassword.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(forgotpassword.this, "Try again! Something happened!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}