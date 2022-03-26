package com.example.bayongapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Registration extends AppCompatActivity {
    EditText fname,mname,lname,eMail,mobileno,passwrd;
    Button registerbtn;
    TextView lgnpg;
    FirebaseAuth fAuth;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fname = findViewById(R.id.firstName);
        mname = findViewById(R.id.middleName);
        lname = findViewById(R.id.lastName);
        eMail = findViewById(R.id.email);
        mobileno = findViewById(R.id.mobileNumber);
        passwrd = findViewById(R.id.password);
        registerbtn = findViewById(R.id.registerBtn);
        lgnpg = findViewById(R.id.loginPage);

        fAuth = FirebaseAuth.getInstance();
        progressbar = findViewById(R.id.progressBar);

        registerbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = eMail.getText().toString().trim();
                String password = passwrd.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    eMail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    passwrd.setError("Password is required.");
                    return;
                }
                if(password.length() <6){
                    passwrd.setError("Password must be >= 6 characters");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registration.this, "User successfully registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                });



            }
        });


    }
}