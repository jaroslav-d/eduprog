package com.example.jaroslav.itmodbep;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EntryDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_in_db);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container_entry_database);

        if (fragment == null) {
            fragment = new EntryDatabaseFragment();
            fm.beginTransaction()
                    .add(R.id.container_entry_database, fragment)
                    .commit();
        }
    }
}
