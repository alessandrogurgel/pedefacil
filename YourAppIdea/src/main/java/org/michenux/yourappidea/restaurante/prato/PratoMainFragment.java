package org.michenux.yourappidea.restaurante.prato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Spinner;

import org.michenux.drodrolib.resources.ResourceUtils;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.restaurante.CategoryContentProvider;
import org.michenux.yourappidea.restaurante.RestauranteConstants;
import org.michenux.yourappidea.restaurante.cardapio.CardapioContentProvider;
import org.michenux.yourappidea.restaurante.cardapio.CardapioItem;

/**
 * Created by alessandro.gurgel on 7/11/15.
 */
public class PratoMainFragment extends Fragment
{
    private CardapioItem cardapioItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle bundle = this.getArguments();

        int cardapioId = bundle.getInt(RestauranteConstants.KEY_CARDAPIO_ITEM_ID);
        cardapioItem = CardapioContentProvider.getInstance().getCardapioById(cardapioId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prato_main, container, false);

        TextView name = (TextView) view.findViewById(R.id.prato_name);
        name.setText(cardapioItem.getName());

        TextView price = (TextView) view.findViewById(R.id.prato_price);
        price.setText(String.format("R$ %.2f", cardapioItem.getPrice()));

        TextView category = (TextView) view.findViewById(R.id.prato_category);
        category.setText(cardapioItem.getCategoy());

        ImageView picture = (ImageView) view.findViewById(R.id.prato_picture);
        picture.setImageDrawable(ResourceUtils.getDrawableByName(
                cardapioItem.getImageName(), getActivity()));

        Spinner quantitySpinner = (Spinner) view.findViewById(R.id.prato_pedido_quantidade);
        String[] items = new String[10];
        for  (int i = 0; i < 10; i++)
        {
            items[i] = Integer.toString(i + 1);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spn, items);
        quantitySpinner.setAdapter(adapter);
        quantitySpinner.setSelection(0);

        final EditText total = (EditText) view.findViewById(R.id.prato_pedido_total);
        total.setText(price.getText());

        final EditText unit_price = (EditText) view.findViewById(R.id.prato_pedido_unit_price);
        unit_price.setText(price.getText());

        quantitySpinner.setOnItemClickListener(new Spinner.OnItemClickListener() {
            @Override
            public boolean onItemClick(Spinner spinner, View view, int i, long l) {
                double totalPrice = (i + 1) * cardapioItem.getPrice();
                total.setText(String.format("R$ %.2f", totalPrice));
                return true;
            }
        });
        ImageView categoryPicture = (ImageView) view.findViewById(R.id.prato_category_picture);
        categoryPicture.setImageDrawable(ResourceUtils.getDrawableByName(CategoryContentProvider.getInstance().getImageFromCategory(cardapioItem.getCategoy()), getActivity()));
        return view ;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.prato_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.prato_menu_info:
                new MaterialDialog.Builder(this.getActivity())
                        .title(R.string.prato_info_title)
                        .items(R.array.prato_info_details)
                        .positiveText(R.string.close)
                        .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
