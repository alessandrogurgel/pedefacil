package org.michenux.yourappidea.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.michenux.drodrolib.gms.gplus.GoogleApiClientDelegate;
import org.michenux.drodrolib.security.UserHelper;
import org.michenux.drodrolib.ui.navdrawer.NavDrawerActivityConfiguration;
import org.michenux.drodrolib.ui.navdrawer.NavDrawerItem;
import org.michenux.drodrolib.ui.navdrawer.NavMenuBuilder;
import org.michenux.drodrolib.ui.navdrawer.NavigationDrawerFragment;
import org.michenux.drodrolib.ui.navdrawer.items.NavMenuDivider;
import org.michenux.drodrolib.ui.navdrawer.items.NavMenuHeader;
import org.michenux.drodrolib.ui.navdrawer.items.NavMenuLabelWithIcon;
import org.michenux.drodrolib.ui.navdrawer.items.NavMenuLoginHeader;
import org.michenux.drodrolib.ui.navdrawer.items.NavMenuSection;
import org.michenux.yourappidea.NavigationController;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.aroundme.AroundMeFragment;
import org.michenux.yourappidea.donations.DonateFragment;
import org.michenux.yourappidea.facebook.FbLoginDelegate;
import org.michenux.yourappidea.map.SimpleMapFragment;
import org.michenux.yourappidea.restaurante.RestauranteMainFragment;
import org.michenux.yourappidea.tutorial.TutorialListFragment;

import java.util.List;

import javax.inject.Inject;

public class YourAppNavigationFragment extends NavigationDrawerFragment {

    @Inject
    NavigationController navController;

    @Inject
    UserHelper mUserHelper;

    private List<NavDrawerItem> mPrimaryMenu ;

    private List<NavDrawerItem> mSecondaryMenu;

    private NavMenuLoginHeader mHeaderDrawerItem;

    private boolean mPrimaryMenuDisplayed = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ( savedInstanceState != null ) {
            mPrimaryMenuDisplayed = savedInstanceState.getBoolean("primaryMenuDisplayed");
        }
    }

    @Override
    protected NavDrawerActivityConfiguration createNavigurationConfiguration() {
        NavMenuLoginHeader.OnHeaderProfileMenuListener mHeaderArrowListener = new NavMenuLoginHeader.OnHeaderProfileMenuListener() {

            @Override
            public void doLogin() {
                if ( mUserHelper.getCurrentUser() == null ) {
                    navController.showLogin(YourAppNavigationFragment.this.getActivity());
                }
            }

            @Override
            public void onOpen() {
                showSecondaryMenu();
            }

            @Override
            public void onClose() {
                showPrimaryMenu();
            }
        };

        mHeaderDrawerItem = NavMenuLoginHeader.createMenuItem(10, R.drawable.navdrawer_header_bg, mUserHelper, mHeaderArrowListener);

        NavMenuBuilder primaryMenuBuilder = new NavMenuBuilder()
                .addDrawerItem(mHeaderDrawerItem)
                .addLabelWithIcon(101, R.string.navdrawer_cardapio, R.drawable.navdrawer_cardapio_selector, true, true)
                .addLabelWithIcon(102, R.string.navdrawer_conta, R.drawable.navdrawer_conta_selector, true, true)
                .addLabelWithIcon(103, R.string.navdrawer_sugestoes, R.drawable.navdrawer_sugestoes_selector, true, true)
                .addLabelWithIcon(104, R.string.navdrawer_favoritos, R.drawable.navdrawer_favoritos_selector, true, true)
                .addLabelWithIcon(105, R.string.navdrawer_chamar_atendente, R.drawable.navdrawer_chamar_atendente_selector, true, true)
                .addDivider(199)
                .addLabelWithIcon(201, R.string.navdrawer_avaliações, R.drawable.navdrawer_avaliacoes_selector, true, true)
                .addLabelWithIcon(202, R.string.navdrawer_historico, R.drawable.navdrawer_historico_selector, true, true)
                .addLabelWithIcon(203, R.string.navdrawer_configuracoes, R.drawable.navdrawer_configuracoes_selector, true, true)
                .addLabelWithIcon(204, R.string.navdrawer_redes_sociais, R.drawable.navdrawer_redes_sociais_selector, true, true)
                .addDivider(199)
                .addLabelWithIcon(301, R.string.navdrawer_ajuda, R.drawable.navdrawer_ajuda_selector, false, false)
                .addLabelWithIcon(302, R.string.navdrawer_sobre, R.drawable.navdrawer_sobre_selector, false, false);
        mPrimaryMenu = primaryMenuBuilder.build();

        NavMenuBuilder secondaryMenuBuilder = new NavMenuBuilder()
                .addDrawerItem(mHeaderDrawerItem)
                .addDrawerItem(NavMenuLabelWithIcon.createMenuItem(270, R.string.navdrawer_logout, R.drawable.navdrawer_logout_selector, false, false));
        mSecondaryMenu = secondaryMenuBuilder.build();

        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration.Builder()
                .layout(R.layout.navdrawer)
                .drawerLayoutId(R.id.drawer_layout)
                .recyclerViewId(R.id.navdrawer_recyclerview)
                .toolbarId(R.id.toolbar)
                .leftDrawerId(R.id.navdrawer_recyclerview)
                .registerViewTypeCreator(new NavMenuLabelWithIcon.ViewHolderCreator())
                .registerViewTypeCreator(new NavMenuDivider.ViewHolderCreator())
                .registerViewTypeCreator(new NavMenuSection.ViewHolderCreator())
                .registerViewTypeCreator(new NavMenuHeader.ViewHolderCreator())
                .registerViewTypeCreator(new NavMenuLoginHeader.ViewHolderCreator())
                .build();
        return navDrawerActivityConfiguration;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if ( !this.mPrimaryMenuDisplayed) {
            mHeaderDrawerItem.setArrowOpened(true);
        }

        if ( this.mPrimaryMenuDisplayed ) {
            this.updateMenu(mPrimaryMenu);
        }
        else {
            this.updateMenu(mSecondaryMenu);
        }
    }

    protected void showSecondaryMenu() {
        this.mHeaderDrawerItem.setArrowOpened(true);
        this.updateMenu(this.mSecondaryMenu);
        this.mPrimaryMenuDisplayed = false;
    }

    protected void showPrimaryMenu() {
        this.mHeaderDrawerItem.setArrowOpened(false);
        this.updateMenu(this.mPrimaryMenu);
        this.mPrimaryMenuDisplayed = true;
    }

    @Override
    protected void onNavItemSelected(int id) {
        switch (id) {
            case 101:
                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new RestauranteMainFragment())
                        .commit();
                break;
            case 102:
                //TODO historico de pedidos
                break;
            case 103:
               // TODO sugestoes
                break;
            case 104:
                // TODO favoritos
                break;
            case 105:
                // TODO chamar atendente
                break;
            case 201:
                // TODO avaliacoes
                break;
            case 202:
                // TODO rate this app
                break;
            case 203:
                // TODO configuracoes
                break;
            case 204:
                // TODO redes sociais
                break;
            case 301:
                // TODO configuracoes
                break;
            case 302:
                // TODO sobre
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("primaryMenuDisplayed", this.mPrimaryMenuDisplayed);
    }
}
