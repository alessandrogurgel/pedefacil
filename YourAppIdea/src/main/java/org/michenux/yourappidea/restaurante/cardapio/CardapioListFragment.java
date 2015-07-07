package org.michenux.yourappidea.restaurante.cardapio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.lucasr.twowayview.ItemClickSupport;
import org.michenux.yourappidea.BuildConfig;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.YourApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alessandro.gurgel on 7/5/15.
 */
public class CardapioListFragment extends Fragment implements ItemClickSupport.OnItemClickListener
{
    private CardapioAdapter mCardapioAdapter;

    private List<Cardapio> getCardapioList()
    {
        List<Cardapio> cardapioList = new ArrayList<Cardapio>();

        Cardapio c1 = new Cardapio("Lagosta", "Comida", 22.30, "lagota.png");

        cardapioList.add(c1);

        return cardapioList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.i(YourApplication.LOG_TAG, "CardapioListFragment.onCreate");
        }
        mCardapioAdapter = new CardapioAdapter(this.getActivity(), getCardapioList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cardapio_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.cardapio_recyclerview);

//        List<String> mObjects = new ArrayList<String>(Arrays.asList("1","2","3"));
//        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(getActivity(), R.layout.listview_row, mObjects);
//        listView.setAdapter(mAdapter);

        listView.setAdapter(mCardapioAdapter);
        return view;

    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View view, int i, long l)
    {

    }
}
