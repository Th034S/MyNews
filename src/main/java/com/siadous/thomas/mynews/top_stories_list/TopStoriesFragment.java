package com.siadous.thomas.mynews.top_stories_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siadous.thomas.mynews.Model.ResultList;
import com.siadous.thomas.mynews.Model.Results;
import com.siadous.thomas.mynews.Model.TopStories;
import com.siadous.thomas.mynews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment implements TopStoriesContract.View  {


    public static TopStoriesFragment newInstance() {
        return(new TopStoriesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_stories, container, false);
    }


    private void initUI() {

        rvMovieList = findViewById(R.id.rv_movie_list);

        moviesList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, moviesList);

        mLayoutManager = new GridLayoutManager(this, 2);
        rvMovieList.setLayoutManager(mLayoutManager);
        rvMovieList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(this, 10), true));
        rvMovieList.setItemAnimator(new DefaultItemAnimator());
        rvMovieList.setAdapter(moviesAdapter);

        pbLoading = findViewById(R.id.pb_loading);

        fabFilter = findViewById(R.id.fab_filter);

        tvEmptyView = findViewById(R.id.tv_empty_view);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(List<TopStories> topStoriesList) {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
