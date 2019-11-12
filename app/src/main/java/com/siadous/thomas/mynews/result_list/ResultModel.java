package com.siadous.thomas.mynews.result_list;

import android.support.annotation.NonNull;
import android.util.Log;

import com.siadous.thomas.mynews.model.ArticleSearch.ArticleSearch;
import com.siadous.thomas.mynews.model.ArticleSearch.Docs;
import com.siadous.thomas.mynews.utils.ApiClient;
import com.siadous.thomas.mynews.utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.siadous.thomas.mynews.utils.ApiClient.API_KEY;

public class ResultModel implements ResultContract.Model {

    private final String TAG = "ResultModel";
    private static int numberOfArticle;


    /**
     * This function will fetch movies data
     * @param onFinishedListener
     * @param pageNo : Which page to load.
     * @return
     */
    @Override
    public int getResultList(final OnFinishedListener onFinishedListener, int pageNo, String categories, String keyword, int beginDate, int endDate) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArticleSearch> call = apiService.getArticleSearch(API_KEY, categories, pageNo, keyword, beginDate, endDate);

        call.enqueue(new Callback<ArticleSearch>() {
            @Override
            public void onResponse(@NonNull Call<ArticleSearch> call, @NonNull Response<ArticleSearch> response) {

                if (response.body() != null) {
                    try {
                        List<Docs> result = response.body().getResponse().getDocs();
                        Log.d(TAG, "Number of articles received: "  + result.size());
                        numberOfArticle = result.size();
                        onFinishedListener.onFinished(result);
                    } catch(NullPointerException e) {
                        Log.d(TAG, String.valueOf(e));
                        Log.d(TAG, "blibli");
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArticleSearch> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Log.d(TAG, "blabla");
                onFinishedListener.onFailure(t);
            }
        });
        return numberOfArticle;
    }

    @Override
    public int getResultListWithoutDate(final OnFinishedListener onFinishedListener, int pageNo, String categories, String keyword) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArticleSearch> call = apiService.getArticleForNotification(API_KEY, categories, pageNo, keyword);

        call.enqueue(new Callback<ArticleSearch>() {
            @Override
            public void onResponse(@NonNull Call<ArticleSearch> call, @NonNull Response<ArticleSearch> response) {

                if (response.body() != null) {
                    try {
                        List<Docs> result = response.body().getResponse().getDocs();
                        Log.d(TAG, "Number of articles received: "  + result.size());
                        numberOfArticle = result.size();
                        onFinishedListener.onFinished(result);
                    } catch(NullPointerException e) {
                        Log.d(TAG, String.valueOf(e));
                        Log.d(TAG, "blibli");
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArticleSearch> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Log.d(TAG, "blabla");
                onFinishedListener.onFailure(t);
            }
        });
        return numberOfArticle;
    }
}
