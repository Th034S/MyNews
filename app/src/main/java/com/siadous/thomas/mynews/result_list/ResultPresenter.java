package com.siadous.thomas.mynews.result_list;

import com.siadous.thomas.mynews.model.ArticleSearch.Docs;

import java.util.List;

public class ResultPresenter implements ResultContract.Presenter, ResultContract.Model.OnFinishedListener {


    private ResultContract.View resultListView;
    private ResultContract.Model resultListModel;

    ResultPresenter(ResultContract.View resultListView) {
        this.resultListView = resultListView;
        resultListModel = new ResultModel();
    }

    @Override
    public void onDestroy() {
        this.resultListView = null;
    }


    @Override
    public void getMoreData(int pageNo, String categories, String keyword, int beginDate, int endDate) {

        if (resultListView != null) {
            resultListView.showProgress();
        }
        resultListModel.getResultList(this, pageNo, categories, keyword, beginDate, endDate);
    }

    @Override
    public void getMoreDataWithoutDate(int pageNo, String categories, String keyword) {
        if (resultListView != null) {
            resultListView.showProgress();
        }
        resultListModel.getResultListWithoutDate(this, pageNo, categories, keyword);
    }

    @Override
    public void requestDataFromServer(String categories, String keyword, int beginDate, int endDate) {
        if (resultListView != null) {
            resultListView.showProgress();
        }
        resultListModel.getResultList(this, 1, categories, keyword, beginDate, endDate);
    }

    @Override
    public void requestDataFromServerWithoutDate(String categories, String keyword) {
        if (resultListView != null) {
            resultListView.showProgress();
        }
        resultListModel.getResultListWithoutDate(this, 1, categories, keyword);

    }

    @Override
    public void onFinished(List<Docs> resultList) {
        resultListView.setDataToRecyclerView(resultList);
        if (resultListView != null) {
            resultListView.hideProgress();
        }
    }



    @Override
    public void onFailure(Throwable t) {

        resultListView.onResponseFailure(t);
        if (resultListView != null) {
            resultListView.hideProgress();
        }
    }
}
