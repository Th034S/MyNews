package com.siadous.thomas.mynews.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.siadous.thomas.mynews.Activities.MainActivity;
import com.siadous.thomas.mynews.Adapters.PageAdapter;
import com.siadous.thomas.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        this.configureToolbar();
        configureViewPager();

        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this.getContext(), "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                Toast.makeText(this.getContext(), "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        // Sets the Toolbar
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

    }


    private void configureViewPager(){

        ViewPager pager = view.findViewById(R.id.activity_main_viewpager);

        pager.setAdapter(new PageAdapter(getChildFragmentManager()));

        TabLayout tabs = view.findViewById(R.id.activity_main_tabs);

        tabs.setupWithViewPager(pager);

        tabs.setTabMode(TabLayout.MODE_FIXED);
    }


}
