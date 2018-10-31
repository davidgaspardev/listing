package com.dev.davidgaspar.realm.mvp;

import android.content.Context;
import android.view.View;

import java.util.List;

public interface MVP {

    interface IItem {

        void setId(long id);
        long getId();

        void setWord(String word);
        String getWord();

        void setMean(String mean);
        String getMean();

        void clean();

    }

    interface IMainView {

        void showItemAdd();
        void showUpdatedList(List<IItem> items);
        void setEdtHint(String txtWord, String txtMean);
        void setEdtText(String txtWord, String txtMean);

        String getEdtWord();
        String getEdtMean();

        Context getContext();
        String  getString(int resId);

    }

    interface IMainPresenter {

        void checkSupportEdtHint();
        void actionSave(View btnSave);
        void startList();
    }

    interface IRealmDb {

        void init(Context context, String filename);

        void insertData(IItem item);

        IItem selectData(long id);
        List<IItem> selectAllData();

    }

}