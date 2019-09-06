package com.siadous.thomas.mynews.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siadous.thomas.mynews.Activities.MainActivity;
import com.siadous.thomas.mynews.Adapters.PageAdapter;
import com.siadous.thomas.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View view;
    SearchFragment searchFragment;
    String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        configureViewPager();

        // Inflate the layout for this fragment
        return view;
    }





    private void configureViewPager(){

        ViewPager pager = view.findViewById(R.id.activity_main_viewpager);

        pager.setAdapter(new PageAdapter(getChildFragmentManager()));

        TabLayout tabs = view.findViewById(R.id.activity_main_tabs);

        tabs.setupWithViewPager(pager);

        tabs.setTabMode(TabLayout.MODE_FIXED);
    }


}
