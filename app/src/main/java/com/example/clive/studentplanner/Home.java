package com.example.clive.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

    //Firebase instance variables
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mStudyDatabaseReference;
    private ChildEventListener mChildEventListener;

    private String studentName;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Instantiate objects
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        //Reference to write to firebase
        mStudyDatabaseReference = mFirebaseDatabase.getReference().child("studyDetails");

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }

        final FirebaseUser user = mAuth.getCurrentUser();

        String userId = "";
        if (user != null){
            userId = user.getUid();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home ");
        }

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mStudyDatabaseReference.addChildEventListener(mChildEventListener);

//        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("student").child(userId);
//
//        //Attach an addValueEventListener to the database reference
//        dbReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            //Method is called when the activity starts and if changes are made to the database
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Create a user information object and populate it using the data in the datasnapshot
//                StudentDetails studentDetails = dataSnapshot.getValue(StudentDetails.class);
//                //Get name from object and save it to a local variable
//                studentName = studentDetails.getFirstName();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.actionProfile:
                                finish();
                                startActivity(new Intent(getApplicationContext(), CreateProfile.class));
                                break;
                            case R.id.actionAddStudy:
                                finish();
                                startActivity(new Intent(getApplicationContext(), AddCourse.class));
                                break;
                            case R.id.actionCalendar:
                                finish();
                                startActivity(new Intent(getApplicationContext(), Home.class));
                                Toast.makeText(getApplicationContext(), "Under Development", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
    }

    @Override
    //Places the 2 image menu top right of screen
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate and add items to action bar if present.
        getMenuInflater().inflate(R.menu.top_menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionLogout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), Login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
