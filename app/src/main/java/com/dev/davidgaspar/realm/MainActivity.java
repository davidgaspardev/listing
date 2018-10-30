package com.dev.davidgaspar.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        Log.w(LOG_TAG, "Realm | " + realm);

    }
}
