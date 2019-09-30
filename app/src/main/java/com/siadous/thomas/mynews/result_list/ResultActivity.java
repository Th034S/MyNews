package com.siadous.thomas.mynews.result_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.siadous.thomas.mynews.Activities.DetailActivity;
import com.siadous.thomas.mynews.Activities.SearchActivity;
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

public class ResultActivity extends AppCompatActivity implements ResultContract.View, ShowEmptyView {



    private static final String TAG = "ResultFragment";
    private ResultPresenter resultPresenter;
    private RecyclerView rvResultList;
    private List<Docs> resultList;
    private ResultAdapter resultAdapter;
    private ProgressBar pbLoading;
    private TextView tvEmptyView;
    private LinearLayout linearLayoutItem;
    private ArticleSearchResponse articleSearch;
    DetailActivity detailActivity;

    public View result;

    private int pageNo = 1;

    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private GridLayoutManager mLayoutManager;

    String keyword;
    String categories;
    int beginDate;
    int endDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        if (null != intent) {
            keyword = intent.getStringExtra("keyword");
            categories = intent.getStringExtra("categories");
            beginDate = intent.getIntExtra("begin_date", 0 );
            endDate = intent.getIntExtra("end_date", 0);
        }

        configureToolbar();


        initUI();

        setListeners();

        // Initialiser le Presenter
        resultPresenter = new ResultPresenter(this);


        if(beginDate !=0 && endDate !=0) {
            // Obtenir les données de la page 1
            resultPresenter.requestDataFromServer(categories, keyword, beginDate, endDate);
        }else {
            resultPresenter.requestDataFromServerWithoutDate(categories, keyword);
        }
        this.configureOnClickRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(ResultActivity.this, SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initUI() {

        rvResultList = findViewById(R.id.rv_result_list);

        resultList = new ArrayList<>();
        resultAdapter = new ResultAdapter(this, resultList);

        mLayoutManager = new GridLayoutManager(this,1);
        rvResultList.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(this, 10), true));
        rvResultList.setItemAnimator(new DefaultItemAnimator());
        rvResultList.setAdapter(resultAdapter);
        rvResultList.setLayoutManager(mLayoutManager);

        pbLoading = findViewById(R.id.pb_loading);

        tvEmptyView = findViewById(R.id.tv_empty_view);

        linearLayoutItem = findViewById(R.id.linear_layout_item);

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
                    if(beginDate != 0 && endDate != 0) {
                        resultPresenter.getMoreData(pageNo, categories, keyword, beginDate, endDate);
                    } else { resultPresenter.getMoreDataWithoutDate(pageNo, categories, keyword); }
                    loading = true;
                }
            }
        });
    }



    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setTitle("Results");

            // display back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        } catch (NullPointerException e) {
            Log.e("your app", e.toString());
        }

    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(rvResultList, R.layout.activity_detail)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {


                        Intent intent= new Intent(ResultActivity.this ,DetailActivity.class);

                        intent.putExtra("key_url",resultAdapter.getArticle(position).getWeb_url());

                        startActivity(intent);

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
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
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
