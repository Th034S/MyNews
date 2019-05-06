package com.siadous.thomas.mynews.Utils;

import com.siadous.thomas.mynews.Model.Education.EducationResponse;
import com.siadous.thomas.mynews.Model.MostPopular.MostPopularList;
import com.siadous.thomas.mynews.Model.TopStories.TopStoriesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("svc/topstories/v2/home.json")
    Call<TopStoriesListResponse> getTopStories(@Query("api-key") String apiKey, @Query("page") int PageNo);

    @GET("svc/mostpopular/v2/viewed/1.json")
    Call<MostPopularList> getMostPopular(@Query("api-key") String apiKey, @Query("page") int PageNo);

    @GET("svc/search/v2/articlesearch.json?q=education&")
    Call<EducationResponse> getEducation(@Query("api-key") String apiKey, @Query("page") int PageNo);

}
