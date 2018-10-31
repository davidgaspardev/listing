package com.dev.davidgaspar.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dev.davidgaspar.realm.mvp.view.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
        }

    }

}
