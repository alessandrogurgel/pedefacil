package org.michenux.yourappidea.restaurante.cardapio;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.michenux.drodrolib.resources.ResourceUtils;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.restaurante.RestauranteConstants;
import org.michenux.yourappidea.restaurante.prato.PratoMainFragment;

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
        TextView rating = (TextView) itemView.findViewById(R.id.cardapio_item_rating);

        picture.setImageDrawable(ResourceUtils.getDrawableByName(
                cardapioItem.getImageName(), this.getContext()));

        name.setText(cardapioItem.getName());
        category.setText(cardapioItem.getCategory());
        price.setText(String.format("R$ %.2f", cardapioItem.getPrice()));
        rating.setText(String.format("nota: %.2f", cardapioItem.getRating()));
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent)
    {
        final CardapioItem cardapioItem = getItem(position);

        if (itemView == null ){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.cardapio_list_item, null);
        }

        setCardapioItemView(cardapioItem, itemView);

        Context context = getContext();

        final FragmentManager manager =((FragmentActivity)context).getSupportFragmentManager();


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PratoMainFragment fragment = new PratoMainFragment();
                Bundle bundle = new Bundle();

                bundle.putInt(RestauranteConstants.KEY_CARDAPIO_ITEM_ID, cardapioItem.getId());
                fragment.setArguments(bundle);
                manager.beginTransaction()
                        .replace(R.id.content_frame,
                                fragment)
                        .commit();
            }
        });

        return itemView;
    }
}
