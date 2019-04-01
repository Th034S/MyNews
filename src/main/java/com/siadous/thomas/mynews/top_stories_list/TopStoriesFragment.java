package com.siadous.thomas.mynews.top_stories_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siadous.thomas.mynews.Model.ResultList;
import com.siadous.thomas.mynews.Model.Results;
import com.siadous.thomas.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment  {


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
    public void onResponse(@Nullable ResultList results) {
        Log.e("thomas", "on response");
      //  if (results != null) this.updateUIWithListOfTopStories(results);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("thomas", "on failure", t);
    }

/*
    private void updateUIWithListOfTopStories(ResultList storiesData){
        StringBuilder stringBuilder = new StringBuilder();
        for (Results result : storiesData){
            stringBuilder.append("-"+ result() +"\n");
        }

    }
 */
}
