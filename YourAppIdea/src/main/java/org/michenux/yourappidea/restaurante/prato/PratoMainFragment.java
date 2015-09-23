package org.michenux.yourappidea.restaurante.prato;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
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
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Spinner;

import org.michenux.drodrolib.resources.ResourceUtils;
import org.michenux.drodrolib.ui.navdrawer.NavigationDrawerFragment;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.home.YourAppMainActivity;
import org.michenux.yourappidea.restaurante.CategoryContentProvider;
import org.michenux.yourappidea.restaurante.RestauranteConstants;
import org.michenux.yourappidea.restaurante.RestauranteMainFragment;
import org.michenux.yourappidea.restaurante.cardapio.CardapioContentProvider;
import org.michenux.yourappidea.restaurante.cardapio.CardapioItem;
import org.michenux.yourappidea.restaurante.pedido.PedidoAbertoManager;

import android.widget.Toast;

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
        category.setText(cardapioItem.getCategory());

        ImageView picture = (ImageView) view.findViewById(R.id.prato_picture);
        picture.setImageDrawable(ResourceUtils.getDrawableByName(
                cardapioItem.getImageName(), getActivity()));

        final Spinner quantitySpinner = (Spinner) view.findViewById(R.id.prato_pedido_quantidade);
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
        categoryPicture.setImageDrawable(ResourceUtils.getDrawableByName(CategoryContentProvider.getInstance().getImageFromCategory(cardapioItem.getCategory()), getActivity()));

        com.rey.material.widget.Button opcoesButton = (com.rey.material.widget.Button)view.findViewById(R.id.button_prato_opcoes);

        if (cardapioItem.getOpcionais().isEmpty() ) {
            opcoesButton.setEnabled(false);
            opcoesButton.setBackgroundColor(getResources().getColor(R.color.disable_color));
            opcoesButton.setTextColor(getResources().getColor(R.color.secondary_text));
        }
        else{
            opcoesButton.setEnabled(true);
        }

        final android.widget.EditText optionsText = (android.widget.EditText)view.findViewById(R.id.prato_observacoes);

        final Activity mActivity = getActivity();

        opcoesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDialog.Builder builder = new SimpleDialog.Builder(R.style.Material_App_Dialog_Simple_Light) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        CharSequence[] values = getSelectedValues();
                        String opcionais = TextUtils.join(", ", values);
                        if (optionsText.getText().length() > 0) {
                            optionsText.setText(optionsText.getText() + "\n" + opcionais);
                        } else {
                            optionsText.setText(opcionais);
                        }

                        super.onPositiveActionClicked(fragment);
                    }


                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.multiChoiceItems(cardapioItem.getOpcionaisTextos())
                        .title("Opcionais")
                        .positiveAction("OK")
                        .negativeAction("CANCELAR");

                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getFragmentManager(), null);
            }
        });

        com.rey.material.widget.Button addButton = (com.rey.material.widget.Button)view.findViewById(R.id.prato_adicionar_pedido);

        final FragmentManager manager =(getActivity()).getSupportFragmentManager();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Integer quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
                PedidoAbertoManager.getInstance().adicionarPrato(cardapioItem, quantity);

                RestauranteMainFragment fragment = new RestauranteMainFragment();
                Bundle bundle = new Bundle();

                bundle.putInt(RestauranteConstants.KEY_RESTAURANTE_TAB, RestauranteConstants.TAB_NUMBER_PEDIDO);
                fragment.setArguments(bundle);
                manager.beginTransaction()
                        .replace(R.id.content_frame,
                                fragment)
                        .commit();

            }
        });

        return view ;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.prato_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.prato_rating);
        menuItem.setTitle(String.format("%.2f", cardapioItem.getRating()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

//            case R.id.prato_menu_info:
//                new MaterialDialog.Builder(this.getActivity())
//                        .title(R.string.prato_info_title)
//                        .items(R.array.prato_info_details)
//                        .positiveText(R.string.close)
//                        .show();
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
