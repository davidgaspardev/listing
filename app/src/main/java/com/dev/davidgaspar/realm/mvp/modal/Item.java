package com.dev.davidgaspar.realm.mvp.modal;

import com.dev.davidgaspar.realm.mvp.MVP.IItem;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmModule;

@RealmModule(classes = { Item.class })
public class Item extends RealmObject implements IItem {

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

    @Override
    public void setId(long id){
        this.mId = id;
    }

    @Override
    public long getId() {
        return this.mId;
    }

    @Override
    public void setWord(String word) {
        this.mWord = word;
    }

    @Override
    public String getWord() {
        return this.mWord;
    }

    @Override
    public void setMean(String mean) {
        this.mMean = mean;
    }

    @Override
    public String getMean() {
        return this.mMean;
    }

    @Override
    public void clean() {
        this.mWord = null;
        this.mMean = null;
    }

}
