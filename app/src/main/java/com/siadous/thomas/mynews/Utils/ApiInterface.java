package com.siadous.thomas.mynews.Utils;

import com.siadous.thomas.mynews.Model.TopStories.TopStoriesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("svc/topstories/v2/home.json")
    Call<TopStoriesListResponse> getTopStories(@Query("api-key") String apiKey, @Query("page") int PageNo);

}
//   https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=JrCGkvBIqnBYpL6DjHPBpzLPZndGGBcQ