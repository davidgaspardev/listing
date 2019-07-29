package com.dev.davidgaspar.realm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dev.davidgaspar.realm.mvp.MVP.IMainView;
import com.dev.davidgaspar.realm.mvp.view.MainView;

public class MainActivity extends AppCompatActivity {

    private IMainView contextView = new MainView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, (Fragment) contextView).commit();
        }

    }

    @Override
    public void onBackPressed() {
        if(contextView.getItemAddVisible() == View.VISIBLE ) {
            contextView.showAddLayout(View.GONE);
            contextView.showAddButton(View.VISIBLE);
        }else {
            super.onBackPressed();
        }
    }

}
