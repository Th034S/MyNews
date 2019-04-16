package com.siadous.thomas.mynews.top_stories_details;

import com.siadous.thomas.mynews.Model.TopStories;

import java.util.List;

public class TopStoriesDetailsPresenter implements TopStoriesDetailsContract.Presenter, TopStoriesDetailsContract.Model.OnFinishedListener{

    private TopStoriesDetailsContract.View topStoriesDetailsView;
    private TopStoriesDetailsContract.Model topStoriesDetailsModel;

    public TopStoriesDetailsPresenter(TopStoriesDetailsContract.View topStoriesDetailsView) {
        this.topStoriesDetailsView = topStoriesDetailsView;
        this.topStoriesDetailsModel = new TopStoriesDetailsModel();
    }

    @Override
    public void onDestroy() {

        topStoriesDetailsView = null;
    }

    @Override
    public void requestMovieData(int topStoriesId) {

        if(topStoriesDetailsView != null){
            topStoriesDetailsView.showProgress();
        }
        topStoriesDetailsModel.getTopStoriesDetails(this, topStoriesId);
    }

    @Override
    public void onFinished(List<TopStories> topStories) {

        if(topStoriesDetailsView != null){
            topStoriesDetailsView.hideProgress();
        }
        topStoriesDetailsView.setDataToViews(topStories);
    }

    @Override
    public void onFailure(Throwable t) {
        if(topStoriesDetailsView != null){
            topStoriesDetailsView.hideProgress();
        }
        topStoriesDetailsView.onResponseFailure(t);
    }
}
