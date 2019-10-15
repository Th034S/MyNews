package com.siadous.thomas.mynews.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.Utils.NotificationWorker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class NotificationActivity extends AppCompatActivity {


    String categories = "";
    EditText editTextSearch;
    CheckBox politicsCheckBox;
    CheckBox sportsCheckBox;
    CheckBox travelCheckBox;
    CheckBox businessCheckBox;
    CheckBox artsCheckBox;
    CheckBox entrepreneursCheckBox;
    Switch aSwitch;
    String keyword = " ";
    private SharedPreferences mPreferences; // Use to store data
    public final static String PREFERENCE_FILE = "PREFERENCE_FILE";
    public final static String PREF_KEYWORD = "PREF_KEYWORD";
    private String keywordPref;
    private String categoriesPref;
    public final static String PREF_CATEGORIES = "PREF_CATEGORIES";
    String[] splitCategories;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        configureToolbar();

        initUI();


        mPreferences = getSharedPreferences(PREFERENCE_FILE, MODE_PRIVATE);

        if ((mPreferences.getString(NotificationActivity.PREF_KEYWORD, null)) != null) {
            keywordPref = mPreferences.getString(NotificationActivity.PREF_KEYWORD, null);
            editTextSearch.setText(keywordPref);
        }

        if((mPreferences.getString(PREF_CATEGORIES, null)) != null){
            categoriesPref = mPreferences.getString(NotificationActivity.PREF_CATEGORIES, null);

            assert categoriesPref != null;
            splitCategories =  categoriesPref.split(", ");

            checkSplitCategories();
        }

        onClickSwitch();

    }


    private void checkSplitCategories() {
        for (int a = 0; a < splitCategories.length ; a++) {
            if (splitCategories[a].equals("politics")) {
                politicsCheckBox.setChecked(true);
            }
            if (splitCategories[a].equals("arts")) {
                artsCheckBox.setChecked(true);
            }
            if (splitCategories[a].equals("sports")) {
                sportsCheckBox.setChecked(true);
            }
            if (splitCategories[a].equals("entrepreneurs")) {
                entrepreneursCheckBox.setChecked(true);
            }
            if (splitCategories[a].equals("business")) {
                businessCheckBox.setChecked(true);
            }
            if (splitCategories[a].equals("travel")) {
                travelCheckBox.setChecked(true);
            }
        }

    }


    private void onClickSwitch() {
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveKeyWord();
                retrieveCategories();

                keywordPref = mPreferences.getString(PREF_KEYWORD, null);
                categoriesPref = mPreferences.getString(PREF_CATEGORIES, null);

                mPreferences.edit().putString(PREF_KEYWORD, keyword).apply();
                mPreferences.edit().putString(PREF_CATEGORIES, categories).apply();

                Calendar currentDate = Calendar.getInstance();
                Calendar dueDate = Calendar.getInstance();

                dueDate.set(Calendar.HOUR_OF_DAY, 8);
                dueDate.set(Calendar.MINUTE, 0);
                dueDate.set(Calendar.SECOND, 0);

                if(dueDate.before(currentDate)) {
                    dueDate.add(Calendar.HOUR_OF_DAY, 24);
                }

                long timeDiff =  dueDate.getTimeInMillis() - currentDate.getTimeInMillis();


                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class).setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                        .setConstraints(constraints)
                        .addTag("TAG_OUTPUT").build();


                WorkManager.getInstance().enqueue(oneTimeWorkRequest);

            }
        });
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        try {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Notifications");

            // display back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        } catch (NullPointerException e) {
            Log.e("your app", e.toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void retrieveCategories() {

        if (politicsCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "politics";
            } else {
                categories = categories + ", politics";
            }
        }
        if (artsCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "arts";
            } else {
                categories = categories + ", arts";
            }
        }
        if(entrepreneursCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "entrepreneurs";
            } else {
                categories = categories + ", entrepreneurs";
            }
        }
        if(businessCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "business";
            } else {
                categories = categories + ", business";
            }
        }
        if(travelCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "travel";
            } else {
                categories = categories + ", travel";
            }
        }
        if(sportsCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "sports";
            } else {
                categories = categories + ", sports";
            }
        }
    }


    private void retrieveKeyWord() {
        keyword = editTextSearch.getText().toString();
    }

    private void initUI() {
        editTextSearch = findViewById(R.id.edit_text);
        politicsCheckBox = findViewById(R.id.checkBox_politics);
        sportsCheckBox = findViewById(R.id.checkBox_sports);
        travelCheckBox = findViewById(R.id.checkBox_travel);
        businessCheckBox = findViewById(R.id.checkBox_business);
        artsCheckBox = findViewById(R.id.checkBox_arts);
        entrepreneursCheckBox = findViewById(R.id.checkBox_entrepreneurs);
        aSwitch = findViewById(R.id.switch1);
    }
}


