package com.siadous.thomas.mynews.top_stories_list;

import android.support.annotation.NonNull;
import android.util.Log;

import com.siadous.thomas.mynews.model.TopStories.TopStories;
import com.siadous.thomas.mynews.model.TopStories.TopStoriesListResponse;
import com.siadous.thomas.mynews.utils.ApiClient;
import com.siadous.thomas.mynews.utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.siadous.thomas.mynews.utils.ApiClient.API_KEY;

public class TopStoriesModel implements TopStoriesContract.Model {

    private final String TAG = "TopStoriesModel";
    private int numberOfArticle;

    /**
     * This function will fetch movies data
     * @param onFinishedListener
     * @param pageNo : Which page to load.
     * @return
     */
    @Override
    public void getTopStoriesList(final OnFinishedListener onFinishedListener, int pageNo) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<TopStoriesListResponse> call = apiService.getTopStories(API_KEY, pageNo);
        call.enqueue(new Callback<TopStoriesListResponse>() {

            // correct call or error
            @Override
            public void onResponse(@NonNull Call<TopStoriesListResponse> call, @NonNull Response<TopStoriesListResponse> response) {
                try {
                    assert response.body() != null;
                    if(response.isSuccessful()) {
                        List<TopStories> topStories = response.body().getResults();
                        Log.d(TAG, "Number of articles received: " + topStories.size());
                        numberOfArticle = topStories.size();
                        onFinishedListener.onFinished(topStories);
                    } else {

                    }

                } catch (NullPointerException e) {
                    Log.d("TAG", String.valueOf(e));
                }
            }

            // bad call
            @Override
            public void onFailure(@NonNull Call<TopStoriesListResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });

    }

}