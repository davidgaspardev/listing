package com.dev.davidgaspar.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmModule;

@RealmModule(classes = { Item.class })
public class Item extends RealmObject {

    @PrimaryKey
    private long   mId;
    private String mWord;
    private String mMean;

    public void setId(long id){
        this.mId = id;
    }

    public void setWord(String word) {
        this.mWord = word;
    }

    public void setMean(String mean) {
        this.mMean = mean;
    }

    public long getId() {
        return this.mId;
    }

    public String getWord() {
        return this.mWord;
    }

    public String getMean() {
        return this.mMean;
    }
}
