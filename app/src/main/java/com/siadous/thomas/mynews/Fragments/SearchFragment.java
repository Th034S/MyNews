package com.siadous.thomas.mynews.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siadous.thomas.mynews.R;

public class SearchFragment extends Fragment {

    public SearchFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_and_notification, container, false);
    }
}
