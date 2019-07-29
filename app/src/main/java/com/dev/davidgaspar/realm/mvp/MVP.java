package com.dev.davidgaspar.realm.mvp;

import android.content.Context;
import android.view.View;

import java.util.List;

public interface MVP {

    interface IItem {

        // Methods getter/setter Id
        void setId(long id);
        long getId();

        // Method getter/setter Word
        void setWord(String word);
        String getWord();

        // Method getter/setter Mean
        void setMean(String mean);
        String getMean();

        void clean();

    }

    interface IMainView {

        // Visibility methods
        void showAddLayout(int visibility);
        void showAddButton(int visibility);
        void showMsgToast(String msg);
        void showUpdatedList(List<IItem> items);

        // Methods setters
        void setEdtHint(String txtWord, String txtMean);
        void setEdtText(String txtWord, String txtMean);

        // Methods getters
        int getItemAddVisible();
        String getEdtWord();
        String getEdtMean();
        Context getContext();
        String  getString(int resId);

    }

    interface IMainPresenter {

        void checkSupportEdtHint();
        void actionButton(View btn);
        void startList();
        void deleteItem(long id);

    }

    interface IRealmDb {

        void init(Context context, String filename);

        void insertData(IItem item);

        void deleteData(long id);
        List<IItem> selectAllData();

    }

}
