package com.dev.davidgaspar.realm.mvp.modal;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmModule;

@RealmModule(classes = { Item.class })
public class Item extends RealmObject {

    // => Attributes

    @PrimaryKey
    private long   mId;
    private String mWord;
    private String mMean;

    // => Constructor

    public Item() {}

    public Item(String word, String mean) {
        this.mWord = word;
        this.mMean = mean;
    }

    // => Methods getters and setters

    public void setId(long id){
        this.mId = id;
    }

    public long getId() {
        return this.mId;
    }

    public void setWord(String word) {
        this.mWord = word;
    }

    public String getWord() {
        return this.mWord;
    }

    public void setMean(String mean) {
        this.mMean = mean;
    }

    public String getMean() {
        return this.mMean;
    }

}
