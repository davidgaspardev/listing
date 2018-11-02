package com.dev.davidgaspar.realm.mvp.presenter.db;

// Android's package
import android.content.Context;

// My packages
import com.dev.davidgaspar.realm.mvp.MVP.IItem;
import com.dev.davidgaspar.realm.mvp.MVP.IRealmDb;
import com.dev.davidgaspar.realm.mvp.modal.Item;

// Java's packages
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// Realm's packages
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmDb implements IRealmDb {

    private Realm mRealm;

    @Override
    public void init(Context context, String filename) {
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(filename)
                .schemaVersion(1)
                .modules(new Item())
                .build();

        this.mRealm = Realm.getInstance(config);

    }

    @Override
    public void insertData(IItem item) {

        item.setId(new Date().getTime());

        this.mRealm.beginTransaction();
        this.mRealm.copyToRealm((Item) item);
        this.mRealm.commitTransaction();

    }

    @Override
    public void deleteData(long id) {

        this.mRealm.beginTransaction();
        IItem results = this.mRealm.where(Item.class).equalTo("mId", id).findFirst();
        assert results != null;
        ((Item) results).deleteFromRealm();
        this.mRealm.commitTransaction();
    }

    @Override
    public List<IItem> selectAllData() {

        RealmResults<Item> results = this.mRealm.where(Item.class).findAll();

        // List<Item> items = new ArrayList<>(results)

        List<IItem> items = new ArrayList<>();
        for (Item item : results) {
            items.add((IItem) item);
        }

        Collections.reverse(items);

        return items;
    }
}
