package com.example.clive.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.clive.studentplanner.R.id.spnCourseId;

public class AddCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner weekday;
    private int dayInt;
    private Spinner paperLevel;
    private Spinner courseId;
    private TimePicker startTime;
    private TimePicker finishTime;
    private TextView room;
    private Button btnAddCourse;


    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mStudyDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        //Set time pickers to 24 hour views
        startTime = (TimePicker) findViewById(R.id.timePickerTimeStart);
        startTime.setIs24HourView(true);
        finishTime = (TimePicker) findViewById(R.id.timePickerTimeFinish);
        finishTime.setIs24HourView(true);

        //Variable instance variables
        weekday = (Spinner) findViewById(R.id.spn_DayOfWeek);
        paperLevel = (Spinner) findViewById(R.id.spnLevelPaper);
        courseId = (Spinner) findViewById(spnCourseId);
        room = (TextView) findViewById(R.id.txtEditLocation);
        btnAddCourse = (Button) findViewById(R.id.btn_courses);

        //Initialise FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        //Entry point to Firebase database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mStudyDatabaseReference = mFirebaseDatabase.getReference().child("studyDetails").child(mAuth.getCurrentUser().getUid());

        //Set onClick listeners
        btnAddCourse.setOnClickListener(this);

        //Set title in action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Study Times ");
        }

        //Authenticate user, if user not authenticated send to login screen
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }

        //Assign user Id to a variable
        final FirebaseUser user = mAuth.getCurrentUser();

        //Populate weekday array
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.weekdays, android.R.layout.simple_spinner_item);

        //Populate paper level spinner with array from string file
        ArrayAdapter adapterPaperLevel = ArrayAdapter.createFromResource(this, R.array.level, android.R.layout.simple_spinner_item);
        paperLevel.setAdapter(adapterPaperLevel);

        //Array instances be to used on selection of paper level
        final ArrayAdapter adaptorCourse5 = ArrayAdapter.createFromResource(this, R.array.Level5, android.R.layout.simple_spinner_item);
        final ArrayAdapter adaptorCourse6 = ArrayAdapter.createFromResource(this, R.array.level6, android.R.layout.simple_spinner_item);
        final ArrayAdapter adaptorCourse7 = ArrayAdapter.createFromResource(this, R.array.level7, android.R.layout.simple_spinner_item);

        //Select correct ArrayAdapter to use from users paper level selection
        paperLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1){
                    courseId.setAdapter(adaptorCourse5);
                }else if (position == 2){
                    courseId.setAdapter(adaptorCourse6);
                }else if (position == 3){
                    courseId.setAdapter(adaptorCourse7);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        courseId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(! courseId.getSelectedItem().toString().equals("Select Paper"))
                {
                    weekday.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Set the bottom navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        //Listener for bottom navigation
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

    //Used later to order details by day int
    private void setDayInt(String day) {
        switch (day){
            case "Monday":
               dayInt = 1;
                break;
            case "Tuesday":
                dayInt = 2;
                break;
            case "Wednesday":
                dayInt = 3;
                break;
            case "Thursday":
                dayInt = 4;
                break;
            case "Friday":
                dayInt = 5;
                break;
            case "Saturday":
                dayInt = 6;
                break;
            case "Sunday":
                dayInt = 7;
                break;
        }
    }

    @Override
    //Places the 2 image menu top right of screen
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate and add items to action bar if present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    //Handles the events for top menu
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

    //Takes the value of weekday at assings it to variable weekday
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        weekday = (String) view.toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void saveCourse() {
        if(paperLevel.getSelectedItem().toString().equals("Select Level Paper")){
            Toast.makeText(this, "Please select a level of paper to continue", Toast.LENGTH_LONG).show();
        }else if (courseId.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a course to continue", Toast.LENGTH_LONG).show();
        }else if (room.getText().equals("")) {
            Toast.makeText(this, "Please select a location for study. " +
                    "Or enter HOME if it is self study", Toast.LENGTH_LONG).show();
        }else {
            String location = room.getText().toString().trim();
            String Id = courseId.getSelectedItem().toString().trim();
            int startHour = startTime.getHour();
            int startMinute = startTime.getMinute();
            int finishHour = finishTime.getHour();
            int finishMinute = finishTime.getMinute();
            String day = weekday.getSelectedItem().toString().trim();
            setDayInt(day);

            //CourseDetails constructor
            CourseDetails courseDetails = new CourseDetails(Id, day, dayInt, location, startHour, startMinute, finishHour, finishMinute);

            //used to push new details to firebase database
            mStudyDatabaseReference.push().setValue(courseDetails);

            Toast.makeText(getApplicationContext(), "Details recorded - Add more or return home", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        saveCourse();
    }
}
