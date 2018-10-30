package com.dev.davidgaspar.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmModule;

@RealmModule(classes = { Item.class })
public class Item extends RealmObject {

    @PrimaryKey
    private long id;
    private String mName;
    private int mAge;

    public void setName(String name) {
        this.mName = name;
    }

    public void setAge(int age) {
        this.mAge = age;
    }

    public String getName() {
        return this.mName;
    }

    public int getAge() {
        return this.mAge;
    }

    public void setId(long newId){
        this.id = newId;
    }


    public long getId() {
        return this.id;
    }
}
