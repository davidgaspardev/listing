package com.dev.davidgaspar.realm.mvp.presenter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.davidgaspar.realm.R;
import com.dev.davidgaspar.realm.mvp.MVP.IMainPresenter;
import com.dev.davidgaspar.realm.mvp.MVP.IItem;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<IItem> {

    private Context mContext;
    private IMainPresenter mPresenter;

    public ItemAdapter(List<IItem> data, Context context, IMainPresenter presenter) {
        super(context, 0, data);
        this.mContext = context;
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup container) {

        View itemView = convertView;

        final IItem item     = getItem(position);
        assert item != null;

        if(itemView == null) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item, container, false);
        }

        TextView txtId   = (TextView) itemView.findViewById(R.id.txt_id);
        String id = "Id: " + item.getId();
        txtId.setText(id);

        txtId.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mPresenter.deleteItem(item.getId());
            }

        });

        TextView txtWord = (TextView) itemView.findViewById(R.id.txt_word);
        txtWord.setText(item.getWord());

        TextView txtMean = (TextView) itemView.findViewById(R.id.txt_mean);
        txtMean.setText(item.getMean());

        return itemView;

    }
}
