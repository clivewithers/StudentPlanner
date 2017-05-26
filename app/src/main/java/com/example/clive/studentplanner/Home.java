package com.example.clive.studentplanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private EditText txtEditStudId;
    private Button btnCreate;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDetailsDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtEditStudId = (EditText) findViewById(R.id.txtEditTest);
        btnCreate = (Button) findViewById(R.id.button2);

        getSupportActionBar().setTitle("Study Planner - Home");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDetailsDatabaseReference = mFirebaseDatabase.getReference().child("test");

        btnCreate.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        if (v == btnCreate){
            StudentDetails studentDetails = new StudentDetails(txtEditStudId.getText().toString().trim());
            mDetailsDatabaseReference.setValue(studentDetails);
            Toast.makeText(getApplicationContext(), "Details created", Toast.LENGTH_SHORT).show();
        }
    }
}
