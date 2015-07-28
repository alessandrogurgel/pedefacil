package org.michenux.yourappidea.restaurante;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;

import org.michenux.drodrolib.ui.navdrawer.NavigationDrawerFragment;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.home.YourAppMainActivity;
import org.michenux.yourappidea.restaurante.cardapio.CardapioFragment;
import org.michenux.yourappidea.restaurante.pedido.PedidoFragment;

/**
 * Created by alessandro.gurgel on 7/5/15.
 */
public class RestauranteMainFragment extends com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment {

    private Toast mToast;
    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected boolean expandTabs() {
        return true;
    }

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        ViewPagerHandler viewPagerHandler = new ViewPagerHandler(getActivity())
                .addPage(R.string.cardapio,
                        new CardapioFragment())
                .addPage(R.string.sugestoes,
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs 2"))
                .addPage(R.string.pedido,
                        new PedidoFragment());

        return viewPagerHandler;

    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            Integer tab = bundle.getInt(RestauranteConstants.KEY_RESTAURANTE_TAB);
            if (tab > 0)
            {
                return  tab;
            }
            bundle.remove(RestauranteConstants.KEY_RESTAURANTE_TAB);
        }

        return 0;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.restaurant_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.chamar_garcon_menu:
                new MaterialDialog.Builder(this.getActivity())
                        .title(R.string.chamar_garcom_info_title)
                        .items(R.array.chamar_garcom_opcoes)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                showToast(String.format("Solicitação enviada. %s está a caminho." , text));
                                return true; // allow selection
                            }
                        })
                        .positiveText(R.string.ask)
                        .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
