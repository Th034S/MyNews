package com.siadous.thomas.mynews.top_stories_list;

import android.util.Log;

import com.siadous.thomas.mynews.Model.TopStories;
import com.siadous.thomas.mynews.Model.TopStoriesListResponse;
import com.siadous.thomas.mynews.Utils.ApiClient;
import com.siadous.thomas.mynews.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.siadous.thomas.mynews.Utils.ApiClient.API_KEY;

public class TopStoriesModel implements TopStoriesContract.Model {

    private final String TAG = "TopStoriesModel";

    /**
     * This function will fetch movies data
     * @param onFinishedListener
     * @param pageNo : Which page to load.
     */
    @Override
    public void getTopStoriesList(final OnFinishedListener onFinishedListener, int pageNo) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<TopStoriesListResponse> call = apiService.getTopStories(API_KEY, pageNo);
        call.enqueue(new Callback<TopStoriesListResponse>() {

            @Override
            public void onResponse(Call<TopStoriesListResponse> call, Response<TopStoriesListResponse> response) {
                List<TopStories> topStories = response.body().getResults();
                Log.d(TAG, "Number of articles received: " + topStories.size());
                onFinishedListener.onFinished(topStories);
            }

            @Override
            public void onFailure(Call<TopStoriesListResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }

}