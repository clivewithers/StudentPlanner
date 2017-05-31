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

public class Login extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    private EditText editTxtEmail;
    private EditText editTxtPassword;
    private Button btnSignIn;
    private TextView editTxtRegister;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Study Planner - Login");
        }

        mAuth = FirebaseAuth.getInstance();

        //Initialize references to views
        btnSignIn = (Button) findViewById(R.id.btn_add);
        editTxtRegister = (TextView) findViewById(R.id.txtRegister);
        editTxtEmail = (EditText) findViewById(R.id.txtEmail);
        editTxtPassword = (EditText) findViewById(R.id.txtPassword);
        progressDialog  = new ProgressDialog(this);

        btnSignIn.setOnClickListener(this);
        editTxtRegister.setOnClickListener(this);

    }

    public void signIn(){
        final String email = editTxtEmail.getText().toString().trim();
        final String password = editTxtPassword.getText().toString().trim();

        //validates email entered
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "You must enter an email address", Toast.LENGTH_SHORT).show();
            return;
        }

        //validates password entered
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "You must enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging on.......");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == btnSignIn){
            signIn();
        }
        if (v == editTxtRegister){
            finish();
            startActivity(new Intent(getApplicationContext(), Register.class));
        }
    }
}
