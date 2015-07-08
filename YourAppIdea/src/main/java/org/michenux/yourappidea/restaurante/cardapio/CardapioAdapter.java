package org.michenux.yourappidea.restaurante.cardapio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.michenux.yourappidea.R;

import java.util.List;

/**
 * Created by alessandro.gurgel on 7/6/15.
 */
public class CardapioAdapter extends ArrayAdapter<Cardapio> {


    public CardapioAdapter(Context context, List<Cardapio> cardapioItems) {
        super(context, 0, cardapioItems);
    }

    private void setCardapioItemView(Cardapio cardapio, View itemView)
    {
        ImageView picture = (ImageView) itemView.findViewById(R.id.cardapio_item_picture);
        TextView name = (TextView) itemView.findViewById(R.id.cardapio_item_name);
        TextView category = (TextView) itemView.findViewById(R.id.cardapio_item_category);
        TextView price = (TextView) itemView.findViewById(R.id.cardapio_item_price);

        picture.setImageResource(R.drawable.airport_landing);

        name.setText(cardapio.getName());
        category.setText(cardapio.getCategoy());
        price.setText(String.format("R$ %s", Double.toString(cardapio.getPrice())));
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent)
    {
        Cardapio cardapio = getItem(position);

        if (itemView == null ){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.cardapio_list_item, null);
        }

        setCardapioItemView(cardapio, itemView);
        return itemView;
    }
}
