package org.michenux.yourappidea.restaurante;

import android.os.Bundle;

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
}
