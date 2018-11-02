package com.dev.davidgaspar.realm.mvp.presenter;

import android.os.Build;
import android.view.View;

import com.dev.davidgaspar.realm.R;
import com.dev.davidgaspar.realm.mvp.MVP.IItem;
import com.dev.davidgaspar.realm.mvp.MVP.IRealmDb;
import com.dev.davidgaspar.realm.mvp.MVP.IMainView;
import com.dev.davidgaspar.realm.mvp.MVP.IMainPresenter;
import com.dev.davidgaspar.realm.mvp.modal.Item;
import com.dev.davidgaspar.realm.mvp.presenter.db.RealmDb;

public class MainPresenter implements IMainPresenter, View.OnClickListener {

    private IMainView mView;
    private IRealmDb  mRealm;
    private IItem     mItem;

    public MainPresenter(IMainView view) {
        mView  = view;
        mItem  = new Item();
        mRealm = new RealmDb();
        mRealm.init(mView.getContext(), mView.getString(R.string.filename));
    }


    @Override
    public void checkSupportEdtHint() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mView.setEdtHint(mView.getString(R.string.edt_word), mView.getString(R.string.edt_mean));
        }
    }

    @Override
    public void actionButton(View btn) {
        btn.setOnClickListener(this);
    }

    @Override
    public void startList() {
        mView.showUpdatedList(mRealm.selectAllData());
    }

    @Override
    public void deleteItem(long id) {
        mRealm.deleteData(id);
        mView.showUpdatedList(mRealm.selectAllData());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_add:

                if(mView.getItemAddVisible() != View.VISIBLE) {

                    mView.showAddLayout(View.VISIBLE);
                    mView.showAddButton(View.GONE);

                }

                break;

            case R.id.btn_save:

                if(!mView.getEdtWord().equals("") || !mView.getEdtMean().equals("")) {

                    mItem.setWord(mView.getEdtWord());
                    mItem.setMean(mView.getEdtMean());

                    mRealm.insertData(mItem);

                    mView.showUpdatedList(mRealm.selectAllData());

                    mItem.clean();
                    mView.setEdtText(mItem.getWord(), mItem.getMean());

                    mView.showAddLayout(View.GONE);
                    mView.showAddButton(View.VISIBLE);

                }else {

                    mView.showMsgToast(mView.getString(R.string.err_edt));

                }

                break;
        }

    }
}
