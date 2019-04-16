package com.siadous.thomas.mynews.top_stories_details;

import android.support.annotation.NonNull;
import android.util.Log;

import com.siadous.thomas.mynews.Model.TopStories;
import com.siadous.thomas.mynews.Model.TopStoriesListResponse;
import com.siadous.thomas.mynews.Utils.ApiClient;
import com.siadous.thomas.mynews.Utils.ApiInterface;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.siadous.thomas.mynews.Utils.ApiClient.API_KEY;

public class TopStoriesDetailsModel implements TopStoriesDetailsContract.Model {
    private final String TAG = "MovieDetailsModel";

    /**
     * This function will fetch movies data
     * @param onFinishedListener
     * @param pageNo : Which page to load.
     */
    @Override
    public void getTopStoriesDetails(final TopStoriesDetailsContract.Model.OnFinishedListener onFinishedListener, int pageNo) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<TopStoriesListResponse> call = apiService.getTopStories(API_KEY, pageNo);
        call.enqueue(new Callback<TopStoriesListResponse>() {

            @Override
            public void onResponse(@NonNull Call<TopStoriesListResponse> call, @NonNull Response<TopStoriesListResponse> response) {
                try {
                    assert response.body() != null;
                    List<TopStories> topStories = response.body().getResults();
                    Log.d(TAG, "Number of articles received: " + topStories.size());
                    onFinishedListener.onFinished(topStories);
                } catch (NullPointerException e) {
                    Log.d("TAG", String.valueOf(e));
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopStoriesListResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }


}
