package com.siadous.thomas.mynews.utils;


import android.content.Context;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.impl.utils.SynchronousExecutor;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotificationWorkerTest {
/*
    @Test
    public void testSimpleNotificationWorker() throws Exception {

        Data input = new Data.Builder()
                .put("KEY_1", 1)
                .put("KEY_2", 2)
                .build();

        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .setInputData(input)
                        .build();

        WorkManager workManager = WorkManager.getInstance();

        workManager.enqueue(request).getResult().get();

        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();
        Data outputData = workInfo.getOutputData();

        assertThat(workInfo.getState(), is(WorkInfo.State.SUCCEEDED));
        assertThat(outputData, is(input));
    }


    @Test
    public void testNotificationWorkerWithFailure() throws Exception {

        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .build();

        WorkManager workManager = WorkManager.getInstance();

        workManager.enqueue(request).getResult().get();

        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();

        assertThat(workInfo.getState(), is(WorkInfo.State.FAILED));
    }

    @Test
    public void testPeriodicWork() throws Exception {
        // Define input data
        Data input = new Data.Builder()
                .put("KEY_1", 1)
                .put("KEY_2", 2)
                .build();

        // Create request
        PeriodicWorkRequest request =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, MINUTES)
                        .setInputData(input)
                        .build();

        WorkManager workManager = WorkManager.getInstance();
        TestDriver testDriver = WorkManagerTestInitHelper.getTestDriver();
        // Enqueue
        workManager.enqueue(request).getResult().get();
        // Tells the testing framework the period delay is met
        testDriver.setPeriodDelayMet(request.getId());
        // Get WorkInfo and outputData
        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();
        // Assert
        assertThat(workInfo.getState(), is(WorkInfo.State.ENQUEUED));
    }

*/
}