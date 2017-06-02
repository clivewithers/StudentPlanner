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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AddCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String weekday;
    private String course;
    private String startTime;
    private String finishTime;
    private String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Spinner spnPaperLevel = (Spinner) findViewById(R.id.spnLevelPaper);
        final Spinner spnCourseId = (Spinner) findViewById(R.id.spnCourseId);
        Spinner spnWeekdays = (Spinner) findViewById(R.id.spn_DayOfWeek);

        ArrayAdapter adaptorPaperLevel = ArrayAdapter.createFromResource(this, R.array.level, android.R.layout.simple_spinner_item);
        spnPaperLevel.setAdapter(adaptorPaperLevel);
        spnPaperLevel.setOnItemSelectedListener(this);

        final ArrayAdapter adaptorCourse5 = ArrayAdapter.createFromResource(this, R.array.Level5, android.R.layout.simple_spinner_item);
        final ArrayAdapter adaptorCourse6 = ArrayAdapter.createFromResource(this, R.array.level6, android.R.layout.simple_spinner_item);
        final ArrayAdapter adaptorCourse7 = ArrayAdapter.createFromResource(this, R.array.level7, android.R.layout.simple_spinner_item);

        spnCourseId.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item));

        do {
                spnPaperLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 1){
                            spnCourseId.setAdapter(adaptorCourse5);
                            spnCourseId.setOnItemSelectedListener(this);
                        }else if (position == 2){
                            spnCourseId.setAdapter(adaptorCourse6);
                            spnCourseId.setOnItemSelectedListener(this);
                        }else if (position == 3){
                            spnCourseId.setAdapter(adaptorCourse7);
                            spnCourseId.setOnItemSelectedListener(this);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
        }
        while (spnCourseId.getSelectedItemPosition() == 0);

        ArrayAdapter adaptor = ArrayAdapter.createFromResource(this, R.array.weekdays, android.R.layout.simple_spinner_item);
        spnWeekdays.setAdapter(adaptor);
        spnWeekdays.setOnItemSelectedListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Study Times");
        }
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
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        weekday = (String) view.toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
