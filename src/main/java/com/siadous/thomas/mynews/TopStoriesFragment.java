package com.siadous.thomas.mynews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siadous.thomas.mynews.Utils.NewYorkTimesCalls;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment implements NewYorkTimesCalls.Callbacks {


    public static TopStoriesFragment newInstance() {
        return(new TopStoriesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.executeHttpRequestWithRetrofit();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_stories, container, false);
    }

    private void executeHttpRequestWithRetrofit(){
        NewYorkTimesCalls.fetchUserFollowing(this, "topstories");
    }

    @Override
    public void onResponse(@Nullable List<TopStoriesData> data) {
        if (data != null) this.updateUIWithListOfTopStories(data);
    }

    @Override
    public void onFailure() {

    }


    private void updateUIWithListOfTopStories(List<TopStoriesData> storiesData){
        StringBuilder stringBuilder = new StringBuilder();
        for (TopStoriesData topStoriesData : storiesData){
            stringBuilder.append("-"+topStoriesData.results+"\n");
        }

    }
}
