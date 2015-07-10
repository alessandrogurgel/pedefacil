package org.michenux.yourappidea.restaurante.cardapio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import org.michenux.yourappidea.BuildConfig;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.YourApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandro.gurgel on 7/5/15.
 */
public class CardapioFragment extends Fragment
{
    private CardapioAdapter mCardapioAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.i(YourApplication.LOG_TAG, "CardapioFragment.onCreate");
        }
        mCardapioAdapter = new CardapioAdapter(this.getActivity(), CardapioContentProvider.getInstance().getCardapio(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cardapio_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.cardapio_recyclerview);

        listView.setAdapter(mCardapioAdapter);
        return view;

    }

}
