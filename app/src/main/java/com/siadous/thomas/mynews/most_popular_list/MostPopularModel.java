package com.siadous.thomas.mynews.most_popular_list;

import android.support.annotation.NonNull;
import android.util.Log;

import com.siadous.thomas.mynews.Model.MostPopular.MostPopular;
import com.siadous.thomas.mynews.Model.MostPopular.MostPopularList;
import com.siadous.thomas.mynews.Model.TopStories.TopStories;
import com.siadous.thomas.mynews.Model.TopStories.TopStoriesListResponse;
import com.siadous.thomas.mynews.Utils.ApiClient;
import com.siadous.thomas.mynews.Utils.ApiInterface;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.siadous.thomas.mynews.Utils.ApiClient.API_KEY;

public class MostPopularModel implements MostPopularContract.Model {

    private final String TAG = "MostPopularModel";

    /**
     * This function will fetch movies data
     * @param onFinishedListener
     * @param pageNo : Which page to load.
     */
    @Override
    public void getMostPopularList(final MostPopularContract.Model.OnFinishedListener onFinishedListener, int pageNo) {
        Log.d("mostPop", "ghsrthbsertb");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MostPopularList> call = apiService.getMostPopular(API_KEY, pageNo);
        Log.d("mostPop", "ghsrthbsertb");
        call.enqueue(new Callback<MostPopularList>() {
            @Override
            public void onResponse(@NonNull Call<MostPopularList> call, @NonNull Response<MostPopularList> response) {
                try {
                    assert response.body() != null;
                    Log.d("mostPop", "ufghfheruhjufffeh");
                    List<MostPopular> mostPopulars = response.body().getResults();
                    Log.d("mostPop", "Number of articles received: " + mostPopulars.size());
                    onFinishedListener.onFinished(mostPopulars);
                } catch (NullPointerException e) {
                    Log.d("TAG", String.valueOf(e));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MostPopularList> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }

}
