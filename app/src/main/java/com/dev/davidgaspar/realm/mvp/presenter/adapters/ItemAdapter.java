package com.dev.davidgaspar.realm.mvp.presenter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.davidgaspar.realm.R;
import com.dev.davidgaspar.realm.mvp.modal.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context mContext;

    public ItemAdapter(List<Item> data, Context context) {
        super(context, 0, data);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup container) {

        View itemView = convertView;

        Item item     = getItem(position);
        assert item != null;

        if(itemView == null) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item, container, false);
        }

        TextView txtId   = (TextView) itemView.findViewById(R.id.txt_id);
        String id = "Id: " + item.getId();
        txtId.setText(id);

        TextView txtWord = (TextView) itemView.findViewById(R.id.txt_word);
        txtWord.setText(item.getWord());

        TextView txtMean = (TextView) itemView.findViewById(R.id.txt_mean);
        txtMean.setText(item.getMean());

        return itemView;

    }


}
