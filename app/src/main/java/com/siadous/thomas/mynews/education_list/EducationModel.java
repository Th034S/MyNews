package com.siadous.thomas.mynews.education_list;

import android.support.annotation.NonNull;
import android.util.Log;

import com.siadous.thomas.mynews.Model.Education.Docs;
import com.siadous.thomas.mynews.Model.Education.Education;
import com.siadous.thomas.mynews.Utils.ApiClient;
import com.siadous.thomas.mynews.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.siadous.thomas.mynews.Utils.ApiClient.API_KEY;

public class EducationModel implements EducationContract.Model {

    private final String TAG = "EducationModel";

    /**
     * This function will fetch movies data
     * @param onFinishedListener
     * @param pageNo : Which page to load.
     */
    @Override
    public void getEducationList(final EducationContract.Model.OnFinishedListener onFinishedListener, int pageNo) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Education> call = apiService.getEducation(API_KEY, pageNo);

        call.enqueue(new Callback<Education>() {
            @Override
            public void onResponse(@NonNull Call<Education> call, @NonNull Response<Education> response) {

                if (response.body() != null) {
                    try {
                        List<Docs> educations = response.body().getResponse().getDocs();
                        Log.d(TAG, "Number of articles received: "  + educations.size());
                        onFinishedListener.onFinished(educations);
                    } catch(NullPointerException e) {
                        Log.d(TAG, String.valueOf(e));
                        Log.d(TAG, "blibli");
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Education> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Log.d(TAG, "blabla");
                onFinishedListener.onFailure(t);
            }
        });
    }

}
