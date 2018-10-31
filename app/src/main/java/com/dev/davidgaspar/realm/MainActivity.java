package com.dev.davidgaspar.realm;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dev.davidgaspar.realm.modal.Item;
import com.dev.davidgaspar.realm.presenter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getCanonicalName();

    private EditText edtWord;
    private EditText edtMean;
    private Button   btnSave;
    private ListView lsvList;

    private Realm    realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startRealm(getString(R.string.filename));

        edtWord = (EditText) findViewById(R.id.word);
        edtMean = (EditText) findViewById(R.id.mean);
        checkEdtHint();

        btnSave = (Button)   findViewById(R.id.save);
        buildClickEvent();

        lsvList = (ListView) findViewById(R.id.list);
        updateList();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
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
                    updateList();

                }else {
                    Toast.makeText(MainActivity.this, "Not have word", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    private void updateList() {

        ItemAdapter adapter = new ItemAdapter(dataFromRealm(), getApplicationContext());
        lsvList.setAdapter(adapter);

    }

    private void startRealm(String filename) {
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(filename)
                .schemaVersion(1)
                .modules(new Item())
                .build();

        realm = Realm.getInstance(config);

    }

    private List<Item> dataFromRealm() {
        RealmResults<Item> results = realm.where(Item.class).findAll();

        return new ArrayList<>(results);
    }

    private void dataToRealm(String word, String mean) {

        Item item = new Item();
        item.setId(new Date().getTime());
        item.setWord(word);
        item.setMean(mean);

        realm.beginTransaction();
        realm.copyToRealm(item);
        realm.commitTransaction();

        RealmResults<Item> results = realm.where(Item.class).findAll();

        Log.w(LOG_TAG, " Realm | " + results.toString());


        edtWord.setText(null);
        edtMean.setText(null);
    }




}
