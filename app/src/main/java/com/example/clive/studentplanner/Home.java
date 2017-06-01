package com.example.clive.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

    private FirebaseAuth mAuth;
    private DatabaseReference mDetailsDatabaseReference;

    private String studentName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }

        final FirebaseUser user = mAuth.getCurrentUser();

        String userId = "";
        if (user != null){
            userId = user.getUid();
        }

        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnCourses = (Button) findViewById(R.id.btnCourses);

        btnProfile.setOnClickListener(this);
        btnCalendar.setOnClickListener(this);
        btnCourses.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home ");
        }

        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("student").child(userId);

        //Attach an addValueEventListener to the database reference
        dbReference.addValueEventListener(new ValueEventListener() {
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
    //Places the 2 image menu top right of screen
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate and add items to action bar if present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == btnProfile) {
            Toast.makeText(this, "Profile button selected - work in progress", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(getApplicationContext(), CreateProfile.class));
        }
        if (v == btnCourses){
            Toast.makeText(this, "Courses button selected - work in progress", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(getApplicationContext(), AddCourse.class));
        }
        if (v == btnCalendar) {
            Toast.makeText(this, "Calendar button selected - work in progress", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
