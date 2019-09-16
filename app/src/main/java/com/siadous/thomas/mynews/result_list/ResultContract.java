package com.siadous.thomas.mynews.result_list;

import com.siadous.thomas.mynews.Model.ArticleSearch.Docs;


import java.util.ArrayList;
import java.util.List;

public interface ResultContract {

    // Get data from network request
    interface Model {

        interface OnFinishedListener {

            void onFinished(List<Docs> educationList);

            void onFailure(Throwable t);
        }

        void getResultList(ResultContract.Model.OnFinishedListener onFinishedListener, int pageNo, ArrayList<String> categories, String keyword);
    }

    // Display data in a recycler view
    interface View {
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Docs> resultList);

        void onResponseFailure(Throwable throwable);

    }

    // Link Model and View together
    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo, ArrayList<String> categories, String keyword);

        void requestDataFromServer( ArrayList<String> categories, String keyword);

    }
}
