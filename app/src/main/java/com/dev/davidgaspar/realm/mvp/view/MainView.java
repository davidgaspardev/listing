package com.dev.davidgaspar.realm.mvp.view;

// Android's packages
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

// My packages
import com.dev.davidgaspar.realm.R;
import com.dev.davidgaspar.realm.mvp.MVP.IItem;
import com.dev.davidgaspar.realm.mvp.MVP.IMainView;
import com.dev.davidgaspar.realm.mvp.MVP.IMainPresenter;
import com.dev.davidgaspar.realm.mvp.presenter.MainPresenter;
import com.dev.davidgaspar.realm.mvp.presenter.adapters.ItemAdapter;

// Java's package
import java.util.List;

public class MainView extends Fragment implements IMainView {

    // Atributes
    private IMainPresenter presenter;
    private EditText edtWord;
    private EditText edtMean;
    private View     btnSave; // Button
    private View     btnAdd;  // Button
    private View     llyAdd;  // LinearLayout
    private ListView lsvList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter(this);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        edtWord = rootView.findViewById(R.id.word);
        edtMean = rootView.findViewById(R.id.mean);
        presenter.checkSupportEdtHint();

        btnSave = rootView.findViewById(R.id.btn_save);
        presenter.actionButton(btnSave);

        btnAdd  = rootView.findViewById(R.id.btn_add);
        presenter.actionButton(btnAdd);

        lsvList = rootView.findViewById(R.id.list);
        presenter.startList();

        llyAdd  = rootView.findViewById(R.id.lly_add);

        return rootView;

    }

    @Override
    public void showAddLayout(int visibility) {
        llyAdd.setVisibility(visibility);
    }

    @Override
    public void showAddButton(int visibility) {
        btnAdd.setVisibility(visibility);
    }

    @Override
    public void showMsgToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpdatedList(List<IItem> items) {
        ItemAdapter itemAdapter = new ItemAdapter(items, getContext(), presenter);
        lsvList.setAdapter(itemAdapter);
    }

    @Override
    public void setEdtHint(String txtWord, String txtMean) {
        edtWord.setHint(txtWord);
        edtMean.setHint(txtMean);
    }

    @Override
    public void setEdtText(String txtWord, String txtMean) {
        edtWord.setText(txtWord);
        edtMean.setText(txtMean);
    }

    @Override
    public int getItemAddVisible() {
        return llyAdd.getVisibility();
    }

    @Override
    public String getEdtWord() {
        return edtWord.getText().toString().trim();
    }

    @Override
    public String getEdtMean() {
        return edtMean.getText().toString().trim();
    }
}
