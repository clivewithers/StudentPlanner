package com.example.clive.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    //    private DetailsAdapter DetailsAdapter;
    private ArrayList<CourseDetails> myDataset = new ArrayList<>();
    private RecyclerView mRecyclerView;
    // private CourseDetails myDataset;

    //Firebase instance variables
    private FirebaseAuth mAuth;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Instantiate objects
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewDetails);

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home ");
        }

        //Reference to write to firebase
        DatabaseReference mStudyDatabaseReference = mFirebaseDatabase.getReference().child("studyDetails").child(mAuth.getCurrentUser().getUid());

        //Reference query that orders snapshot by day -- need to change day to store int
        Query detailsQuery = mFirebaseDatabase.getReference().child("studyDetails").child(mAuth.getCurrentUser().getUid()).orderByChild("dayInt");

        detailsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    myDataset.add(ds.getValue(CourseDetails.class));
                }
                populateView();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        mStudyDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds: dataSnapshot.getChildren()){
//                    myDataset.add(ds.getValue(CourseDetails.class));
//                }
//                populateView();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


//        mRecyclerView.setAdapter(mAdapter);

 /*
        mRecyclerView.setHasFixedSize(true);
        */
//        mLayoutManager = new GridLayoutManager(this, 2);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

//


//        mChildEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                CourseDetails courseDetails = dataSnapshot.getValue(CourseDetails.class);
//
//                myDataset.add(courseDetails);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
//            }
//        };
//        mStudyDatabaseReference.addChildEventListener(mChildEventListener);



/*        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("student").child(userId);

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

*/

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

    public void populateView(){
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(llm);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(adapter);
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
