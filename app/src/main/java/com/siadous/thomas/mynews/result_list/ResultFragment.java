package com.siadous.thomas.mynews.result_list;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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


import com.siadous.thomas.mynews.Adapters.ResultAdapter;
import com.siadous.thomas.mynews.Model.ArticleSearch.ArticleSearchResponse;
import com.siadous.thomas.mynews.Model.ArticleSearch.Docs;

import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.Utils.GridSpacingItemDecoration;

import com.siadous.thomas.mynews.Utils.ItemClickSupport;
import com.siadous.thomas.mynews.Utils.ShowEmptyView;


import java.util.ArrayList;
import java.util.List;

import static com.siadous.thomas.mynews.Utils.GridSpacingItemDecoration.dpToPx;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment implements ResultContract.View, ShowEmptyView {



    private static final String TAG = "ResultFragment";
    private ResultPresenter resultPresenter;
    private RecyclerView rvResultList;
    private List<Docs> resultList;
    private ResultAdapter resultAdapter;
    private ProgressBar pbLoading;
    private TextView tvEmptyView;
    private LinearLayout linearLayoutItem;
    private ArticleSearchResponse articleSearch;
    private ResultDetailFragment resultDetailFragment;

    public View result;

    private int pageNo = 1;

    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private GridLayoutManager mLayoutManager;

    String keyword;
    ArrayList<String> categories;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        result = inflater.inflate(R.layout.fragment_result, container, false);

        keyword = getArguments().getString("keyword");
        categories = getArguments().getStringArrayList("categories");

        initUI();

        setListeners();

        // Initialiser le Presenter
        resultPresenter = new ResultPresenter(this);

        // Obtenir les données de la page 1
        resultPresenter.requestDataFromServer(categories, keyword);
        this.configureOnClickRecyclerView();

        return result;
    }

    private void initUI() {

        rvResultList = result.findViewById(R.id.rv_result_list);

        resultList = new ArrayList<>();
      //  resultAdapter = new ResultAdapter(this, resultList);

        mLayoutManager = new GridLayoutManager(this.getContext(),1);
        rvResultList.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(this.getContext(), 10), true));
        rvResultList.setItemAnimator(new DefaultItemAnimator());
        rvResultList.setAdapter(resultAdapter);
        rvResultList.setLayoutManager(mLayoutManager);

        pbLoading = result.findViewById(R.id.pb_loading);

        tvEmptyView = result.findViewById(R.id.tv_empty_view);

        linearLayoutItem = result.findViewById(R.id.linear_layout_item);

    }


    /**
     * This function will contain listeners for all views.
     */
    private void setListeners() {

        rvResultList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = rvResultList.getChildCount();
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
                    resultPresenter.getMoreData(pageNo, categories, keyword);
                    loading = true;
                }
            }
        });
    }



    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(rvResultList, R.layout.fragment_details)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        resultDetailFragment = new ResultDetailFragment();
                        Bundle args = new Bundle();
                        args.putString("key", resultAdapter.getArticle(position).getWeb_url());
                        resultDetailFragment.setArguments(args);

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_page_result, resultDetailFragment)
                                .addToBackStack(null)
                                .commit();

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
    public void setDataToRecyclerView(List<Docs> resultArrayList) {
        resultAdapter.updateList(resultArrayList);
        resultAdapter.notifyDataSetChanged();

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
        resultPresenter.onDestroy();
    }



    @Override
    public void showEmptyView() {

        rvResultList.setVisibility(View.GONE);
        tvEmptyView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideEmptyView() {
        rvResultList.setVisibility(View.VISIBLE);
        tvEmptyView.setVisibility(View.GONE);
    }

}
