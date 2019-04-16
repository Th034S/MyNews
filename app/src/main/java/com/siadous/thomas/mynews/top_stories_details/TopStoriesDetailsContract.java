package com.siadous.thomas.mynews.top_stories_details;

import com.siadous.thomas.mynews.Model.TopStories;

import java.util.List;

public interface TopStoriesDetailsContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<TopStories> topStories);

            void onFailure(Throwable t);
        }

        void getTopStoriesDetails(OnFinishedListener onFinishedListener, int pageNo);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToViews(TopStories topStories);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestMovieData(int topStoriesID);
    }
}
