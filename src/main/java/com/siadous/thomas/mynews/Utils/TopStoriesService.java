package com.siadous.thomas.mynews.Utils;

import com.siadous.thomas.mynews.TopStoriesData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TopStoriesService {
    @GET("svc/{articleType}/v2/home.json?api-key=JrCGkvBIqnBYpL6DjHPBpzLPZndGGBcQsvc")
    Call<List<TopStoriesData>> getFollowing(@Path("articleType") String articleType);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
