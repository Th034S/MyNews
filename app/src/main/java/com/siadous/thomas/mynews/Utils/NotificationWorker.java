package com.siadous.thomas.mynews.Utils;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.siadous.thomas.mynews.Activities.NotificationActivity;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.result_list.ResultContract;
import com.siadous.thomas.mynews.result_list.ResultModel;


import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;

public class NotificationWorker extends Worker {

    private SharedPreferences mPreference;
    private String keyword;
    private String categories;
    private ResultModel resultModel;
    private ResultContract.Model.OnFinishedListener onFinishedListener;
    private int numberOfArticle;


    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {


        mPreference = getApplicationContext().getSharedPreferences(NotificationActivity.PREFERENCE_FILE, MODE_PRIVATE);

        keyword = mPreference.getString(NotificationActivity.PREF_KEYWORD, null);
        categories = mPreference.getString(NotificationActivity.PREF_CATEGORIES, null);


        resultModel = new ResultModel();

        numberOfArticle = resultModel.getResultListWithoutDate(onFinishedListener, 1, keyword, categories);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Articles")
                .setContentText("Nombre d'articles trouvés : " + numberOfArticle)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManager mNotificationManager =  getSystemService(this.getApplicationContext(), NotificationManager.class);


        mNotificationManager.notify(0, builder.build());

        return Result.success();
    }
}
