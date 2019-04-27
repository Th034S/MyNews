package com.siadous.thomas.mynews.top_stories_list;

import com.siadous.thomas.mynews.Model.TopStories.TopStories;

import java.util.List;

public class TopStoriesPresenter implements TopStoriesContract.Presenter, TopStoriesContract.Model.OnFinishedListener {

    private TopStoriesContract.View topStoriesListView;
    private TopStoriesContract.Model topStoriesListModel;

    TopStoriesPresenter(TopStoriesContract.View topStoriesListView) {
        this.topStoriesListView = topStoriesListView;
        topStoriesListModel = new TopStoriesModel();
    }

    @Override
    public void onDestroy() {
        this.topStoriesListView = null;
    }


    @Override
    public void getMoreData(int pageNo) {

        if (topStoriesListView != null) {
            topStoriesListView.showProgress();
        }
        topStoriesListModel.getTopStoriesList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {
        if (topStoriesListView != null) {
            topStoriesListView.showProgress();
        }
        topStoriesListModel.getTopStoriesList(this, 1);
    }

    @Override
    public void onFinished(List<TopStories> topStoriesList) {
        topStoriesListView.setDataToRecyclerView(topStoriesList);
        if (topStoriesListView != null) {
            topStoriesListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        topStoriesListView.onResponseFailure(t);
        if (topStoriesListView != null) {
            topStoriesListView.hideProgress();
        }
    }
}
