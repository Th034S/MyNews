package com.siadous.thomas.mynews.Utils;

import com.siadous.thomas.mynews.Model.ArticleSearch.ArticleSearch;
import com.siadous.thomas.mynews.Model.Education.Education;
import com.siadous.thomas.mynews.Model.Education.EducationResponse;
import com.siadous.thomas.mynews.Model.MostPopular.MostPopularList;
import com.siadous.thomas.mynews.Model.TopStories.TopStoriesListResponse;

import java.util.List;

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
    Call<ArticleSearch> getArticleSearch(@Query("api-key") String apiKey, @Query("q") List<String> categories, @Query("page") int PageNo, @Query("keyword") String keyword);

}
