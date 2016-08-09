package com.orogersilva.comabem.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orogersilva.comabem.R;

public class MainActivity extends AppCompatActivity {

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // endregion
}
