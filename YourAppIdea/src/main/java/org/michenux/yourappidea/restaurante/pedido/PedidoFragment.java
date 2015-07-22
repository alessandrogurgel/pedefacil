package org.michenux.yourappidea.restaurante.pedido;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.michenux.yourappidea.BuildConfig;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.YourApplication;
import org.michenux.yourappidea.restaurante.cardapio.CardapioAdapter;
import org.michenux.yourappidea.restaurante.cardapio.CardapioContentProvider;

/**
 * Created by alessandro.gurgel on 7/21/15.
 */
public class PedidoFragment extends Fragment
{
    private PedidoAdapter pedidoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.i(YourApplication.LOG_TAG, "CardapioFragment.onCreate");
        }
        pedidoAdapter = new PedidoAdapter(this.getActivity(), PedidoAbertoManager.getInstance().getItems());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pedido_main_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.pedido_items_list);
        listView.setAdapter(pedidoAdapter);

        TextView total = (TextView)view.findViewById(R.id.text_pedido_total);
        total.setText(PedidoAbertoManager.getInstance().getTotal());

        return view;

    }

}
