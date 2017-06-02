package com.example.clive.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfile extends AppCompatActivity implements View.OnClickListener {

    private EditText txtEditStudId;
    private EditText txtEditFirstName;
    private EditText txtEditLastName;
    private Spinner spnEditMajor;
    private Button btnCreate;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDetailsDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
        }

        txtEditStudId = (EditText) findViewById(R.id.txtEditStudentId);
        txtEditFirstName = (EditText) findViewById(R.id.txtEditFirst);
        txtEditLastName = (EditText) findViewById(R.id.txtEditLast);
        spnEditMajor = (Spinner) findViewById(R.id.spnEditMajor);
        btnCreate = (Button) findViewById(R.id.btnCreate);

        ArrayAdapter<String> institutionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.major));
        spnEditMajor.setAdapter(institutionAdapter);

        mFirebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mFirebaseAuth.getCurrentUser();

        mDetailsDatabaseReference = FirebaseDatabase.getInstance()
                .getReference().child("student").child(mFirebaseAuth.getCurrentUser().getUid());

        btnCreate.setOnClickListener(this);

        }

    @Override
    //Places the 2 image menu top right of screen
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate and add items to action bar if present.
        getMenuInflater().inflate(R.menu.top_menu_home, menu);
        return true;
    }

    public void saveDetails(){
        if(spnEditMajor.getSelectedItem().toString().equals("Select Major")){
            Toast.makeText(getApplicationContext(), "No Major selected", Toast.LENGTH_SHORT).show();
        }else{
            String Id = txtEditStudId.getText().toString().trim();
            String first = txtEditFirstName.getText().toString().trim();
            String last = txtEditLastName.getText().toString().trim();
            String major = spnEditMajor.getSelectedItem().toString().trim();

            StudentDetails studentDetails = new StudentDetails(Id, first, last, major);

            mDetailsDatabaseReference.setValue(studentDetails);

            Toast.makeText(getApplicationContext(), "Profile created", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        }
    }
    @Override
    public void onClick(View v) {
        if (v == btnCreate){
            saveDetails();
//            StudentDetails studentDetails = new StudentDetails(txtEditStudId.getText().toString().trim());
//            mDetailsDatabaseReference.setValue(studentDetails);
            Toast.makeText(getApplicationContext(), "Details created", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        switch (item.getItemId()) {
            case R.id.actionLogout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), Login.class));
                return true;
            case R.id.actionHome:
                finish();
                startActivity(new Intent(getApplicationContext(), Home.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
