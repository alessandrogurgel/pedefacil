package org.michenux.yourappidea.restaurante;

import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.friends.FriendListFragment;

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
        return new ViewPagerHandler(getActivity())
                .addPage(R.string.cardapio,
                        new FriendListFragment())
                .addPage(R.string.sugestoes,
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs 2"))
                .addPage(R.string.pedido,
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs 3"))
                .addPage(R.string.ajuda,
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs 4"));
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }
}
