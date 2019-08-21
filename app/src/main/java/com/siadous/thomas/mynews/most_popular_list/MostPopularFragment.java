package com.siadous.thomas.mynews.most_popular_list;

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
import com.siadous.thomas.mynews.Adapters.MostPopularAdapter;
import com.siadous.thomas.mynews.Model.MostPopular.MostPopular;
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
public class MostPopularFragment extends Fragment implements MostPopularContract.View, ShowEmptyView {


    private static final String TAG = "MostPopularFragment";
    private MostPopularPresenter mostPopularPresenter;
    private RecyclerView rvMostPopularList;
    private List<MostPopular> mostPopularList;
    private MostPopularAdapter mostPopularAdapter;  // A VOIR
    private ProgressBar pbLoading;
    private TextView tvEmptyView;
    private LinearLayout linearLayoutItem;
    private MostPopularDetailFragment mostPopularDetailFragment;

    public View result;

    private int pageNo = 1;

    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private GridLayoutManager mLayoutManager;

    public static MostPopularFragment newInstance() {
        return(new MostPopularFragment());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        result = inflater.inflate(R.layout.fragment_most_popular, container, false);

        initUI();

        setListeners();

        // Initialiser le Presenter
        mostPopularPresenter = new MostPopularPresenter(this);

        // Obtenir les données de la page 1
        mostPopularPresenter.requestDataFromServer();
        this.configureOnClickRecyclerView();
        // Inflate the layout for this fragment
        return result ;
    }


    private void initUI() {

        rvMostPopularList = result.findViewById(R.id.rv_most_popular_list);

        mostPopularList = new ArrayList<>();
        mostPopularAdapter = new MostPopularAdapter(this, mostPopularList);

        mLayoutManager = new GridLayoutManager(this.getContext(),1);
        rvMostPopularList.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(this.getContext(), 10), true));
        rvMostPopularList.setItemAnimator(new DefaultItemAnimator());
        rvMostPopularList.setAdapter(mostPopularAdapter);
        rvMostPopularList.setLayoutManager(mLayoutManager);

        pbLoading = result.findViewById(R.id.pb_loading);

        tvEmptyView = result.findViewById(R.id.tv_empty_view);

        linearLayoutItem = result.findViewById(R.id.linear_layout_item);

    }


    /**
     * This function will contain listeners for all views.
     */
    private void setListeners() {

        rvMostPopularList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = rvMostPopularList.getChildCount();
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
                    mostPopularPresenter.getMoreData(pageNo);
                    loading = true;
                }
            }
        });
    }




    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(rvMostPopularList, R.layout.fragment_details)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        mostPopularDetailFragment = new MostPopularDetailFragment();
                        Bundle args = new Bundle();
                        args.putString("key", mostPopularAdapter.getArticle(position).getUrl());
                        mostPopularDetailFragment.setArguments(args);

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_page_most_popular, mostPopularDetailFragment)
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
    public void setDataToRecyclerView(List<MostPopular> mostPopularArrayList) {
        mostPopularAdapter.updateList(mostPopularArrayList);
        mostPopularAdapter.notifyDataSetChanged();

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
        mostPopularPresenter.onDestroy();
    }



    @Override
    public void showEmptyView() {

        rvMostPopularList.setVisibility(View.GONE);
        tvEmptyView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideEmptyView() {
        rvMostPopularList.setVisibility(View.VISIBLE);
        tvEmptyView.setVisibility(View.GONE);
    }

}
