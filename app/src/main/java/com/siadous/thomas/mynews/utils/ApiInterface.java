package com.siadous.thomas.mynews.utils;

import com.siadous.thomas.mynews.model.ArticleSearch.ArticleSearch;
import com.siadous.thomas.mynews.model.Education.Education;
import com.siadous.thomas.mynews.model.MostPopular.MostPopularList;
import com.siadous.thomas.mynews.model.TopStories.TopStoriesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    // 3 Endpoints
    @GET("svc/topstories/v2/home.json")
    Call<TopStoriesListResponse> getTopStories(@Query("api-key") String apiKey, @Query("page") int PageNo);

    @GET("svc/mostpopular/v2/viewed/1.json")
    Call<MostPopularList> getMostPopular(@Query("api-key") String apiKey, @Query("page") int PageNo);

    @GET("svc/search/v2/articlesearch.json?q=education")
    Call<Education> getEducation(@Query("api-key") String apiKey, @Query("page") int PageNo);

    @GET ("svc/search/v2/articlesearch.json")
    Call<ArticleSearch> getArticleSearch(@Query("api-key") String apiKey, @Query("q") String categories,
                                         @Query("page") int PageNo, @Query("keyword") String keyword,
                                         @Query("begin_date") int beginDate, @Query("end_date") int endDate );


    @GET ("svc/search/v2/articlesearch.json")
    Call<ArticleSearch> getArticleForNotification(@Query("api-key") String apiKey, @Query("q") String categories,
                                         @Query("page") int PageNo, @Query("keyword") String keyword);
}
