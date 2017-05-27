package com.example.clive.studentplanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private Button btnProfile;
    private Button btnCourses;
    private Button btnCalendar;
    private Button btnExtra;

    private FirebaseAuth mAuth;
    private DatabaseReference mDetailsDatabaseReference;

    private String studentName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnCourses = (Button) findViewById(R.id.btnCourses);
        btnExtra = (Button) findViewById(R.id.btnExtraTimes);

        btnProfile.setOnClickListener(this);
        btnCalendar.setOnClickListener(this);
        btnCourses.setOnClickListener(this);
        btnExtra.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Study Planner - Home ");
        }

        String user_id = "";
        if (user != null) {
            user_id = user.getUid();
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("student").child(user_id);

        //Attach an addValueEventListener to the database reference
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            //Method is called when the activity starts and if changes are made to the database
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Create a user information object and populate it using the data in the datasnapshot
                StudentDetails studentDetails = dataSnapshot.getValue(StudentDetails.class);
                //Get name from object and save it to a local variable
                studentName = studentDetails.getFirstName();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnProfile) {
            Toast.makeText(this, "Profile button selected - work in progress", Toast.LENGTH_LONG).show();
        }
        if (v == btnCourses){
            Toast.makeText(this, "Courses button selected - work in progress", Toast.LENGTH_LONG).show();
        }
        if (v == btnCalendar) {
            Toast.makeText(this, "Calendar button selected - work in progress", Toast.LENGTH_LONG).show();
        }
        if (v == btnExtra){
            Toast.makeText(this, "Extra button selected - work in progress", Toast.LENGTH_LONG).show();
        }
    }
}
