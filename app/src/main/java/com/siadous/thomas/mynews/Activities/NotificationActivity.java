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

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.Utils.NotificationWorker;

import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        configureToolbar();

        initUI();

        aSwitch.setEnabled(false);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if((politicsCheckBox.isChecked() || businessCheckBox.isChecked() || sportsCheckBox.isChecked() ||
                        artsCheckBox.isChecked() || travelCheckBox.isChecked() || entrepreneursCheckBox.isChecked())
                        && s.toString().length() != 0) {
                    aSwitch.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveKeyWord();
                retrieveCategories();

                mPreferences = getSharedPreferences(PREFERENCE_FILE, MODE_PRIVATE);

                keywordPref = mPreferences.getString(PREF_KEYWORD, null);
                categoriesPref = mPreferences.getString(PREF_CATEGORIES, null);

                mPreferences.edit().putString(PREF_KEYWORD, keyword).apply();
                mPreferences.edit().putString(PREF_CATEGORIES, categories).apply();

                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.SECONDS).build();

                WorkManager.getInstance().enqueue(periodicWorkRequest);
            }
        });
    }




    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        try {
            getSupportActionBar().setTitle("Notifications");

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


