package org.michenux.yourappidea.restaurante;

import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;

/**
 * Created by alessandro.gurgel on 7/5/15.
 */
public class RestauranteMainFragment extends com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment {

    @Override
    protected boolean expandTabs() {
        return false;
    }

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(getActivity())
                .addPage("Tab 1",
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs 1"))
                .addPage("Tab 2",
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs 2"));
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }
}
