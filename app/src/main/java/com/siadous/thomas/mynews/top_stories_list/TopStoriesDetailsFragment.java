package com.siadous.thomas.mynews.top_stories_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.siadous.thomas.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesDetailsFragment extends Fragment {

    private WebView webView;

    public TopStoriesDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_stories_details, container, false);

        String data = getArguments().getString("key");


        webView = view.findViewById(R.id.web_view_details);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(data);



        // Inflate the layout for this fragment
        return view;
    }

}
