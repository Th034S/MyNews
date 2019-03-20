package com.siadous.thomas.mynews.Utils;

import android.support.annotation.Nullable;

import com.siadous.thomas.mynews.TopStoriesData;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewYorkTimesCalls {

    public interface Callbacks {
        void onResponse(@Nullable List<TopStoriesData> data);
        void onFailure();
    }


    public static void fetchUserFollowing(Callbacks callbacks, String articleType){


        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);


        TopStoriesService topStoriesService = TopStoriesService.retrofit.create(TopStoriesService.class);


        Call<List<TopStoriesData>> call = topStoriesService.getFollowing(articleType);

        call.enqueue(new Callback<List<TopStoriesData>>() {

            @Override
            public void onResponse(Call<List<TopStoriesData>> call, Response<List<TopStoriesData>> response) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<TopStoriesData>> call, Throwable t) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}
