package org.michenux.yourappidea.restaurante.cardapio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.michenux.drodrolib.resources.ResourceUtils;
import org.michenux.yourappidea.R;

import java.util.List;

/**
 * Created by alessandro.gurgel on 7/6/15.
 */
public class CardapioAdapter extends ArrayAdapter<CardapioItem> {


    public CardapioAdapter(Context context, List<CardapioItem> cardapioItems) {
        super(context, 0, cardapioItems);
    }

    private void setCardapioItemView(CardapioItem cardapioItem, View itemView)
    {
        ImageView picture = (ImageView) itemView.findViewById(R.id.cardapio_item_picture);
        TextView name = (TextView) itemView.findViewById(R.id.cardapio_item_name);
        TextView category = (TextView) itemView.findViewById(R.id.cardapio_item_category);
        TextView price = (TextView) itemView.findViewById(R.id.cardapio_item_price);

        picture.setImageDrawable(ResourceUtils.getDrawableByName(
                cardapioItem.getImageName(), this.getContext()));

        name.setText(cardapioItem.getName());
        category.setText(cardapioItem.getCategoy());
        price.setText(String.format("R$ %s", Double.toString(cardapioItem.getPrice())));
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent)
    {
        CardapioItem cardapioItem = getItem(position);

        if (itemView == null ){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.cardapio_list_item, null);
        }

        setCardapioItemView(cardapioItem, itemView);
        return itemView;
    }
}
