package com.siadous.thomas.mynews.education_list;


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

import com.siadous.thomas.mynews.Adapters.EducationAdapter;


import com.siadous.thomas.mynews.Model.Education.Docs;
import com.siadous.thomas.mynews.Model.Education.EducationResponse;
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
public class EducationFragment extends Fragment implements EducationContract.View, ShowEmptyView {


    private static final String TAG = "EducationFragment";
    private EducationPresenter educationPresenter;
    private RecyclerView rvEducationList;
    private List<Docs> educationList;
    private EducationAdapter educationAdapter;  // A VOIR
    private ProgressBar pbLoading;
    private TextView tvEmptyView;
    private LinearLayout linearLayoutItem;
    private EducationResponse education;
    private EducationDetailFragment educationDetailFragment;

    public View result;

    private int pageNo = 1;

    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private GridLayoutManager mLayoutManager;

    public static EducationFragment newInstance() {
        return(new EducationFragment());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        result = inflater.inflate(R.layout.fragment_education, container, false);

        initUI();

        setListeners();

        Log.d("educationFragment", "aaaaaaaaaaaaa");
        // Initialiser le Presenter
        educationPresenter = new EducationPresenter(this);

        Log.d("educationFragment", "bbbbbbbbbbbbb");
        // Obtenir les données de la page 1
        educationPresenter.requestDataFromServer();
        Log.d("educationFragment", "ccccccccccccc");
        this.configureOnClickRecyclerView();
        // Inflate the layout for this fragment
        return result ;
    }


    private void initUI() {

        rvEducationList = result.findViewById(R.id.rv_education_list);

        educationList = new ArrayList<>();
        educationAdapter = new EducationAdapter(this, educationList);

        mLayoutManager = new GridLayoutManager(this.getContext(),1);
        rvEducationList.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(this.getContext(), 10), true));
        rvEducationList.setItemAnimator(new DefaultItemAnimator());
        rvEducationList.setAdapter(educationAdapter);
        rvEducationList.setLayoutManager(mLayoutManager);

        pbLoading = result.findViewById(R.id.pb_loading);

        tvEmptyView = result.findViewById(R.id.tv_empty_view);

        linearLayoutItem = result.findViewById(R.id.linear_layout_item);

    }


    /**
     * This function will contain listeners for all views.
     */
    private void setListeners() {

        rvEducationList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = rvEducationList.getChildCount();
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
                    educationPresenter.getMoreData(pageNo);
                    loading = true;
                }
            }
        });
    }




    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(rvEducationList, R.layout.fragment_details)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        //  topStories = topStoriesAdapter.getArticle(position);

                        educationDetailFragment = new EducationDetailFragment();
                        Log.d("TAG", "12871255");
                        Bundle args = new Bundle();
                        args.putString("key", educationAdapter.getArticle(position).getWeb_url());
                        educationDetailFragment.setArguments(args);
                        Log.d("TAG", "6666666666666");

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_page_education, educationDetailFragment)
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
    public void setDataToRecyclerView(List<Docs> educationArrayList) {
        educationAdapter.updateList(educationArrayList);
        educationAdapter.notifyDataSetChanged();

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
        educationPresenter.onDestroy();
    }



    @Override
    public void showEmptyView() {

        rvEducationList.setVisibility(View.GONE);
        tvEmptyView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideEmptyView() {
        rvEducationList.setVisibility(View.VISIBLE);
        tvEmptyView.setVisibility(View.GONE);
    }


}
