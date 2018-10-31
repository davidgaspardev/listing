package com.dev.davidgaspar.realm.mvp.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dev.davidgaspar.realm.R;
import com.dev.davidgaspar.realm.mvp.modal.Item;
import com.dev.davidgaspar.realm.mvp.presenter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainFragment extends Fragment {

    private final String LOG_TAG = MainFragment.class.getCanonicalName();

    private EditText edtWord;
    private EditText edtMean;
    private Button   btnSave;
    private ListView lsvList;

    private Realm    realm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        startRealm(getString(R.string.filename));

        edtWord = (EditText) rootView.findViewById(R.id.word);
        edtMean = (EditText) rootView.findViewById(R.id.mean);
        checkEdtHint();

        btnSave = (Button)   rootView.findViewById(R.id.save);
        buildClickEvent();

        lsvList = (ListView) rootView.findViewById(R.id.list);
        updateList();

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

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
                    Toast.makeText(getActivity(), "Not have word", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    private void updateList() {

        ItemAdapter adapter = new ItemAdapter(dataFromRealm(), getContext());
        lsvList.setAdapter(adapter);

    }

    private void startRealm(String filename) {

        // Getting context
        Context context = getContext();
        assert context != null;

        // Initializing the database (Realm)
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(filename)
                .schemaVersion(1)
                .modules(new Item())
                .build();

        realm = Realm.getInstance(config);

    }

    private List<Item> dataFromRealm() {
        RealmResults<Item> results = realm.where(Item.class).findAll();

        List<Item> items = new ArrayList<>(results);

        Collections.reverse(items);

        return items;
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
