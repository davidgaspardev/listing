package com.dev.davidgaspar.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("database.realm")
                .schemaVersion(2)
                .modules(new Item())
                .build();

        Realm realm = Realm.getInstance(config);

        Log.w(LOG_TAG, "Realm | Started " + realm);

        Item item = new Item();
        item.setId(new Date().getTime());
        item.setName("David");
        item.setAge(19);

        realm.beginTransaction();
        realm.copyToRealm(item);
        realm.commitTransaction();
        //realm.deleteAll();

        RealmResults<Item> results = realm.where(Item.class).findAll();

        Log.w(LOG_TAG, "Realm | Reading " + results.toString());

        realm.close();

    }
}
