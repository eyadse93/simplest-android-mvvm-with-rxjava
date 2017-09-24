package com.eyad.mvvmsample.main.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.eyad.mvvmsample.R;
import com.eyad.mvvmsample.views.TestFragment;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new TestFragment()).commit();
    }
}
