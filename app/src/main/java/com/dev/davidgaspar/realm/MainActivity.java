package com.dev.davidgaspar.realm;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getCanonicalName();

    private EditText edtWord;
    private EditText edtMean;
    private Button   btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWord = (EditText) findViewById(R.id.word);
        edtMean = (EditText) findViewById(R.id.mean);
        checkEdtHint();

        btnSave = (Button)   findViewById(R.id.save);
        buildClickEvent();

        //Realm.init(this);

        //RealmConfiguration config = new RealmConfiguration.Builder()
        //        .name("database.realm")
        //        .schemaVersion(2)
        //        .modules(new Item())
        //        .build();

        //Realm realm = Realm.getInstance(config);

        //Log.w(LOG_TAG, "Realm | Started " + realm);

        //Item item = new Item();
        //item.setId(new Date().getTime());
        //item.setName("David");
        //item.setAge(19);

        //realm.beginTransaction();
        //realm.copyToRealm(item);
        //realm.commitTransaction();
        //realm.deleteAll();

        //RealmResults<Item> results = realm.where(Item.class).findAll();

        //Log.w(LOG_TAG, "Realm | Reading " + results.toString());

        //realm.close();

    }

    private void checkEdtHint() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            edtWord.setHint(getString(R.string.edt_word));
            edtMean.setHint(getString(R.string.edt_mean));
        }

    }

    private void buildClickEvent() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String word = edtWord.getText().toString().trim();
                String mean = edtMean.getText().toString().trim();

                if(!word.equals("") && !mean.equals("")) {

                    dataToRealm(word, mean);

                }else {
                    Toast.makeText(MainActivity.this, "Not have word", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    private void dataToRealm(String word, String mean) {

        Item item = new Item();
        item.setId(new Date().getTime());
        item.setWord(word);
        item.setMean(mean);

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("database.realm")
                .schemaVersion(1)
                .modules(new Item())
                .build();

        Realm realm = Realm.getInstance(config);

        realm.beginTransaction();
        realm.copyToRealm(item);
        realm.commitTransaction();

        RealmResults<Item> results = realm.where(Item.class).findAll();

        Log.w(LOG_TAG, " Realm | " + results.toString());

        realm.close();

        edtWord.setText(null);
        edtMean.setText(null);
    }


}
