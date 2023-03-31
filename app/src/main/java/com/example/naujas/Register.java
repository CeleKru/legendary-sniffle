package com.example.naujas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registerUsername, registerEmail, registerPassword;
    private Button registerButton;
    private TextView loginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        registerUsername = findViewById(R.id.register_username);
        registerEmail = findViewById(R.id.register_email);
        registerPassword = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.RegisterButton);
        loginRedirect = findViewById(R.id.loginRedirect);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = registerUsername.getText().toString().trim();
                String email = registerEmail.getText().toString().trim();
                String pass = registerPassword.getText().toString().trim();
                
                if (email.isEmpty()){
                    registerEmail.setError("Email cannot be empty!");
                }
                if (pass.isEmpty()){
                    registerPassword.setError("Password cannot be empty!");
                } else {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            }else {
                                Toast.makeText(Register.this, "Register Failed!!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(Register.this, Login.class));
            }
        });

    }
}