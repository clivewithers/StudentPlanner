package com.example.clive.studentplanner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegister;
    private EditText editTxtEmail;
    private EditText editTxtPassword;
    private TextView txtSignIn;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Study Planner - Register ");
        }


        mAuth = FirebaseAuth.getInstance();

        btnRegister = (Button) findViewById(R.id.btnRegister);
        editTxtEmail = (EditText) findViewById(R.id.txtRegEmail);
        editTxtPassword = (EditText) findViewById(R.id.txtRegPassword);
        txtSignIn = (TextView) findViewById(R.id.txtSignIn);
        progressDialog = new ProgressDialog(this);

        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), CreateProfile.class));
        }

        btnRegister.setOnClickListener(this);
        txtSignIn.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTxtEmail.getText().toString().trim();
        String password = editTxtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(this, "You must enter an email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            // password is empty
            Toast.makeText(this, "You must enter super secret password", Toast.LENGTH_SHORT).show();
            return;
        }

        // if validations are ok show progress bar
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            // user is successfully registered and logged in
                            Toast.makeText(getApplicationContext(), "Account created and logged in", Toast.LENGTH_SHORT).show();
                            mAuth.sendPasswordResetEmail(mAuth.getCurrentUser().getEmail());
                            finish();
                            startActivity(new Intent(getApplicationContext(), CreateProfile.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "The email you entered already exists. Please use sign in page", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if( v == btnRegister){
            registerUser();
        }

        if(v == txtSignIn){
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
    }
}