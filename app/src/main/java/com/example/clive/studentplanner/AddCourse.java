package com.example.clive.studentplanner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AddCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

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
}
