package com.siadous.thomas.mynews.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siadous.thomas.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends Fragment {


    public static MostPopularFragment newInstance() {
        return(new MostPopularFragment());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_popular, container, false);
    }

}
