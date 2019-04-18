package com.siadous.thomas.mynews.top_stories_list;



import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.siadous.thomas.mynews.Adapters.TopStoriesAdapter;
import com.siadous.thomas.mynews.Model.TopStories;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.Utils.GridSpacingItemDecoration;
import com.siadous.thomas.mynews.Utils.ItemClickSupport;


import java.util.ArrayList;
import java.util.List;

import static com.siadous.thomas.mynews.Utils.GridSpacingItemDecoration.dpToPx;

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
    private LinearLayout linearLayoutItem;
    private TopStories topStories;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        result = inflater.inflate(R.layout.fragment_top_stories, container, false);

        // Référencer les éléments graphique + créer une list de Movie
        initUI();

        setListeners();

        // Initialiser le Presenter
        topStoriesPresenter = new TopStoriesPresenter(this);
        // Obtenir les données de la page 1
        topStoriesPresenter.requestDataFromServer();

        this.configureOnClickRecyclerView();

        // Inflate the layout for this fragment
        return result;
    }


    private void initUI() {

        rvTopStoriesList = result.findViewById(R.id.rv_top_stories_list);

        topStoriesList = new ArrayList<>();
        topStoriesAdapter = new TopStoriesAdapter(this, topStoriesList);

        mLayoutManager = new GridLayoutManager(this.getContext(),1);
        rvTopStoriesList.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(this.getContext(), 10), true));
        rvTopStoriesList.setItemAnimator(new DefaultItemAnimator());
        rvTopStoriesList.setAdapter(topStoriesAdapter);
        rvTopStoriesList.setLayoutManager(mLayoutManager);

        pbLoading = result.findViewById(R.id.pb_loading);

        tvEmptyView = result.findViewById(R.id.tv_empty_view);

        linearLayoutItem = result.findViewById(R.id.linear_layout_item);
    }

    /**
     * This function will contain listeners for all views.
     */
    private void setListeners() {

        rvTopStoriesList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
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
        topStoriesAdapter.updateList(topStoriesArrayList);
        topStoriesAdapter.notifyDataSetChanged();

        // This will help us to fetch data from next page no.
        pageNo++;
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this.getContext(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        topStoriesPresenter.onDestroy();
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(rvTopStoriesList, R.layout.fragment_top_stories)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        TopStories topStories = topStoriesAdapter.getArticle(position);

                        Log.e("TAG", "Position : "+position);
                        Log.d("TAG", "11111111111111");
                        TopStoriesDetailsFragment topStoriesDetailsFragment = new TopStoriesDetailsFragment();
                        Bundle args = new Bundle();
                        args.putString("key", topStories.getUrl());
                        topStoriesDetailsFragment.setArguments(args);
                        getFragmentManager().beginTransaction().replace(R.id.web_view_details, topStoriesDetailsFragment).addToBackStack("Some string").commit();
                        Log.d("TAG", "222222222222222");

                        TopStoriesDetailsFragment topStoriesDetailsFragment1= new TopStoriesDetailsFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.web_view_details, topStoriesDetailsFragment, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    }
                });
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
