package com.siadous.thomas.mynews.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.siadous.thomas.mynews.activities.NotificationActivity;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.result_list.ResultContract;
import com.siadous.thomas.mynews.result_list.ResultModel;


import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

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


        displayNotification(keyword);

        return Result.success();
    }


    private void displayNotification(String keyword) {

     NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

     if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
         assert notificationManager != null;
         notificationManager.createNotificationChannel(channel);
     }

     NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
             .setContentTitle("Articles sur : " + keyword)
             .setContentText("Nombre d'articles trouvés : " + numberOfArticle)
             .setPriority(NotificationCompat.PRIORITY_DEFAULT)
             .setSmallIcon(R.drawable.ic_launcher_background);

        assert notificationManager != null;
        notificationManager.notify(1, builder.build());

    }
}
