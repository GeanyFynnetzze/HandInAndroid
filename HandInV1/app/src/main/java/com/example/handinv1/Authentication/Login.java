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

public class Login extends AppCompatActivity {
    EditText mPassword, mEmail;
    Button mLoginBtn;
    TextView mCreateBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPassword=findViewById(R.id.Password);
        mEmail=findViewById(R.id.Email);
        mCreateBtn=findViewById(R.id.createAcc);
        mLoginBtn=findViewById(R.id.loginBtn);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
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
                progressBar.setVisibility(View.INVISIBLE);

                //authentication for the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(Login.this, "Logged in like a boss, as " + user.getEmail(), Toast.LENGTH_SHORT).show();
                          //  startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            if (user.isEmailVerified()) loginActivity();
                            else
                                Toast.makeText(Login.this, "Verify your email first", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Error my man " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }


    private void loginActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }
}