package org.michenux.yourappidea.restaurante.cardapio;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.michenux.drodrolib.resources.ResourceUtils;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.aroundme.AroundMeFragment;
import org.michenux.yourappidea.friends.FriendListFragment;
import org.michenux.yourappidea.restaurante.MainFragment;

import java.util.List;

/**
 * Created by alessandro.gurgel on 7/6/15.
 */
public class CardapioAdapter extends ArrayAdapter<CardapioItem> {

    private Fragment fg;

    public CardapioAdapter(Context context, List<CardapioItem> cardapioItems, Fragment fg) {
        super(context, 0, cardapioItems);

        this.fg = fg;
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
        final CardapioItem cardapioItem = getItem(position);

        if (itemView == null ){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.cardapio_list_item, null);
        }

        setCardapioItemView(cardapioItem, itemView);

        Context context = getContext();

        final FragmentManager manager = fg.getParentFragment().getChildFragmentManager();
//        final FragmentManager manager =((FragmentActivity)context).getSupportFragmentManager();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.cardapio_content_frame, new FriendListFragment());
//                transaction.commit();
            }
        });

        return itemView;
    }
}
