package com.siadous.thomas.mynews.education_list;

import android.util.Log;


import com.siadous.thomas.mynews.model.Education.Docs;

import java.util.List;

public class EducationPresenter implements EducationContract.Presenter, EducationContract.Model.OnFinishedListener {


    private EducationContract.View educationListView;
    private EducationContract.Model educationListModel;

    EducationPresenter(EducationContract.View educationListView) {
        this.educationListView = educationListView;
        educationListModel = new EducationModel();
    }

    @Override
    public void onDestroy() {
        this.educationListView = null;
    }


    @Override
    public void getMoreData(int pageNo) {

        if (educationListView != null) {
            educationListView.showProgress();
        }
        educationListModel.getEducationList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {
        if (educationListView != null) {
            educationListView.showProgress();
        }
        Log.d("dataRequest", "lelelelelelel");
        educationListModel.getEducationList(this, 1);
    }

    @Override
    public void onFinished(List<Docs> educationList) {
        educationListView.setDataToRecyclerView(educationList);
        if (educationListView != null) {
            educationListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        educationListView.onResponseFailure(t);
        if (educationListView != null) {
            educationListView.hideProgress();
        }
    }
}
