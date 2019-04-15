package com.siadous.thomas.mynews.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://api.nytimes.com/";
    public static final String API_KEY = "JrCGkvBIqnBYpL6DjHPBpzLPZndGGBcQ";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
