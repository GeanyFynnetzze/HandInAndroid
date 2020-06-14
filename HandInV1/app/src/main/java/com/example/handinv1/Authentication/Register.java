package com.example.handinv1.Authentication;

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

import com.example.handinv1.MainActivity;
import com.example.handinv1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
   EditText mName, mPassword, mEmail;
   Button mRegisterBtn;
   TextView mLoginBtn;
   FirebaseAuth fAuth;
   ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPassword=findViewById(R.id.Password);
        mEmail=findViewById(R.id.Email);
        mRegisterBtn=findViewById(R.id.RegisterBtn);
        mLoginBtn=findViewById(R.id.textView4);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email required");
                }
                if (TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password required");
                }

                if (password.length() < 6)
                {
                    mPassword.setError("Password must be at least 6 Characters");
                    return;
                }
                progressBar.setVisibility(View.GONE);

                //register the user

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser user =fAuth.getCurrentUser();
                           user.sendEmailVerification();
                            Toast.makeText(Register.this, "Verify your email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }
                        else
                        {
                            Toast.makeText(Register.this,"Error my man" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}
