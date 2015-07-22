package org.michenux.yourappidea.restaurante.pedido;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.michenux.yourappidea.R;
import org.michenux.yourappidea.restaurante.cardapio.CardapioItem;

import java.util.List;

/**
 * Created by alessandro.gurgel on 7/21/15.
 */
public class PedidoAdapter extends ArrayAdapter<Pair<CardapioItem, Integer>> {

    public PedidoAdapter(Context context, List<Pair<CardapioItem, Integer>> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent)
    {
        final Pair<CardapioItem, Integer> item = getItem(position);

        if (itemView == null ){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.pedido_item_list, null);
        }

        TextView pedido =  (TextView)itemView.findViewById(R.id.text_pedido_item);

        pedido.setText(String.format("%s %s", item.second, item.first.getName()));

        TextView total = (TextView)itemView.findViewById(R.id.text_pedido_item_total);
        total.setText(String.format("R$ %.2f", item.first.getPrice() * item.second));


        ImageView image = (ImageView)itemView.findViewById(R.id.image_pedido_item_delete);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println( item.first.getName() );
            }
        });

        return itemView;
    }
}
