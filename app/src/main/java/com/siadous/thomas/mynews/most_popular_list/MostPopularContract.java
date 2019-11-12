package com.siadous.thomas.mynews.most_popular_list;

import com.siadous.thomas.mynews.model.MostPopular.MostPopular;


import java.util.List;

public interface MostPopularContract {

    // Get data from network request
    interface Model {

        interface OnFinishedListener {

            void onFinished(List<MostPopular> mostPopularList);

            void onFailure(Throwable t);
        }

        void getMostPopularList(MostPopularContract.Model.OnFinishedListener onFinishedListener, int pageNo);
    }

    // Display data in a recycler view
    interface View {
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<MostPopular> mostPopularList);

        void onResponseFailure(Throwable throwable);

    }

    // Link Model and View together
    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();

    }
}
