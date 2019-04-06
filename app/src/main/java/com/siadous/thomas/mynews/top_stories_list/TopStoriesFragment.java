package com.siadous.thomas.mynews.top_stories_list;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.siadous.thomas.mynews.Adapters.TopStoriesAdapter;
import com.siadous.thomas.mynews.Model.ResultList;
import com.siadous.thomas.mynews.Model.Results;
import com.siadous.thomas.mynews.Model.TopStories;
import com.siadous.thomas.mynews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment implements TopStoriesContract.View, ShowEmptyView  {

    private static final String TAG = "TopStoriesFragment";
    private TopStoriesPresenter topStoriesPresenter;
    private RecyclerView rvTopStoriesList;
    private List<TopStories> topStoriesList;
    private TopStoriesAdapter topStoriesAdapter;
    private ProgressBar pbLoading;
    private TextView tvEmptyView;

    public View result;

    private int pageNo = 1;

    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private GridLayoutManager mLayoutManager;


    public static TopStoriesFragment newInstance() {
        return(new TopStoriesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        result = inflater.inflate(R.layout.fragment_top_stories, container, false);

        // Référencer les éléments graphique + créer une list de Movie
        initUI();

        setListeners();

        // Initialiser le Presenter
        topStoriesPresenter = new TopStoriesPresenter(this);
        // Obtenir les données de la page 1
        topStoriesPresenter.requestDataFromServer();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_stories, container, false);
    }


    private void initUI() {

        rvTopStoriesList = result.findViewById(R.id.rv_top_stories_list);

        topStoriesList = new ArrayList<>();
        topStoriesAdapter = new TopStoriesAdapter(this, topStoriesList);

        mLayoutManager = new GridLayoutManager(this,1);
        rvTopStoriesList.setLayoutManager(mLayoutManager);
        rvTopStoriesList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(this, 10), true));
        rvTopStoriesList.setItemAnimator(new DefaultItemAnimator());
        rvTopStoriesList.setAdapter(topStoriesAdapter);

        pbLoading = result.findViewById(R.id.pb_loading);

        tvEmptyView = result.findViewById(R.id.tv_empty_view);
    }

    /**
     * This function will contain listeners for all views.
     */
    private void setListeners() {

        rvTopStoriesList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = rvTopStoriesList.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                // Handling the infinite scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    topStoriesPresenter.getMoreData(pageNo);
                    loading = true;
                }

            }
        });

    }


    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<TopStories> topStoriesArrayList) {
        topStoriesList.addAll(topStoriesArrayList);
        topStoriesAdapter.notifyDataSetChanged();

        // This will help us to fetch data from next page no.
        pageNo++;
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        topStoriesPresenter.onDestroy();
    }



    @Override
    public void showEmptyView() {

        rvTopStoriesList.setVisibility(View.GONE);
        tvEmptyView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideEmptyView() {
        rvTopStoriesList.setVisibility(View.VISIBLE);
        tvEmptyView.setVisibility(View.GONE);
    }
}
