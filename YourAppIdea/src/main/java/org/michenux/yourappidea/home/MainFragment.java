package org.michenux.yourappidea.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.michenux.drodrolib.ui.navdrawer.NavigationDrawerFragment;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.YourApplication;

public class MainFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((YourApplication) getActivity().getApplication()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater
                .inflate(R.layout.main_fragment, container, false);

        Button table = (Button) mainView.findViewById(R.id.mainmenu_button5);
        table.setOnClickListener(this);

        Button next = (Button) mainView.findViewById(R.id.mainmenu_button6);
        next.setOnClickListener(this);

        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        NavigationDrawerFragment fragment = ((YourAppMainActivity)this.getActivity()).findNavDrawerFragment();
        switch (v.getId()) {
            case R.id.mainmenu_button6:
                fragment.selectItem(1, false);
                break;
        }
    }
}
