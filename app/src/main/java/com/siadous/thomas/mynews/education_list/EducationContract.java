package com.siadous.thomas.mynews.education_list;


import com.siadous.thomas.mynews.Model.Education.Docs;
import com.siadous.thomas.mynews.Model.Education.Education;
import com.siadous.thomas.mynews.Model.Education.EducationResponse;

import java.util.List;

public interface EducationContract {

    // Get data from network request
    interface Model {

        interface OnFinishedListener {

            void onFinished(List<Docs> educationList);

            void onFailure(Throwable t);
        }

        void getEducationList(EducationContract.Model.OnFinishedListener onFinishedListener, int pageNo);
    }

    // Display data in a recycler view
    interface View {
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Docs> educationList);

        void onResponseFailure(Throwable throwable);

    }

    // Link Model and View together
    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();

    }
}
