package org.michenux.yourappidea.restaurante.prato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import org.michenux.drodrolib.ui.fragment.MasterDetailFragmentHelper;
import org.michenux.drodrolib.ui.fragment.MasterDetailFragments;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.friends.FriendDetailFragment;
import org.michenux.yourappidea.friends.FriendListFragment;

/**
 * Created by alessandro.gurgel on 7/11/15.
 */
public class PratoMainFragment extends Fragment
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friends, container, false);

        MasterDetailFragments currentFragments = MasterDetailFragmentHelper
                .getCurrentFragments(R.id.friendmain_fragment,
                        R.id.frienddetail_fragment, FriendDetailFragment.class,
                        getChildFragmentManager());
        if ( currentFragments.master == null ) {
            currentFragments.master = FriendListFragment.newInstance();
        }


        MasterDetailFragmentHelper.initFragments(currentFragments, R.id.friendmain_fragment,
                R.id.frienddetail_fragment, getResources().getConfiguration(), getChildFragmentManager());


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
