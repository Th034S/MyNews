package com.siadous.thomas.mynews.Utils;

import com.siadous.thomas.mynews.Model.TopStories;
import com.siadous.thomas.mynews.Model.TopStoriesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("svc/topstories/v2/home.json")
    Call<TopStoriesListResponse> getTopStories(@Query("api_key") String apiKey, @Query("page") int PageNo);
}
